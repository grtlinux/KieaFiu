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
public class FiuFile {

	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileStartReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03000020");
				
				lnsJsonNode.put("/__body_data", "docCode", "REP002");
				lnsJsonNode.put("/__body_data", "msgCode", "01");
				lnsJsonNode.put("/__body_data", "sendSeq", "00000001");
				lnsJsonNode.put("/__body_data", "docNo", "yyyyMMdd-00000001");
				lnsJsonNode.put("/__body_data", "annDate", "yyyyMMdd");
				lnsJsonNode.put("/__body_data", "befDocNo", "");
				lnsJsonNode.put("/__body_data", "fileName", "CRT-G00001-yyyyMMdd-00000001.env");
				lnsJsonNode.put("/__body_data", "transNo", "0000001/0000001");
				lnsJsonNode.put("/__body_data", "baseDocNo", "yyyyMMdd-00000001");
				lnsJsonNode.put("/__body_data", "orgCode", "G00001");
				lnsJsonNode.put("/__body_data", "recLength", "0001");
				lnsJsonNode.put("/__body_data", "zipYn", "0");
				lnsJsonNode.put("/__body_data", "totLength", "0000008152");
				lnsJsonNode.put("/__body_data", "data", "____DATA______");
				
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
	
	public void recvFileStartReq(LnsSocketTicket lnsSocketTicket) throws Exception {
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
	
	public void sendFileStartRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03100020");
				
				lnsJsonNode.put("/__body_data", "docCode", "REP002");
				lnsJsonNode.put("/__body_data", "msgCode", "01");
				lnsJsonNode.put("/__body_data", "sendSeq", "00000001");
				lnsJsonNode.put("/__body_data", "docNo", "yyyyMMdd-00000001");
				lnsJsonNode.put("/__body_data", "annDate", "yyyyMMdd");
				lnsJsonNode.put("/__body_data", "befDocNo", "");
				lnsJsonNode.put("/__body_data", "fileName", "CRT-G00001-yyyyMMdd-00000001.env");
				lnsJsonNode.put("/__body_data", "transNo", "0000001/0000001");
				lnsJsonNode.put("/__body_data", "baseDocNo", "yyyyMMdd-00000001");
				lnsJsonNode.put("/__body_data", "orgCode", "G00001");
				lnsJsonNode.put("/__body_data", "recLength", "0001");
				lnsJsonNode.put("/__body_data", "zipYn", "0");
				lnsJsonNode.put("/__body_data", "totLength", "0000008152");
				lnsJsonNode.put("/__body_data", "linkYn", "N");
				lnsJsonNode.put("/__body_data", "recvLength", "0000000000");
				
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
	
	public void recvFileStartRes(LnsSocketTicket lnsSocketTicket) throws Exception {
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
	
	public void sendFileData(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03000030");
				
				lnsJsonNode.put("/__body_data", "sequence", "0000003");
				lnsJsonNode.put("/__body_data", "sendLength", "0000008038");
				lnsJsonNode.put("/__body_data", "dataLength", "5430");
				lnsJsonNode.put("/__body_data", "data", "_____DATA_______");
				
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
	
	public void recvFileData(LnsSocketTicket lnsSocketTicket) throws Exception {
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
	
	public void sendFileCheckReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03000040");
				
				lnsJsonNode.put("/__body_data", "sequence", "0000003");
				lnsJsonNode.put("/__body_data", "totLength", "0000008152");
				
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
	
	public void recvFileCheckReq(LnsSocketTicket lnsSocketTicket) throws Exception {
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
	
	public void sendFileCheckRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03100040");
				
				lnsJsonNode.put("/__body_data", "result", "00");
				lnsJsonNode.put("/__body_data", "sequence", "0000003");
				lnsJsonNode.put("/__body_data", "totLength", "0000008152");
				
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
	
	public void recvFileCheckRes(LnsSocketTicket lnsSocketTicket) throws Exception {
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
	
	public void sendFileFinishReq(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03000050");
				
				lnsJsonNode.put("/__body_data", "sequence", "0000003");
				lnsJsonNode.put("/__body_data", "sendLength", "0000008152");
				lnsJsonNode.put("/__body_data", "finDateTime", StringTools.getYYYYMMDDHHMMSS());
				
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
	
	public void recvFileFinishReq(LnsSocketTicket lnsSocketTicket) throws Exception {
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
	
	public void sendFileFinishRes(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03100050");
				
				lnsJsonNode.put("/__body_data", "result", "00");
				lnsJsonNode.put("/__body_data", "sequence", "0000003");
				lnsJsonNode.put("/__body_data", "sendLength", "0000008152");
				lnsJsonNode.put("/__body_data", "finDateTime", StringTools.getYYYYMMDDHHMMSS());
				
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
	
	public void recvFileFinishRes(LnsSocketTicket lnsSocketTicket) throws Exception {
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
