package org.tain.task.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.httpClient.LnsHttpClient;
import org.tain.mapper.LnsJsonNode;
import org.tain.object.lns.LnsStream;
import org.tain.properties.ProjEnvUrlProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApisProcess {

	@Autowired
	private ProjEnvUrlProperties projEnvUrlProperties;
	
	@Autowired
	private LnsHttpClient lnsHttpClient;
	
	public LnsStream process(LnsStream reqLnsStream) throws Exception {
		log.info("KANG-20200908 >>>>> {}", CurrentInfo.get());
		
		/*
		//String strReqJson = null;
		if (Flag.flag) {
			// 1. Stream to Json
			LnsJsonNode lnsJsonNode = new LnsJsonNode();
			lnsJsonNode.put("httpUrl", "http://localhost:18087/v1.1/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("reqResType", reqLnsStream.getTypeCode());
			lnsJsonNode.put("stream", reqLnsStream.getData());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-1 >>>>> lnsJsonNode.s2j = {}", lnsJsonNode.toPrettyString());
			
			strReqJson = lnsJsonNode.getText("json");
		}
		*/
		String reqResType = null;
		JsonNode reqJsonNode = null;
		if (Flag.flag) {
			// 1. Stream to Json
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/s2j");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", reqLnsStream.getTypeCode());
			lnsJsonNode.put("/request", "stream", reqLnsStream.getData());
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			
			reqResType = lnsJsonNode.getText("/request", "reqResType");
			reqJsonNode = lnsJsonNode.getJsonNode("/response", "json");
			log.info("ONLINE-1 >>>>> after MAPPER.s2j {} = \n[{}]", reqResType, reqJsonNode.toPrettyString());
		}
		
		/*
		//String strResJson = null;
		//String reqResType = null;
		if (Flag.flag) {
			// 2. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode();
			lnsJsonNode.put("httpUrl", "http://localhost:18082/v1.1/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("reqResType", reqLnsStream.getTypeCode());
			lnsJsonNode.put("reqJson", strReqJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-2 >>>>> lnsJsonNode.link = {}", lnsJsonNode.toPrettyString());
			
			strResJson = lnsJsonNode.getText("resJson");
			reqResType = lnsJsonNode.getText("reqResType");
		}
		*/
		JsonNode resJsonNode = null;
		if (Flag.flag) {
			// 2. link
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getLink() + "/link/process");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", reqLnsStream.getTypeCode());
			lnsJsonNode.put("/request", "json", reqJsonNode);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			
			resJsonNode = lnsJsonNode.getJsonNode("/response", "response");
			reqResType = resJsonNode.at("/__head_data").get("reqres").asText() + resJsonNode.at("/__head_data").get("type").asText();
			log.info("ONLINE-2 >>>>> after LINK.process {} = \n{}", reqResType, resJsonNode.toPrettyString());
			//strResJson = lnsJsonNode.getJsonNode("/response", "response").toPrettyString();
			//log.info("ONLINE-2.2 >>>>> LINK_PROCESS lnsJsonNode.link {}    __body_data = \n{}", lnsJsonNode.getText("/request", "reqResType"), lnsJsonNode.getJsonNode("/response/response", "__body_data").toPrettyString());
		}
		
		/*
		if (Flag.flag) {
			// 3. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode();
			lnsJsonNode.put("httpUrl", "http://localhost:18087/v1.1/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("reqResType", reqResType);
			lnsJsonNode.put("json", strResJson);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			log.info("ONLINE-3 >>>>> lnsJsonNode.j2s = {}", lnsJsonNode.toPrettyString());
			
			// 4. get stream
			strResStream = lnsJsonNode.getText("stream");
			log.info("ONLINE-4 >>>>> lnsJsonNode.ResStream = [{}]", strResStream);
		}
		*/
		
		String strResStream = null;
		if (Flag.flag) {
			// 2. Json to Stream
			LnsJsonNode lnsJsonNode = new LnsJsonNode("{\"request\":{},\"response\":{}}");
			lnsJsonNode.put("httpUrl", this.projEnvUrlProperties.getMapper() + "/mapper/j2s");
			lnsJsonNode.put("httpMethod", "POST");
			lnsJsonNode.put("/request", "reqResType", reqResType);
			lnsJsonNode.put("/request", "json", resJsonNode);
			lnsJsonNode = this.lnsHttpClient.post(lnsJsonNode);
			
			strResStream = lnsJsonNode.getText("/response", "stream");
			log.info("ONLINE-3 >>>>> after MAPPER.j2s {} = \n[{}]", reqResType, strResStream);
		}
		
		LnsStream resLnsStream = null;
		if (Flag.flag) {
			// 5. lnsStream
			resLnsStream = new LnsStream(strResStream);
			log.info("ONLINE-4 >>>>> resLnsStream = {}", JsonPrint.getInstance().toPrettyJson(resLnsStream));
		}
		
		return resLnsStream;
	}
}
