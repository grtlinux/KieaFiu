package org.tain.socket;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.tain.object.ticket.LnsSocketTicket;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FiuClientMain {

	public static void process() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// client
			//String host = this.projEnvJobProperties.getServerHost();
			String host = "localhost";
			//int port = this.projEnvJobProperties.getServerPort();
			int port = 1234;
			
			Socket socket = null;
			try {
				LnsSocketTicket lnsSocketTicket = null;
				
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
	}
}
