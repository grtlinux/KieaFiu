package org.tain.socket;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Setter
@Getter
@Slf4j
public class InfoObject {

	private String name;
	
	//////////////////////////////////////////////////////////////////
	
	public InfoObject() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
	}
}
