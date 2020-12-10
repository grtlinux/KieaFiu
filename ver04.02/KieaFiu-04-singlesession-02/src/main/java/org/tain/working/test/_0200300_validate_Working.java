package org.tain.working.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.data.LnsData;
import org.tain.httpClient.LnsHttpClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class _0200300_validate_Working {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	@Autowired
	private LnsData lnsData;
	
	public void test00() throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		String strReqStream = "0814020030099999920201016125525125525                                                               "
				+ "true test                70641266  320344321323        receiverFirstName   receiverLastName    Bangkok             "
				+ "MG-0012345          THA  true true abc@lightnet.io                                   345364566      33   MG-0012345          "
				+ "002       account_deposit     204515304846        Sender              Man                 Bangkok             10400               "
				+ "Temp address        GOV                 PURCHASE_GOODS      THA  true true true true 45645645666         66 abc@lightnet.io     "
				+ "MYSP83245384           1992-03-23          BUSINESS_PARTNER    senderMiddleName    THAADMIN               IDNIDRmgi"
				+ "This is MGI test remark                           KOR10.00          USD1502293110098058              ";
		
		String strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0200\",\n" + 
				"    \"type\" : \"300\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"saveReport\": true,\n" + 
				"    \"terminalName\": \"test\",\n" + 
				"    \"receiver\": {\n" + 
				"        \"agentId\": \"70641266\",\n" + 
				"        \"accountId\": \"320344321323\",\n" + 
				"        \"firstName\": \"receiverFirstName\",\n" + 
				"        \"lastName\": \"receiverLastName\",\n" + 
				"        \"address\": {\n" + 
				"            \"city\": \"Bangkok\",\n" + 
				"            \"line1\": \"MG-0012345\",\n" + 
				"            \"countryCode\": \"THA\"\n" + 
				"        },\n" + 
				"        \"notification\": {\n" + 
				"            \"transactionEmailOptIn\": true,\n" + 
				"            \"transactionSMSOptIn\": true\n" + 
				"        },\n" + 
				"        \"email\": \"abc@lightnet.io\",\n" + 
				"        \"phone\": {\n" + 
				"            \"number\": \"345364566\",\n" + 
				"            \"countryCode\": \"33\"\n" + 
				"        },\n" + 
				"        \"accountNumber\": \"MG-0012345\",\n" + 
				"        \"extraFields\": {\n" + 
				"            \"CIMBBANKNAME\": \"002\"\n" + 
				"        }\n" + 
				"    },\n" + 
				//"    \"deliveryMethod\": \"account_deposit\",\n" + // received 
				"    \"deliveryMethod\": \"cash\",\n" + 
				"    \"sender\": {\n" + 
				"        \"cardNumber\": \"204515304846\",\n" + 
				"        \"firstName\": \"Sender\",\n" + 
				"        \"lastName\": \"Man\",\n" + 
				"        \"address\": {\n" + 
				"            \"city\": \"Bangkok\",\n" + 
				"            \"postalCode\": \"10400\",\n" + 
				"            \"line1\": \"Temp address\"\n" + 
				"        },\n" + 
				"        \"idType\": \"GOV\",\n" + 
				"        \"purpose\": \"PURCHASE_GOODS\",\n" + 
				"        \"birthCountryCode\": \"THA\",\n" + 
				"        \"notification\": {\n" + 
				"            \"transactionEmailOptIn\": true,\n" + 
				"            \"transactionSMSOptIn\": true,\n" + 
				"            \"marketingEmailOptIn\": true,\n" + 
				"            \"marketingSMSOptIn\": true\n" + 
				"        },\n" + 
				"        \"homePhone\": {\n" + 
				"            \"number\": \"45645645666\",\n" + 
				"            \"countryCode\": \"66\"\n" + 
				"        },\n" + 
				"        \"email\": \"abc@lightnet.io\",\n" + 
				"        \"idCountryCode\": \"MYS\",\n" + 
				"        \"idNumber\": \"P83245384\",\n" + 
				"        \"dob\": \"1992-03-23\",\n" + 
				"        \"relationshipToReceiver\": \"BUSINESS_PARTNER\",\n" + 
				"        \"middleName\": \"senderMiddleName\",\n" + 
				"        \"citizenshipCountryCode\": \"THA\",\n" + 
				"        \"occupation\": \"ADMIN\"\n" + 
				"    },\n" + 
				"    \"destination\": {\n" + 
				"        \"country\": \"IDN\",\n" + 
				"        \"receive\": {\n" + 
				"            \"currency\": \"IDR\"\n" + 
				"        },\n" + 
				"        \"operatorCode\": \"mgi\"\n" + 
				"    },\n" + 
				"    \"remark\": \"This is MGI test remark\",\n" + 
				"    \"source\": {\n" + 
				"        \"country\": \"KOR\",\n" + 
				"        \"send\": {\n" + 
				"            \"amount\":\"10.00\",\n" + 
				"            \"currency\": \"USD\"\n" + 
				"        },\n" + 
				//"        \"transactionId\": \"1502293110098058\"\n" + 
				"        \"transactionId\": \"1502293110098100\"\n" + 
				"    }\n" + 
				"  }\n" + 
				"}";
		
		strReqJson = "{\n" + 
				"  \"__head_data\" : {\n" + 
				//"    \"length\" : \"0116\",\n" + 
				"    \"length\" : \"0000\",\n" + 
				"    \"reqres\" : \"0200\",\n" + 
				"    \"type\" : \"300\",\n" + 
				"    \"trNo\" : \"999999\",\n" + 
				"    \"reqDate\" : \"20201016\",\n" + 
				"    \"reqTime\" : \"125525\",\n" + 
				"    \"resTime\" : \"125525\"\n" + 
				"  },\n" + 
				"  \"__body_data\" : {\n" + 
				"    \"receiver\": {\n" + 
				"        \"firstName\": \"receiverFirstName\",\n" + 
				"        \"lastName\": \"receiverLastName\",\n" + 
				"        \"notification\": {\n" + 
				"            \"transactionSMSOptIn\": false,\n" + 
				"            \"transactionEmailOptIn\": false\n" + 
				"        },\n" + 
				"        \"address\": {\n" + 
				"            \"city\": \"Bangkok\",\n" + 
				"            \"line1\": \"MG-0012345\"\n" + 
				"        },\n" + 
				"        \"phone\": {\n" + 
				"            \"number\": \"345364566\",\n" + 
				"            \"countryCode\": \"33\"\n" + 
				"        },\n" + 
				"        \"accountNumber\": \"MG-0012345\",\n" + 
				"        \"email\": \"receiverEmail@test.com\"\n" + 
				"    },\n" + 
				"    \"deliveryMethod\": \"cash\",\n" +   // important account_deposit
				"    \"sender\": {\n" + 
				"        \"lastName\": \"senderLastNamex\",\n" + 
				"        \"secondLastName\": \"senderSecondLastName\",\n" + 
				"        \"address\": {\n" + 
				"            \"city\": \"Bangkok\",\n" + 
				"            \"postalCode\": \"10400\",\n" + 
				"            \"line1\": \"Temp address\"\n" + 
				"        },\n" + 
				"        \"idType\": \"GOV\",\n" + 
				"        \"purpose\": \"PURCHASE_GOODS\",\n" + 
				"        \"birthCountryCode\": \"THA\",\n" + 
				"        \"homePhone\": {\n" + 
				"            \"number\": \"45645645666\",\n" + 
				"            \"countryCode\": \"66\"\n" + 
				"        },\n" + 
				"        \"idCountryCode\": \"MYS\",\n" + 
				"        \"idNumber\": \"P83245384\",\n" + 
				"        \"firstName\": \"IQLZSO\",\n" + 
				"        \"notification\": {\n" + 
				"            \"marketingSMSOptIn\": false,\n" + 
				"            \"transactionSMSOptIn\": false,\n" + 
				"            \"transactionEmailOptIn\": false,\n" + 
				"            \"marketingEmailOptIn\": false\n" + 
				"        },\n" + 
				"        \"dob\": \"1992-03-23\",\n" + 
				"        \"relationshipToReceiver\": \"BUSINESS_PARTNER\",\n" + 
				"        \"middleName\": \"senderMiddleName\",\n" + 
				"        \"citizenshipCountryCode\": \"THA\",\n" + 
				"        \"email\": \"senderEmail@test.com\",\n" + 
				"        \"occupation\" : \"ADMIN\"\n" + 
				"    },\n" + 
				"    \"destination\": {\n" + 
				"        \"country\": \"IDN\",\n" + 
				"        \"receive\": {\n" + 
				"            \"amount\": \"43366.3500\",\n" + 
				"            \"currency\": \"IDR\"\n" + 
				"        },\n" + 
				"        \"operatorCode\": \"mgi\"\n" + 
				"    },\n" + 
				"    \"promotionCodes\": [\n" + 
				"        \"FLATFEE\"\n" + 
				"    ],\n" + 
				"    \"remark\": \"This is MGI test remark\",\n" + 
				"    \"source\": {\n" + 
				"        \"country\": \"KOR\",\n" + 
				"        \"send\": {\n" + 
				"            \"currency\": \"USD\"\n" + 
				"        },\n" + 
				"        \"transactionId\": \"4324682635157307\"\n" + 
				"    },\n" + 
				"    \"terminalName\": \"ter-001\"\n" + 
				"  }\n" + 
				"}";
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> CSTRUCT lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200300");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> J2S lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "stream"));
		}
		
		if (Flag.flag) {
			// 3. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200300");
			lnsJsonNode.put("/request", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> S2J lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "json").toPrettyString());
		}
		
		if (Flag.flag) {
			// 4. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200300");
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
			lnsJsonNode.put("/request", "reqResType", "0200300");
			lnsJsonNode.put("/request", "json", new LnsJsonNode(strReqJson).get());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> LINK_PROCESS lnsJsonNode.link {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("response").toPrettyString());
			strResJson = lnsJsonNode.getJsonNode("/response", "response").toPrettyString();
			log.info("ONLINE-5.2 >>>>> LINK_PROCESS lnsJsonNode.link {}    __body_data = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response/response", "__body_data").toPrettyString());
			lnsData.setLnsTransactionId(lnsJsonNode.getText("/response/response/__body_data/data", "transactionId"));
		}
		
		if (!Flag.flag) {
			// 6. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> HEADBASE lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response", "jsonInfo").toPrettyString());
		}
		
		if (!Flag.flag) {
			// 7. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210300");
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
		
		String strReqStream = "0116020030099999920201016125525125525                                                               "
				+ "701225-1234567      ";
		
		String strResStream = "0181021030099999920201016125525125525                                                               "
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
			lnsJsonNode.put("/reqJson", "reqResType", "0200300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "cstruct"));
		}
		
		if (Flag.flag) {
			// 2. resCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0210300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "cstruct"));
		}
		
		if (Flag.flag) {
			// 3. Stream to Json of strReqStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200300");
			lnsJsonNode.put("/reqJson", "stream", strReqStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "json"));
		}
		
		if (Flag.flag) {
			// 4. Stream to Json of strResStream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0210300");
			lnsJsonNode.put("/reqJson", "stream", strResStream);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-4 >>>>> lnsJsonNode.s2j {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "json"));
		}
		
		if (Flag.flag) {
			// 5. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200300");
			lnsJsonNode.put("/reqJson", "json", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-5 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "stream"));
		}
		
		if (Flag.flag) {
			// 6. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0210300");
			lnsJsonNode.put("/reqJson", "json", strResJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-6 >>>>> lnsJsonNode.j2s {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "stream"));
		}
		
		if (Flag.flag) {
			// 7. Info of Head Base
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/info/headbase");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-7 >>>>> lnsJsonNode.j2s {} = \n{}", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "jsonInfo"));
		}
		
		if (Flag.flag) {
			// 8. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"reqJson\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/reqJson", "reqResType", "0200300");
			// extHttpUrl, extHttpMethod
			lnsJsonNode.put("/reqJson", "reqJson", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-8 >>>>> lnsJsonNode.link {} = \n[{}]", lnsJsonNode.getText("/resJson", "reqResType"), lnsJsonNode.getText("/resJson", "resJson"));
		}
	}
	
	public void test02CStruct() throws Exception {
		log.info("KANG-202012003 >>>>> {}", CurrentInfo.get());
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0200300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> CSTRUCT lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
		
		if (Flag.flag) {
			// 1. reqCStruct
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/cstruct");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", "0210300");
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> CSTRUCT lnsJsonNode.cstruct {} = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getText("/response", "cstruct"));
		}
	}
}
