package org.tain.socket;

import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FiuTools {

	public static LnsJsonNode getDefault(ProjEnvParamProperties projEnvParamProperties) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			/*
			lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
			lnsJsonNode.put("/__head_data", "length", "0000");
			//lnsJsonNode.put("/__head_data", "transactionCode", "DFIUXBOGO");
			lnsJsonNode.put("/__head_data", "transactionCode", "OFIUXBOGO");
			lnsJsonNode.put("/__head_data", "systemName", "FIU");
			lnsJsonNode.put("/__head_data", "bogoCode", "GC0017");
			lnsJsonNode.put("/__head_data", "userId", "hanwha9");
			lnsJsonNode.put("/__head_data", "typeCode", "00000000");
			lnsJsonNode.put("/__head_data", "reqresGubun", "S");
			lnsJsonNode.put("/__head_data", "orgGubun", "B");
			lnsJsonNode.put("/__head_data", "sequenceYn", "N");
			lnsJsonNode.put("/__head_data", "resCode", "000");
			*/
		}
		
		if (Flag.flag) {
			lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
			lnsJsonNode.put("/__head_data", "length", "0000");
			lnsJsonNode.put("/__head_data", "transactionCode", projEnvParamProperties.getTransactionCode());
			lnsJsonNode.put("/__head_data", "systemName", projEnvParamProperties.getSystemName());
			lnsJsonNode.put("/__head_data", "bogoCode", projEnvParamProperties.getBogoCode());
			lnsJsonNode.put("/__head_data", "userId", projEnvParamProperties.getUserId());
			lnsJsonNode.put("/__head_data", "typeCode", "00000000");
			lnsJsonNode.put("/__head_data", "reqresGubun", "S");
			lnsJsonNode.put("/__head_data", "orgGubun", "B");
			lnsJsonNode.put("/__head_data", "sequenceYn", "N");
			lnsJsonNode.put("/__head_data", "resCode", "000");
		}
		
		return lnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
}
