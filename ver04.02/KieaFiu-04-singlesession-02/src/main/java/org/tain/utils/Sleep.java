package org.tain.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sleep {

	public static void run(long milliseconds) {
		log.info("KANG-20201111 >>>>> Sleep.run({})", milliseconds);
		
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
		}
	}
}
