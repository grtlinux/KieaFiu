package org.tain.working.gpki;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HostInfo {

	public void test01() throws Exception {
		log.info("KANG-20201128 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			InetAddress ip = null;
			try {
				ip = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			System.out.println("IP           : " + ip.getHostAddress());
			System.out.println("HostName     : " + ip.getHostName());
			System.out.println("java.vendor  : " + java.lang.System.getProperty("java.vendor").trim());
			System.out.println("os.name      : " + java.lang.System.getProperty("os.name").trim());
			System.out.println("--------------------------------------");
			System.out.println("java.version : " + java.lang.System.getProperty("java.version").trim());
		}
	}
}
