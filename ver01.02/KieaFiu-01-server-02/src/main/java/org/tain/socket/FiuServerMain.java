package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.object.lns.LnsStream;
import org.tain.object.ticket.LnsSocketTicket;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuServerMain {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
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
				
				// recv
				LnsStream lnsStream = lnsSocketTicket.recvStream();
				log.info(">>>>> RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
				
				
				
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
