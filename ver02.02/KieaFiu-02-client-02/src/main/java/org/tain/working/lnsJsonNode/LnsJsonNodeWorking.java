package org.tain.working.lnsJsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsJsonToStream;
import org.tain.mapper.LnsMstInfo;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.socket.FiuTools;
import org.tain.task.MapperReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LnsJsonNodeWorking {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;

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
	
	///////////////////////////////////////////////////////////////////////////
	
	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	public void test03() throws Exception {
		log.info("KANG-20201113 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault(this.projEnvParamProperties);
				lnsJsonNode.put("/__head_data", "typeCode", "03000020");
				
				lnsJsonNode.put("/__body_data", "docCode", "REP002");
				lnsJsonNode.put("/__body_data", "msgCode", "01");
				lnsJsonNode.put("/__body_data", "sendSeq", "00000001");
				lnsJsonNode.put("/__body_data", "docNo", StringTools.getYYYY() + "-00000001");
				lnsJsonNode.put("/__body_data", "annDate", StringTools.getYYYYMMDD());
				lnsJsonNode.put("/__body_data", "befDocNo", "");
				lnsJsonNode.put("/__body_data", "fileName", "CRT-GC0017-" + StringTools.getYYYYMMDD() + "-00000001.env");
				lnsJsonNode.put("/__body_data", "transNo", "0000001/0000001");
				lnsJsonNode.put("/__body_data", "baseDocNo", StringTools.getYYYYMMDD() + "-00000001");
				lnsJsonNode.put("/__body_data", "midOrgCode", "GA0002");
				lnsJsonNode.put("/__body_data", "recLength", "0001");
				lnsJsonNode.put("/__body_data", "zipYn", "0");
				lnsJsonNode.put("/__body_data", "totLength", "0000000100");
				lnsJsonNode.put("/__body_data", "data", "____GPKI_Hash_DATA______");
				
				log.info(">>>>> SEND.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
			}
			
			String strStream = null;
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("/__head_data", "typeCode"));
				strStream = new LnsJsonToStream(lnsMstInfo, lnsJsonNode.get()).get();
				
				log.info(">>>>> SEND.strStream: [{}]", strStream);
			}
			
		}
	}
}
