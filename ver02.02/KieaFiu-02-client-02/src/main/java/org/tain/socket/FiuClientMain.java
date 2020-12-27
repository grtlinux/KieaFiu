package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.object.ticket.LnsSocketTicket;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.enums.FiuType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuClientMain {

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
	
	private FiuInfoFile fiuInfoFile;
	
	///////////////////////////////////////////////////////////////////////////
	
	public void process() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// file to send
			boolean isSuccessOfGetFile = this.fiuInfo.setFiuInfoFiles();
			if (!isSuccessOfGetFile) {
				return;
			}
			
			if (!this.fiuInfo.setFiuInfoFileNext())
				return;
		}
		
		LnsSocketTicket lnsSocketTicket = null;
		if (Flag.flag) {
			// connection socket
			String host = this.projEnvUrlProperties.getConnectHost();
			int port = this.projEnvUrlProperties.getConnectPort();
			
			lnsSocketTicket = new LnsSocketTicket();
			Socket socket = new Socket();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
			log.info(">>>>> CLIENT.inetSocketAddress = {}.", inetSocketAddress);
			socket.connect(inetSocketAddress);
			
			// set socket to ticket
			lnsSocketTicket.set(socket);
			log.info(">>>>> {} has a socket. SET SOCKET.", lnsSocketTicket);
		}
		
		if (Flag.flag) {
			// transfer file with connection socket
			
			LnsJsonNode reqLnsJsonNode = null;
			LnsJsonNode resLnsJsonNode = null;
			
			FiuType fiuType = FiuType.BIZ_OPEN;
			String typeCode = null;
			
			while (fiuType != FiuType.FIU_END) {
				if (Flag.flag) {
					// reqLnsJsonNode
					if (fiuType == FiuType.BIZ_OPEN) {
						reqLnsJsonNode = this.fiuBiz.getBizOpenReq();
					} else if (fiuType == FiuType.FILE_START) {
						this.fiuInfoFile = this.fiuInfo.getFiuInfoFile();
						reqLnsJsonNode = this.fiuFile.getFileStartReq(this.fiuInfoFile);
					} else if (fiuType == FiuType.FILE_SEND_DATA) {
						reqLnsJsonNode = this.fiuFile.getFileSendData();
					} else if (fiuType == FiuType.FILE_CHECK) {
						reqLnsJsonNode = this.fiuFile.getFileCheckReq();
					} else if (fiuType == FiuType.FILE_FINISH) {
						reqLnsJsonNode = this.fiuFile.getFileFinishReq();
					} else if (fiuType == FiuType.BIZ_CLOSE) {
						reqLnsJsonNode = this.fiuBiz.getBizCloseReq();
					} else {
						throw new Exception("WRONG FiuType...");
					}
				}
				
				if (Flag.flag) {
					if (fiuType == FiuType.FILE_SEND_DATA) {
						this.fiuSocket.sendData(lnsSocketTicket);
						fiuType = FiuType.FILE_CHECK;
						continue;
					} else {
						this.fiuSocket.send(lnsSocketTicket, reqLnsJsonNode);
					}
				}
				
				if (Flag.flag) {
					// recv
					resLnsJsonNode = this.fiuSocket.recv(lnsSocketTicket);
				}
				
				if (Flag.flag) {
					// typeCode
					typeCode = resLnsJsonNode.getText("/__head_data", "typeCode");
					log.info(">>>>> typeCode = {}", typeCode);
				}
				
				if (Flag.flag) {
					// resLnsJsonNode
					if ("06100010".equals(typeCode)) {          // BIZ_OPEN_RES
						fiuType = FiuType.FILE_START;
					} else if ("03100020".equals(typeCode)) {   // FILE_START_RES
						fiuType = FiuType.FILE_SEND_DATA;
					} else if ("03100040".equals(typeCode)) {   // FILE_CHECK_RES
						if (this.fiuInfoFile.setNextPage())
							fiuType = FiuType.FILE_SEND_DATA;
						else
							fiuType = FiuType.FILE_FINISH;
					} else if ("03100050".equals(typeCode)) {   // FILE_FINISH_RES
						this.fiuInfoFile.moveFile();
						if (this.fiuInfo.setFiuInfoFileNext()) {
							fiuType = FiuType.FILE_START;
						} else {
							fiuType = FiuType.BIZ_CLOSE;
						}
					} else if ("06100040".equals(typeCode)) {   // BIZ_CLOSE_RES
						fiuType = FiuType.FIU_END;
					}
				}
			}
		}
		
		if (Flag.flag) {
		}
	}
}
