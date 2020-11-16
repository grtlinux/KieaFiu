package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
				
				if (!Flag.flag) {
					/*
					// recv
					LnsStream lnsStream = lnsSocketTicket.recvStream();
					log.info(">>>>> RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
					*/
				}
				
				if (Flag.flag) {
					// bizOpen
					this.fiuBiz.recvBizOpenReq(lnsSocketTicket);
					this.fiuBiz.sendBizOpenRes(lnsSocketTicket);
				}
				
				if (Flag.flag) {
					if (Flag.flag) {
						// fileStart
						this.fiuFile.recvFileStartReq(lnsSocketTicket);
						this.fiuFile.sendFileStartRes(lnsSocketTicket);
					}
					
					if (Flag.flag) {
						for (int i=0; i < 1; i++) {
							if (Flag.flag) {
								// fileData
								this.fiuFile.recvFileData(lnsSocketTicket);
							}
							
							if (Flag.flag) {
								// fileCheck
								this.fiuFile.recvFileCheckReq(lnsSocketTicket);
								this.fiuFile.sendFileCheckRes(lnsSocketTicket);
							}
						}
					}
					
					if (Flag.flag) {
						// fileFinish
						this.fiuFile.recvFileFinishReq(lnsSocketTicket);
						this.fiuFile.sendFileFinishRes(lnsSocketTicket);
					}
				}
				
				if (Flag.flag) {
					// bizClose
					this.fiuBiz.recvBizCloseReq(lnsSocketTicket);
					this.fiuBiz.sendBizCloseRes(lnsSocketTicket);
				}
				
				//Sleep.run(1 * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (serverSocket != null) try { serverSocket.close(); } catch (Exception e) {}
			}
		}
		
		//if (Flag.flag) System.exit(0);
	}
}
