package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.object.ticket.LnsSocketTicket;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuServerMain {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private FiuSocket fiuSocket;
	
	@Autowired
	private FiuBiz fiuBiz;
	
	@Autowired
	private FiuFile fiuFile;
	
	public void process() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// server
			ServerSocket serverSocket = new ServerSocket();
			int port = this.projEnvUrlProperties.getListenPort();
			InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", port);
			serverSocket.bind(inetSocketAddress);
			log.info(">>>>> SERVER.inetSocketAddress = {}.", inetSocketAddress);
			
			LnsSocketTicket lnsSocketTicket = new LnsSocketTicket();
			try {
				Socket socket = serverSocket.accept();  // connect-block
					
				// set socket to ticket
				lnsSocketTicket.set(socket);
				log.info(">>>>> {} has a socket. SET SOCKET.", lnsSocketTicket);
				
				boolean flgClose = false;
				LnsJsonNode reqLnsJsonNode = null;
				LnsJsonNode resLnsJsonNode = null;
				String typeCode = null;
				while (!flgClose) {
					reqLnsJsonNode = this.fiuSocket.recv(lnsSocketTicket);
					typeCode = reqLnsJsonNode.getText("/__head_data", "typeCode");
					log.info(">>>>> typeCode = {}", typeCode);
					
					switch (typeCode) {
					// BIZ
					case "06000010": // recvBizOpenReq
						resLnsJsonNode = this.fiuBiz.getBizOpenRes(reqLnsJsonNode);
						this.fiuSocket.send(lnsSocketTicket, resLnsJsonNode);
						break;
					case "06000040": // recvBizCloseReq
						resLnsJsonNode = this.fiuBiz.getBizCloseRes(reqLnsJsonNode);
						this.fiuSocket.send(lnsSocketTicket, resLnsJsonNode);
						flgClose = true;
						break;
					// FILE
					case "03000020": // recvFileStartReq
						resLnsJsonNode = this.fiuFile.getFileStartRes(reqLnsJsonNode);
						this.fiuSocket.send(lnsSocketTicket, resLnsJsonNode);
						// create file
						break;
					case "03000030": // recvFileData
						//this.fiuFile.sendFileStartRes(lnsSocketTicket);
						// write file
						break;
					case "03000040": // recvFileCheckReq
						this.fiuFile.sendFileCheckRes(lnsSocketTicket);
						// check file
						break;
					case "03000050": // recvFileFinishReq
						this.fiuFile.sendFileFinishRes(lnsSocketTicket);
						// close file
						break;
					// ERROR
					default:
						//throw new Exception("ERROR: WRONG TYPE-CODE...");
						flgClose = true;
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (serverSocket != null) try { serverSocket.close(); } catch (Exception e) {}
				// send ERROR of 06000040
				
			}
		}
	}
}
