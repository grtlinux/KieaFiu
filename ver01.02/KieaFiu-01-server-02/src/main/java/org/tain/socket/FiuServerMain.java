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
	
	@Autowired
	private FiuInfo fiuInfo;
	
	public void process() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsSocketTicket lnsSocketTicket = null;
		ServerSocket serverSocket = new ServerSocket();
		if (Flag.flag) {
			int port = this.projEnvUrlProperties.getListenPort();
			InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", port);
			serverSocket.bind(inetSocketAddress);
			log.info(">>>>> SERVER.inetSocketAddress = {}.", inetSocketAddress);
			
			lnsSocketTicket = new LnsSocketTicket();
			
			Socket socket = serverSocket.accept();  // connect-block
			
			// set socket to ticket
			lnsSocketTicket.set(socket);
			log.info(">>>>> {} has a socket. SET SOCKET.", lnsSocketTicket);
			log.info(">>>>> {}", this.fiuInfo.getName());
		}
		
		if (Flag.flag) {
			// server
			LnsJsonNode reqLnsJsonNode = null;
			LnsJsonNode resLnsJsonNode = null;
			
			boolean flgClose = false;
			String typeCode = null;
			
			try {
				while (!flgClose) {
					if (Flag.flag) {
						// recv
						reqLnsJsonNode = this.fiuSocket.recv(lnsSocketTicket);
						typeCode = reqLnsJsonNode.getText("/__head_data", "typeCode");
						log.info(">>>>> typeCode = {}", typeCode);
					}
					
					if ("06000010".equals(typeCode)) {
						resLnsJsonNode = this.fiuBiz.getBizOpenRes(reqLnsJsonNode);
					} else if ("03000020".equals(typeCode)) {
						resLnsJsonNode = this.fiuFile.getFileStartRes(reqLnsJsonNode);
					} else if ("03000030".equals(typeCode)) {
						// recv file content
						//resLnsJsonNode = this.fiuFile.getFileStartRes(reqLnsJsonNode);
					} else if ("03000040".equals(typeCode)) {
						resLnsJsonNode = this.fiuFile.getFileCheckRes(reqLnsJsonNode);
					} else if ("03000050".equals(typeCode)) {
						resLnsJsonNode = this.fiuFile.getFileFinishRes(reqLnsJsonNode);
					} else if ("06000040".equals(typeCode)) {
						resLnsJsonNode = this.fiuBiz.getBizCloseRes(reqLnsJsonNode);
						flgClose = true;
					}
					
					if (Flag.flag) {
						// send
						this.fiuSocket.send(lnsSocketTicket, resLnsJsonNode);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				// send ERROR of 06000040
				resLnsJsonNode = this.fiuBiz.getBizCloseRes(reqLnsJsonNode);
				this.fiuSocket.send(lnsSocketTicket, resLnsJsonNode);
			} finally {
				if (serverSocket != null) try { serverSocket.close(); } catch (Exception e) {}
			}
		}
	}
}
