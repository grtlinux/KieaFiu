package org.tain.httpClient;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.RestTemplateConfig;
import org.tain.utils.enums.RestTemplateType;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LnsHttpClient {

	public LnsJsonNode get(LnsJsonNode lnsJsonNode) throws Exception {
		
		log.info("=========================== LnsHttpClient.get =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> GET.REQ.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.GET;
			
			LnsJsonNode reqJsonNode = new LnsJsonNode(lnsJsonNode.getJsonNode("request"));
			log.info(">>>>> GET.REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			
			Map<String,String> reqMap = new ObjectMapper().readValue(reqJsonNode.toPrettyString(), new TypeReference<Map<String,String>>(){});
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.setAll(reqMap);
			
			UriComponents builder = UriComponentsBuilder.fromHttpUrl(httpUrl)
					.queryParams(map)
					.build(true);
			httpUrl = builder.toString();
			log.info(">>>>> GET.REQ.httpUrl = {}", httpUrl);
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			log.info(">>>>> GET.REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(reqHeaders);
			log.info(">>>>> GET.REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> response = null;
			try {
				response = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> GET.RES.getStatusCodeValue() = {}", response.getStatusCodeValue());
				log.info(">>>>> GET.RES.getStatusCode()      = {}", response.getStatusCode());
				log.info(">>>>> GET.RES.getBody()            = {}", response.getBody());
				
				LnsJsonNode resJsonNode = new LnsJsonNode(response.getBody());
				lnsJsonNode.put("response", resJsonNode.toPrettyString());
				log.info(">>>>> GET.RES-1.lnsJsonNode = {}", lnsJsonNode.toPrettyString());
				
				lnsJsonNode.put("code", "00000");
				lnsJsonNode.put("status", "success");
				lnsJsonNode.put("message", "OK");
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJsonNode.put("code", "99999");
				lnsJsonNode.put("status", "fail");
				lnsJsonNode.put("message", message.substring(pos1 + 1, pos2));
			}
		}
		
		if (Flag.flag) {
			log.info(">>>>> GET.RES-2.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		log.info("--------------------------- LnsHttpClient.get -----------------------------");
		
		return lnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode post(LnsJsonNode lnsJsonNode) throws Exception {
		
		log.info("=========================== LnsHttpClient.post =============================");
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info(">>>>> POST.REQ.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		if (Flag.flag) {
			String httpUrl = lnsJsonNode.getText("httpUrl");
			HttpMethod httpMethod = HttpMethod.POST;
			
			LnsJsonNode request = null;
			if (!Flag.flag && lnsJsonNode.getJsonNode("request") == null) {
				//reqJsonNode = new LnsJsonNode(lnsJsonNode.get());
				//log.info(">>>>> POST.REQ.reqJsonNode    = {}", reqJsonNode.toPrettyString());
			} else {
				request = new LnsJsonNode(lnsJsonNode.getJsonNode("request"));
				log.info(">>>>> POST.REQ.request    = {}", request.toPrettyString());
			}
			
			HttpHeaders reqHeaders = new HttpHeaders();
			reqHeaders.setContentType(MediaType.APPLICATION_JSON);
			log.info(">>>>> POST.REQ.reqHeaders     = {}", reqHeaders);
			
			HttpEntity<String> reqHttpEntity = new HttpEntity<>(request.toPrettyString(), reqHeaders);
			log.info(">>>>> POST.REQ.reqHttpEntity  = {}", reqHttpEntity);
			
			ResponseEntity<String> responseEntity = null;
			try {
				responseEntity = RestTemplateConfig.get(RestTemplateType.SETENV).exchange(
						httpUrl
						, httpMethod
						, reqHttpEntity
						, String.class);
				
				log.info(">>>>> POST.RES.getStatusCodeValue() = {}", responseEntity.getStatusCodeValue());
				log.info(">>>>> POST.RES.getStatusCode()      = {}", responseEntity.getStatusCode());
				log.info(">>>>> POST.RES.getBody()            = {}", responseEntity.getBody());
				
				LnsJsonNode response = new LnsJsonNode(responseEntity.getBody());
				lnsJsonNode.put("response", response.get());
				
				log.info(">>>>> POST.RES-1.lnsJsonNode = {}", lnsJsonNode.toPrettyString());
				
				lnsJsonNode.put("code", "00000");
				lnsJsonNode.put("status", "success");
				lnsJsonNode.put("message", "OK");
			} catch (Exception e) {
				//e.printStackTrace();
				String message = e.getMessage();
				log.error("ERROR >>>>> {}", message);
				int pos1 = message.indexOf('[');
				int pos2 = message.lastIndexOf(']');
				lnsJsonNode.put("code", "99999");
				lnsJsonNode.put("status", "fail");
				lnsJsonNode.put("message", message.substring(pos1 + 1, pos2));
			}
		}
		
		if (Flag.flag) {
			log.info(">>>>> POST.RES-2.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
		}
		
		log.info("--------------------------- LnsHttpClient.post -----------------------------");
		
		return lnsJsonNode;
	}
}
