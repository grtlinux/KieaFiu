package org.tain.working.lnsJsonNode;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LnsJsonNodeWorking {

	public void test01() throws Exception {
		log.info("KANG-20201113 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// Number test
			Number ret = addNumbers(123456789012L, 45699887766123L);
			if (Flag.flag) log.info(">>>>> Number ret = {}", ret.longValue());
		}
	}
	
	public Number addNumbers(Number a, Number b) {
		if(a instanceof Double || b instanceof Double) {
			return a.doubleValue() + b.doubleValue();
		} else if(a instanceof Float || b instanceof Float) {
			return a.floatValue() + b.floatValue();
		} else if(a instanceof Long || b instanceof Long) {
			return a.longValue() + b.longValue();
		} else {
			return a.intValue() + b.intValue();
		}
	}
}
