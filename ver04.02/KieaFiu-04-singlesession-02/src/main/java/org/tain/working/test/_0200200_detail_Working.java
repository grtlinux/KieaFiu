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
public class _0200200_detail_Working {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	public void test00() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String strReqStream = "0116020020099999920201016125525125525                                                               "
				+ "KORTHBIDN1000      IDRmgi  cash      ";
		
		String strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0200\",\n" + 
				"    \"type\" : \"200\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"sourceCountry\" : \"KOR\",\n" + 
				"    \"sourceSendCurrency\" : \"USD\",\n" + 
				"    \"destinationCountry\" : \"IDN\",\n" + 
				"    \"destinationReceiveAmount\" : \"1000\",\n" + 
				"    \"destinationReceiveCurrency\" : \"IDR\",\n" + 
				"    \"destinationOperatorCode\" : \"mgi\",\n" + 
				"    \"deliveryMethod\": \"cash\"\n" + 
				"  }\n" + 
				"}";
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200200");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200200");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
		}
		
		if (Flag.flag) {
			// 3. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200200");
			lnsJsonNode.put("/request", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "json").toPrettyString());
		}
		
		if (Flag.flag) {
			// 4. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200200");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-4 >>>>> lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
		}
		
		String strResJson = null;
		String strResStream = null;
		if (Flag.flag) {
			// 5. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200200");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> LINK_PROCESS lnsJsonNode.link {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("response").toPrettyString());
			strResJson = lnsJsonNode.getJsonNode("/response", "response").toPrettyString();
		}
		
		if (!Flag.flag) {
			// TODO: after info-json
			// 6. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210200");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> HEADBASE lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
		}
		
		if (!Flag.flag) {
			// TODO: after info-json
			// 7. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210200");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strResJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-7.1 >>>>> J2S lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
			strResStream = lnsJsonNode.getText("/response", "stream");
			log.info("ONLINE-7.2 >>>>> strResStream {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), strResStream);
		}
	}
	
	@Deprecated
	public void test01() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String strReqStream = "0116020020099999920201016125525125525                                                               "
				+ "701225-1234567      ";
		
		String strResStream = "0181021020099999920201016125525125525                                                               "
				+ "7012251234567       true falsefalse                    1001088   05012345678900      ";
		
		String strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0200\",\n" + 
				"    \"type\" : \"200\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"sourceCountry\" : \"KOR\",\n" + 
				"    \"sourceSendCurrency\" : \"THB\",\n" + 
				"    \"destinationCountry\" : \"IDN\",\n" + 
				"    \"destinationReceiveAmount\" : \"1000\",\n" + 
				"    \"destinationReceiveCurrency\" : \"IDR\",\n" + 
				"    \"destinationOperatorCode\" : \"mgi\",\n" + 
				"    \"deliveryMethod\": \"cash\"\n" + 
				"  }\n" + 
				"}";
		
		String strResJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0181\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0210\",\n" + 
				"    \"type\" : \"200\",\n" + 
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
			lnsJsonNode.put("/reqJson", "reqResType", "0200200");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. resCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0210200");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "cstruct"));
		}
		
		if (Flag.flag) {
			// 3. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200200");
			lnsJsonNode.put("/reqJson", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "json"));
		}
		
		if (Flag.flag) {
			// 4. Stream to Json of strResStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0210200");
			lnsJsonNode.put("/reqJson", "stream", strResStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-4 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "json"));
		}
		
		if (Flag.flag) {
			// 5. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200200");
			lnsJsonNode.put("/reqJson", "json", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "stream"));
		}
		
		if (Flag.flag) {
			// 6. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0210200");
			lnsJsonNode.put("/reqJson", "json", strResJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "stream"));
		}
		
		if (Flag.flag) {
			// 7. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200200");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-7 >>>>> lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "jsonInfo"));
		}
		
		if (Flag.flag) {
			// 8. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200200");
			// extHttpUrl, extHttpMethod
			lnsJsonNode.put("/reqJson", "reqJson", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-8 >>>>> lnsJsonNode.link {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "resJson"));
		}
	}
}
