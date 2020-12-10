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
public class _0200100_getFee_Working {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	public void test00() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String strReqStream = "0127020010099999920201016125525125525                                                               "
				+ "KORUSD100.50         THATHB";
		
		String strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0200\",\n" + 
				"    \"type\" : \"100\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"sourceCountry\": \"KOR\",\n" + 
				"    \"sourceSendCurrency\": \"USD\",\n" + 
				"    \"sourceSendAmount\": \"100.50\",\n" + 
				"    \"destinationCountry\": \"THA\",\n" + 
				"    \"destinationReceiveCurrency\": \"THB\"\n" + 
				"  }\n" + 
				"}";
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> CSTRUCT lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> J2S lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
		}
		
		if (Flag.flag) {
			// 3. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode.put("/request", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> S2J lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "json").toPrettyString());
		}
		
		if (Flag.flag) {
			// 4. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-4 >>>>> HEADBASE lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
		}
		
		String strResJson = null;
		String strResStream = null;
		if (Flag.flag) {
			// 5. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> LINK_PROCESS lnsJsonNode.link {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("response").toPrettyString());
			strResJson = lnsJsonNode.getJsonNode("/response", "response").toPrettyString();
			log.info("ONLINE-5.2 >>>>> LINK_PROCESS lnsJsonNode.link {}    __body_data = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response/response", "__body_data").toPrettyString());
		}
		
		if (Flag.flag) {
			// 6. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> HEADBASE lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
		}
		
		if (Flag.flag) {
			// 7. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210100");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strResJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-7.1 >>>>> J2S lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
			strResStream = lnsJsonNode.getText("/response", "stream");
			log.info("ONLINE-7.2 >>>>> strResStream {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), strResStream);
		}
	}
	
	public void test01() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String strReqStream = "0127020010099999920201016125525125525                                                               "
				+ "KORTHB               IDNIDR";
		
		String strResStream = "0690021010099999920201016125525125525                                                               "
				+ "success             OK        "
				+ "cash                13.0000        USD  excluded            Cash                                              "
				+ "MoneyGram 888                                                                                                           "
				+ "1              USD  29.7332        THB  mgi  THA  "
				+ "account_deposit     15.0000        USD  excluded            Account deposit                                   "
				+ "MoneyGram 888                                     72412820            EARTHPORT- THAILAND                               "
				+ "1              USD  30.34          THB  mgi  THA  ";
		
		String strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0200\",\n" + 
				"    \"type\" : \"100\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"sourceCountry\": \"KOR\",\n" + 
				"    \"sourceSendCurrency\": \"USD\",\n" + 
				"    \"sourceSendAmount\": \"100.50\",\n" + 
				"    \"destinationCountry\": \"THA\",\n" + 
				"    \"destinationReceiveCurrency\": \"THB\"\n" + 
				"  }\n" + 
				"}";
		
		String strResJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0181\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0210\",\n" + 
				"    \"type\" : \"100\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"      \"status\" : \"success\",\n" + 
				"      \"message\" : \"OK\",\n" + 
				"      \"data\" : [ {\n" + 
				"        \"deliveryMethod\" : \"cash\",\n" + 
				"        \"fee\" : {\n" + 
				"          \"amount\" : \"13.0000\",\n" + 
				"          \"currency\" : \"USD\",\n" + 
				"          \"model\" : \"excluded\"\n" + 
				"        },\n" + 
				"        \"metadata\" : {\n" + 
				"          \"deliveryMethodDisplayName\" : \"Cash\",\n" + 
				"          \"destinationOperatorName\" : \"MoneyGram 888\"\n" + 
				"        },\n" + 
				"        \"rate\" : {\n" + 
				"          \"from\" : {\n" + 
				"            \"amount\" : \"1\",\n" + 
				"            \"currency\" : \"USD\"\n" + 
				"          },\n" + 
				"          \"to\" : {\n" + 
				"            \"amount\" : \"29.7332\",\n" + 
				"            \"currency\" : \"THB\"\n" + 
				"          }\n" + 
				"        },\n" + 
				"        \"destination\" : {\n" + 
				"          \"operatorCode\" : \"mgi\",\n" + 
				"          \"country\" : \"THA\"\n" + 
				"        }\n" + 
				"      }, {\n" + 
				"        \"deliveryMethod\" : \"account_deposit\",\n" + 
				"        \"fee\" : {\n" + 
				"          \"amount\" : \"15.0000\",\n" + 
				"          \"currency\" : \"USD\",\n" + 
				"          \"model\" : \"excluded\"\n" + 
				"        },\n" + 
				"        \"metadata\" : {\n" + 
				"          \"deliveryMethodDisplayName\" : \"Account deposit\",\n" + 
				"          \"destinationOperatorName\" : \"MoneyGram 888\",\n" + 
				"          \"receiveAgentId\" : \"72412820\",\n" + 
				"          \"receiveAgentName\" : \"EARTHPORT- THAILAND\"\n" + 
				"        },\n" + 
				"        \"rate\" : {\n" + 
				"          \"from\" : {\n" + 
				"            \"amount\" : \"1\",\n" + 
				"            \"currency\" : \"USD\"\n" + 
				"          },\n" + 
				"          \"to\" : {\n" + 
				"            \"amount\" : \"30.34\",\n" + 
				"            \"currency\" : \"THB\"\n" + 
				"          }\n" + 
				"        },\n" + 
				"        \"destination\" : {\n" + 
				"          \"operatorCode\" : \"mgi\",\n" + 
				"          \"country\" : \"THA\"\n" + 
				"        }\n" + 
				"      } ]\n" + 
				"    }\n" + 
				"}";
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. resCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 3. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
		}
		
		if (Flag.flag) {
			// 4. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210100");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strResJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
		}
		
		if (Flag.flag) {
			// 5. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode.put("/request", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "json").toPrettyString());
		}
		
		if (Flag.flag) {
			// 6. Stream to Json of strResStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210100");
			lnsJsonNode.put("/request", "stream", strResStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-4 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "json").toPrettyString());
		}
		
		if (Flag.flag) {
			// 7. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-7 >>>>> lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
		}
		
		if (Flag.flag) {
			// 8-1. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-8-1 >>>>> lnsJsonNode.link {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("response").toPrettyString());
			
			// 8-2. Json to Stream
			LnsJsonNode lnsJsonNode2 = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode2.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode2.put("httpMethod", "POST");
			lnsJsonNode2.put("/request", "reqResType", "0210100");
			lnsJsonNode2.put("/request", "json", lnsJsonNode.getJsonNode("/response", "response"));
			lnsJsonNode2 = this.lnsHttpClient.post(lnsJsonNode2);
			log.info("ONLINE-8-2 >>>>> lnsJsonNode2.j2s {} = \n[{}]", lnsJsonNode2.getText("/request", "reqResType"), lnsJsonNode2.getText("/response", "stream"));
		}
	}
	
	public void test02CStruct() throws Exception {
		log.info("KANG-202012003 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> CSTRUCT lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210100");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> CSTRUCT lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
	}
}
