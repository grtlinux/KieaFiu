package org.tain.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuBiz {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizOpenReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault(this.projEnvParamProperties);
			reqLnsJsonNode.put("/__head_data", "typeCode", "06000010");
			
			reqLnsJsonNode.put("/__body_data", "openDataTime", StringTools.getYYYYMMDDHHMMSS());
			reqLnsJsonNode.put("/__body_data", "fileCheckSec", "0030");
			reqLnsJsonNode.put("/__body_data", "sendMethod", "B");
			reqLnsJsonNode.put("/__body_data", "encYn", "0");
			reqLnsJsonNode.put("/__body_data", "seqTakeYn", "N");
			
			log.info(">>>>> reqLnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizOpenRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault(this.projEnvParamProperties);
			resLnsJsonNode.put("/__head_data", "typeCode", "06100010");
			
			resLnsJsonNode.put("/__body_data", "openDataTime", StringTools.getYYYYMMDDHHMMSS());
			resLnsJsonNode.put("/__body_data", "fileCheckSec", "0030");
			resLnsJsonNode.put("/__body_data", "sendMethod", "B");
			resLnsJsonNode.put("/__body_data", "encYn", "0");
			resLnsJsonNode.put("/__body_data", "seqTakeYn", "N");
			
			log.info(">>>>> reslnsJsonNode = {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizCloseReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault(this.projEnvParamProperties);
			resLnsJsonNode.put("/__head_data", "typeCode", "06000040");
			
			resLnsJsonNode.put("/__body_data", "closeDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> reslnsJsonNode = {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizCloseRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault(this.projEnvParamProperties);
			resLnsJsonNode.put("/__head_data", "typeCode", "06100040");
			
			resLnsJsonNode.put("/__body_data", "closeDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> reslnsJsonNode = {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizCloseResError(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault(this.projEnvParamProperties);
			resLnsJsonNode.put("/__head_data", "typeCode", "06100040");
			resLnsJsonNode.put("/__head_data", "resCode", "999");
			
			resLnsJsonNode.put("/__body_data", "closeDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> reslnsJsonNode = {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
}
