package org.tain.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsJsonToStream;
import org.tain.mapper.LnsMstInfo;
import org.tain.mapper.LnsStreamToJson;
import org.tain.object.lns.LnsStream;
import org.tain.object.ticket.LnsSocketTicket;
import org.tain.task.MapperReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.StringTools;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuBiz {

	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendBizOpenReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "06000010");
				
				lnsJsonNode.put("/__body_data", "openDataTime", StringTools.getYYYYMMDDHHMMSS());
				lnsJsonNode.put("/__body_data", "fileCheckSec", "0030");
				lnsJsonNode.put("/__body_data", "sendMethod", "B");
				lnsJsonNode.put("/__body_data", "encYn", "0");
				lnsJsonNode.put("/__body_data", "seqTakeYn", "N");
				
				log.info(">>>>> SEND.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
			}
			
			String strStream = null;
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("/__head_data", "typeCode"));
				strStream = new LnsJsonToStream(lnsMstInfo, lnsJsonNode.get()).get();
				
				log.info(">>>>> SEND.strStream: [{}]", strStream);
			}
			
			if (Flag.flag) {
				LnsStream lnsStream = new LnsStream(strStream);
				lnsSocketTicket.sendStream(lnsStream);
				log.info(">>>>> SEND.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
			}
		}
	}
	
	public void recvBizOpenReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String strStream = null;
			if (Flag.flag) {
				LnsStream lnsStream = lnsSocketTicket.recvStream();
				log.info(">>>>> RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
				strStream = lnsStream.getData();
				log.info(">>>>> RECV.strStream = {}", strStream);
			}
			
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(strStream.substring(42, 50));
				JsonNode jsonNode = new LnsStreamToJson(lnsMstInfo, strStream).get();
				log.info(">>>>> RECV.jsonNode = {}", jsonNode.toPrettyString());
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendBizOpenRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "06100010");
				
				lnsJsonNode.put("/__body_data", "openDataTime", StringTools.getYYYYMMDDHHMMSS());
				lnsJsonNode.put("/__body_data", "fileCheckSec", "0030");
				lnsJsonNode.put("/__body_data", "sendMethod", "B");
				lnsJsonNode.put("/__body_data", "encYn", "0");
				lnsJsonNode.put("/__body_data", "seqTakeYn", "N");
				
				log.info(">>>>> SEND.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
			}
			
			String strStream = null;
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("/__head_data", "typeCode"));
				strStream = new LnsJsonToStream(lnsMstInfo, lnsJsonNode.get()).get();
				
				log.info(">>>>> SEND.strStream: [{}]", strStream);
			}
			
			if (Flag.flag) {
				LnsStream lnsStream = new LnsStream(strStream);
				lnsSocketTicket.sendStream(lnsStream);
				log.info(">>>>> SEND.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
			}
		}
	}
	
	public void recvBizOpenRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String strStream = null;
			if (Flag.flag) {
				LnsStream lnsStream = lnsSocketTicket.recvStream();
				log.info(">>>>> RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
				strStream = lnsStream.getData();
				log.info(">>>>> RECV.strStream = {}", strStream);
			}
			
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(strStream.substring(42, 50));
				JsonNode jsonNode = new LnsStreamToJson(lnsMstInfo, strStream).get();
				log.info(">>>>> RECV.jsonNode = {}", jsonNode.toPrettyString());
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendBizCloseReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "06000040");
				
				lnsJsonNode.put("/__body_data", "closeDateTime", StringTools.getYYYYMMDDHHMMSS());
				
				log.info(">>>>> SEND.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
			}
			
			String strStream = null;
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("/__head_data", "typeCode"));
				strStream = new LnsJsonToStream(lnsMstInfo, lnsJsonNode.get()).get();
				
				log.info(">>>>> SEND.strStream: [{}]", strStream);
			}
			
			if (Flag.flag) {
				LnsStream lnsStream = new LnsStream(strStream);
				lnsSocketTicket.sendStream(lnsStream);
				log.info(">>>>> SEND.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
			}
		}
	}
	
	public void recvBizCloseReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String strStream = null;
			if (Flag.flag) {
				LnsStream lnsStream = lnsSocketTicket.recvStream();
				log.info(">>>>> RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
				strStream = lnsStream.getData();
				log.info(">>>>> RECV.strStream = {}", strStream);
			}
			
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(strStream.substring(42, 50));
				JsonNode jsonNode = new LnsStreamToJson(lnsMstInfo, strStream).get();
				log.info(">>>>> RECV.jsonNode = {}", jsonNode.toPrettyString());
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendBizCloseRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "06100040");
				
				lnsJsonNode.put("/__body_data", "closeDateTime", StringTools.getYYYYMMDDHHMMSS());
				
				log.info(">>>>> SEND.lnsJsonNode: {}", lnsJsonNode.toPrettyString());
			}
			
			String strStream = null;
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("/__head_data", "typeCode"));
				strStream = new LnsJsonToStream(lnsMstInfo, lnsJsonNode.get()).get();
				
				log.info(">>>>> SEND.strStream: [{}]", strStream);
			}
			
			if (Flag.flag) {
				LnsStream lnsStream = new LnsStream(strStream);
				lnsSocketTicket.sendStream(lnsStream);
				log.info(">>>>> SEND.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
			}
		}
	}
	
	public void recvBizCloseRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String strStream = null;
			if (Flag.flag) {
				LnsStream lnsStream = lnsSocketTicket.recvStream();
				log.info(">>>>> RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
				strStream = lnsStream.getData();
				log.info(">>>>> RECV.strStream = {}", strStream);
			}
			
			if (Flag.flag) {
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(strStream.substring(42, 50));
				JsonNode jsonNode = new LnsStreamToJson(lnsMstInfo, strStream).get();
				log.info(">>>>> RECV.jsonNode = {}", jsonNode.toPrettyString());
			}
		}
	}
}
