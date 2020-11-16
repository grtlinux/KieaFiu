package org.tain.working.lnsJsonNode;

import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
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
	
	///////////////////////////////////////////////////////////////////////////
	
	public void test02() throws Exception {
		log.info("KANG-20201113 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = new LnsJsonNode();
			lnsJsonNode.put("field1", "Hello world");
			lnsJsonNode.put("field2", 12345);
			lnsJsonNode.put("field4", (Double) 12345.1234);
			lnsJsonNode.put("field3", false);
			
			if (Flag.flag) log.info(">>>>> 1. lnsJsonNode = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = new LnsJsonNode("[]");
			lnsJsonNode.add("Hello world");
			lnsJsonNode.add(12345);
			lnsJsonNode.add(123456789L);
			lnsJsonNode.add((Double) 123456789.123);
			lnsJsonNode.add(true);
			
			if (Flag.flag) log.info(">>>>> 2. lnsJsonNode = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{}");
			lnsJsonNode.put("head", new LnsJsonNode().get());
			lnsJsonNode.put("body", new LnsJsonNode().get());
			lnsJsonNode.put("foot", new LnsJsonNode().get());
			
			lnsJsonNode.put("/head", "name", "my header");
			lnsJsonNode.put("/body", "name", "my body");
			lnsJsonNode.put("/foot", "name", "my footer");
			lnsJsonNode.put("/body", "data", "body data");
			lnsJsonNode.put("/body", "salary", 1234567);
			lnsJsonNode.put("/body", "usable", true);
			
			if (Flag.flag) log.info(">>>>> 3. lnsJsonNode = {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{}");
			
			if (Flag.flag) log.info(">>>>> 4. lnsJsonNode = {}", lnsJsonNode.toPrettyString());
		}
	}
}
