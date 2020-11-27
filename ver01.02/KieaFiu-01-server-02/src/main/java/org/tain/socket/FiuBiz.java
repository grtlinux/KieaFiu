package org.tain.socket;

import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuBiz {

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizOpenRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			if (Flag.flag) {
				resLnsJsonNode = FiuTools.getDefault();
				resLnsJsonNode.put("/__head_data", "typeCode", "06100010");
				
				resLnsJsonNode.put("/__body_data", "openDataTime", StringTools.getYYYYMMDDHHMMSS());
				resLnsJsonNode.put("/__body_data", "fileCheckSec", "0030");
				resLnsJsonNode.put("/__body_data", "sendMethod", "B");
				resLnsJsonNode.put("/__body_data", "encYn", "0");
				resLnsJsonNode.put("/__body_data", "seqTakeYn", "N");
				
				log.info(">>>>> reslnsJsonNode = {}", resLnsJsonNode.toPrettyString());
			}
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getBizCloseRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			if (Flag.flag) {
				resLnsJsonNode = FiuTools.getDefault();
				resLnsJsonNode.put("/__head_data", "typeCode", "06100040");
				
				resLnsJsonNode.put("/__body_data", "closeDateTime", StringTools.getYYYYMMDDHHMMSS());
				
				log.info(">>>>> reslnsJsonNode = {}", resLnsJsonNode.toPrettyString());
			}
		}
		
		return resLnsJsonNode;
	}
}
