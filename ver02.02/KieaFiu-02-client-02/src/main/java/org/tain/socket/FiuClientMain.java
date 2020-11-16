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
	
	public void process() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// client
			String host = this.projEnvUrlProperties.getConnectHost();
			int port = this.projEnvUrlProperties.getConnectPort();
			
			Socket socket = null;
			LnsSocketTicket lnsSocketTicket = new LnsSocketTicket();
			try {
				socket = new Socket();
				InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
				socket.connect(inetSocketAddress);
				
				// set socket to ticket
				lnsSocketTicket.set(socket);
				log.info(">>>>> {} has a socket. SET SOCKET.", lnsSocketTicket);
				
				
				
				
				
				
				//Sleep.run(1 * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//
			}
		}
		
		//if (Flag.flag) System.exit(0);
	}
}
