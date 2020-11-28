package org.tain.socket;

import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuFile {

	//@Autowired
	//private MapperReaderJob mapperReaderJob;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileStartReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000020");
			
			reqLnsJsonNode.put("/__body_data", "docCode", "REP002");
			reqLnsJsonNode.put("/__body_data", "msgCode", "01");
			reqLnsJsonNode.put("/__body_data", "sendSeq", "00000001");
			reqLnsJsonNode.put("/__body_data", "docNo", StringTools.getYYYY() + "-00000001");
			reqLnsJsonNode.put("/__body_data", "annDate", StringTools.getYYYYMMDD());
			reqLnsJsonNode.put("/__body_data", "befDocNo", "");
			reqLnsJsonNode.put("/__body_data", "fileName", "CRT-GC0017-" + StringTools.getYYYYMMDD() + "-00000001.env");
			reqLnsJsonNode.put("/__body_data", "transNo", "0000001/0000001");
			reqLnsJsonNode.put("/__body_data", "baseDocNo", StringTools.getYYYYMMDD() + "-00000001");
			reqLnsJsonNode.put("/__body_data", "midOrgCode", "GA0002");
			reqLnsJsonNode.put("/__body_data", "recLength", "0001");
			reqLnsJsonNode.put("/__body_data", "zipYn", "0");
			reqLnsJsonNode.put("/__body_data", "totLength", "0000000100");
			reqLnsJsonNode.put("/__body_data", "data", "____GPKI_Hash_DATA______");
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileStartRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault();
			resLnsJsonNode.put("/__head_data", "typeCode", "03100020");
			
			resLnsJsonNode.put("/__body_data", "docCode", "REP002");
			resLnsJsonNode.put("/__body_data", "msgCode", "01");
			resLnsJsonNode.put("/__body_data", "sendSeq", "00000001");
			resLnsJsonNode.put("/__body_data", "docNo", StringTools.getYYYY() + "-00000001");
			resLnsJsonNode.put("/__body_data", "annDate", StringTools.getYYYYMMDD());
			resLnsJsonNode.put("/__body_data", "befDocNo", "");
			resLnsJsonNode.put("/__body_data", "fileName", "CRT-GC0017-" + StringTools.getYYYYMMDD() + "-00000001.env");
			resLnsJsonNode.put("/__body_data", "transNo", "0000001/0000001");
			resLnsJsonNode.put("/__body_data", "baseDocNo", StringTools.getYYYYMMDD() + "-00000001");
			resLnsJsonNode.put("/__body_data", "midOrgCode", "GA0002");
			resLnsJsonNode.put("/__body_data", "recLength", "0001");
			resLnsJsonNode.put("/__body_data", "zipYn", "0");
			resLnsJsonNode.put("/__body_data", "totLength", "0000000100");
			resLnsJsonNode.put("/__body_data", "linkYn", "N");
			resLnsJsonNode.put("/__body_data", "recvLength", "0000000000");
			
			log.info(">>>>> reslnsJsonNode: {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	@Deprecated
	public void sendFileData(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 =========================================================");
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = FiuTools.getDefault();
				lnsJsonNode.put("/__head_data", "typeCode", "03000030");
				
				lnsJsonNode.put("/__body_data", "sequence", "0000001");
				lnsJsonNode.put("/__body_data", "sendLength", "0000000100");
				lnsJsonNode.put("/__body_data", "dataLength", "0100");
				lnsJsonNode.put("/__body_data", "data", "_____100_DATA_______");
				
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
	
	@Deprecated
	public void recvFileData(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 =========================================================");
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
	*/
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileData() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000030");
			
			reqLnsJsonNode.put("/__body_data", "sequence", "0000001");
			reqLnsJsonNode.put("/__body_data", "sendLength", "0000000100");
			reqLnsJsonNode.put("/__body_data", "dataLength", "0100");
			reqLnsJsonNode.put("/__body_data", "data", "_____100_DATA_______");
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void writeFileData(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		log.info(">>>>> WriteFileData RECV.reqLnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileCheckReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000040");
			
			reqLnsJsonNode.put("/__body_data", "sequence", "0000001");
			reqLnsJsonNode.put("/__body_data", "totLength", "0000000100");
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileCheckRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault();
			resLnsJsonNode.put("/__head_data", "typeCode", "03100040");
			
			resLnsJsonNode.put("/__body_data", "result", "00");
			resLnsJsonNode.put("/__body_data", "sequence", "0000001");
			resLnsJsonNode.put("/__body_data", "totLength", "0000000100");
			
			log.info(">>>>> SEND.lnsJsonNode: {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileFinishReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000050");
			
			reqLnsJsonNode.put("/__body_data", "sequence", "0000001");
			reqLnsJsonNode.put("/__body_data", "sendLength", "0000000100");
			reqLnsJsonNode.put("/__body_data", "finDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileFinishRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault();
			resLnsJsonNode.put("/__head_data", "typeCode", "03100050");
			
			resLnsJsonNode.put("/__body_data", "result", "00");
			resLnsJsonNode.put("/__body_data", "sequence", "0000001");
			resLnsJsonNode.put("/__body_data", "sendLength", "0000000100");
			resLnsJsonNode.put("/__body_data", "finDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> SEND.lnsJsonNode: {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
}
