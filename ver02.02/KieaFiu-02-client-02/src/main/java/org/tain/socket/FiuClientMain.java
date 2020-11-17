package org.tain.socket;

import java.net.InetSocketAddress;
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
public class FiuClientMain {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private FiuBiz fiuBiz;
	
	@Autowired
	private FiuFile fiuFile;
	
	public void process() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		log.info(">>>>> Start InfoObject.name = {}.", InfoObject.name);
		
		if (Flag.flag) {
			// client
			String host = this.projEnvUrlProperties.getConnectHost();
			int port = this.projEnvUrlProperties.getConnectPort();
			
			Socket socket = null;
			LnsSocketTicket lnsSocketTicket = new LnsSocketTicket();
			try {
				socket = new Socket();
				InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
				log.info(">>>>> CLIENT.inetSocketAddress = {}.", inetSocketAddress);
				socket.connect(inetSocketAddress);
				
				// set socket to ticket
				lnsSocketTicket.set(socket);
				log.info(">>>>> {} has a socket. SET SOCKET.", lnsSocketTicket);
				
				if (!Flag.flag) {
					/*
					// send for test
					LnsStream lnsStream = new LnsStream("0030Hello, world !! server........");
					lnsSocketTicket.sendStream(lnsStream);
					log.info(">>>>> SEND.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
					*/
				}
				
				if (Flag.flag) {
					// file
				}
				
				if (Flag.flag) {
					// bizOpen
					this.fiuBiz.sendBizOpenReq(lnsSocketTicket);
					this.fiuBiz.recvBizOpenRes(lnsSocketTicket);
				}
				
				if (Flag.flag) {
					if (Flag.flag) {
						// fileStart
						this.fiuFile.sendFileStartReq(lnsSocketTicket);
						this.fiuFile.recvFileStartRes(lnsSocketTicket);
					}
					
					if (Flag.flag) {
						for (int i=0; i < 1; i++) {
							InfoObject.name += "0";
							log.info(">>>>> Loop InfoObject.name = {}.", InfoObject.name);
							
							if (Flag.flag) {
								// fileData
								this.fiuFile.sendFileData(lnsSocketTicket);
							}
							
							if (Flag.flag) {
								// fileCheck
								this.fiuFile.sendFileCheckReq(lnsSocketTicket);
								this.fiuFile.recvFileCheckRes(lnsSocketTicket);
							}
						}
					}
					
					if (Flag.flag) {
						// fileFinish
						this.fiuFile.sendFileFinishReq(lnsSocketTicket);
						this.fiuFile.recvFileFinishRes(lnsSocketTicket);
					}
				}
				
				if (Flag.flag) {
					// bizClose
					this.fiuBiz.sendBizCloseReq(lnsSocketTicket);
					this.fiuBiz.recvBizCloseRes(lnsSocketTicket);
				}
				//Sleep.run(1 * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//
			}
		}
		
		log.info(">>>>> Finish InfoObject.name = {}.", InfoObject.name);
		
		//if (Flag.flag) System.exit(0);
	}
}
