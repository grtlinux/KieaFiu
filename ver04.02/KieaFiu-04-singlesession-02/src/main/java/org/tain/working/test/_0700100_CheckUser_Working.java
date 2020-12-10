package org.tain.working.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.httpClient.LnsHttpClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class _0700100_CheckUser_Working {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	public void test01() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String strReqStream = "0116070010099999920201016125525125525                                                               "
				+ "701225-1234567      ";
		
		String strResStream = "0181071010099999920201016125525125525                                                               "
				+ "7012251234567       true falsefalse                    1001088   05012345678900      ";
		
		String strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0700\",\n" + 
				"    \"type\" : \"100\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"id_number\" : \"701225-1234567\"\n" + 
				"  }\n" + 
				"}";
		
		String strResJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0181\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0710\",\n" + 
				"    \"type\" : \"100\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"id_number\" : \"7012251234567\",\n" + 
				"    \"sign_status\" : true,\n" + 
				"    \"sentbe_exist_status\" : false,\n" + 
				"    \"remittable_status\" : false,\n" + 
				"    \"user_id\" : \"1001088\",\n" + 
				"    \"account_number\" : \"05012345678900\"\n" + 
				"  }\n" + 
				"}";
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0700100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. resCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0710100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "cstruct"));
		}
		
		if (Flag.flag) {
			// 3. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0700100");
			lnsJsonNode.put("/reqJson", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "json"));
		}
		
		if (Flag.flag) {
			// 4. Stream to Json of strResStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0710100");
			lnsJsonNode.put("/reqJson", "stream", strResStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-4 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "json"));
		}
		
		if (Flag.flag) {
			// 5. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0700100");
			lnsJsonNode.put("/reqJson", "json", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "stream"));
		}
		
		if (Flag.flag) {
			// 6. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0710100");
			lnsJsonNode.put("/reqJson", "json", strResJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "stream"));
		}
		
		if (Flag.flag) {
			// 7. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0700100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-7 >>>>> lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "jsonInfo"));
		}
		
		if (Flag.flag) {
			// 8. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0700100");
			// extHttpUrl, extHttpMethod
			lnsJsonNode.put("/reqJson", "reqJson", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-8 >>>>> lnsJsonNode.link {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "resJson"));
		}
	}
}
