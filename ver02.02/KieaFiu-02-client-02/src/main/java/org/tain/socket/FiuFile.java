package org.tain.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.mapper.LnsJsonNode;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FiuFile {

	@Autowired
	private FiuInfo fiuInfo;
	
	//@Autowired
	//private MapperReaderJob mapperReaderJob;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileStartReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000020");
			
			reqLnsJsonNode.put("/__body_data", "docCode", "REP002");
			reqLnsJsonNode.put("/__body_data", "annMsgCode", "01");
			reqLnsJsonNode.put("/__body_data", "annDocNo", StringTools.getYYYY() + "-00000001");
			reqLnsJsonNode.put("/__body_data", "annDate", StringTools.getYYYYMMDD());
			reqLnsJsonNode.put("/__body_data", "befDocNo", "");
			reqLnsJsonNode.put("/__body_data", "fileName", this.fiuInfo.getFileName());
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
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault();
			resLnsJsonNode.put("/__head_data", "typeCode", "03100020");
			
			resLnsJsonNode.put("/__body_data", "docCode"    , reqLnsJsonNode.getText("/__body_data", "docCode"));
			resLnsJsonNode.put("/__body_data", "annMsgCode" , reqLnsJsonNode.getText("/__body_data", "annMsgCode"));
			resLnsJsonNode.put("/__body_data", "annDocNo"   , reqLnsJsonNode.getText("/__body_data", "annDocNo"));
			resLnsJsonNode.put("/__body_data", "annDate"    , reqLnsJsonNode.getText("/__body_data", "annDate"));
			resLnsJsonNode.put("/__body_data", "befDocNo"   , "");
			resLnsJsonNode.put("/__body_data", "fileName"   , reqLnsJsonNode.getText("/__body_data", "fileName"));
			resLnsJsonNode.put("/__body_data", "transNo"    , reqLnsJsonNode.getText("/__body_data", "transNo"));
			resLnsJsonNode.put("/__body_data", "baseDocNo"  , reqLnsJsonNode.getText("/__body_data", "baseDocNo"));
			resLnsJsonNode.put("/__body_data", "midOrgCode" , reqLnsJsonNode.getText("/__body_data", "midOrgCode"));
			resLnsJsonNode.put("/__body_data", "recLength"  , reqLnsJsonNode.getText("/__body_data", "recLength"));
			resLnsJsonNode.put("/__body_data", "zipYn"      , reqLnsJsonNode.getText("/__body_data", "zipYn"));
			resLnsJsonNode.put("/__body_data", "totLength"  , reqLnsJsonNode.getText("/__body_data", "totLength"));
			resLnsJsonNode.put("/__body_data", "linkYn"     , "Y");
			resLnsJsonNode.put("/__body_data", "recvLength" , "0000000000");
			
			log.info(">>>>> reslnsJsonNode: {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileSendData() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		String data = null;
		if (Flag.flag) {
			//data = this.fiuInfo.getCurrentPage();
			data = "";
		}
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000030");
			
			reqLnsJsonNode.put("/__body_data", "sequence"  , String.format("%07d" , this.fiuInfo.getPageSeq()));
			reqLnsJsonNode.put("/__body_data", "sentLength", String.format("%010d", this.fiuInfo.getPageSentLength()));
			reqLnsJsonNode.put("/__body_data", "dataLength", String.format("%04d" , this.fiuInfo.getPageLength()));
			reqLnsJsonNode.put("/__body_data", "data"      , data);
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileRecvData(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		if (Flag.flag) {
			
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/*
	 * totLength = sentLength + dataLength;
	 */
	public LnsJsonNode getFileCheckReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000040");
			
			reqLnsJsonNode.put("/__body_data", "sequence" , String.format("%07d", this.fiuInfo.getPageSeq()));
			reqLnsJsonNode.put("/__body_data", "totLength", String.format("%010d", this.fiuInfo.getPageSentLength() + this.fiuInfo.getPageLength()));
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileCheckRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault();
			resLnsJsonNode.put("/__head_data", "typeCode", "03100040");
			
			resLnsJsonNode.put("/__body_data", "result"   , "00");
			resLnsJsonNode.put("/__body_data", "sequence" , reqLnsJsonNode.getText("/__body_data", "sequence"));
			resLnsJsonNode.put("/__body_data", "totLength", reqLnsJsonNode.getText("/__body_data", "totLength"));
			
			log.info(">>>>> SEND.lnsJsonNode: {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileFinishReq() throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode reqLnsJsonNode = null;
		if (Flag.flag) {
			reqLnsJsonNode = FiuTools.getDefault();
			reqLnsJsonNode.put("/__head_data", "typeCode", "03000050");
			
			reqLnsJsonNode.put("/__body_data", "sequence"   , String.format("%07d", this.fiuInfo.getPageSeq()));
			reqLnsJsonNode.put("/__body_data", "totLength"  , String.format("%010d", this.fiuInfo.getPageSentLength() + this.fiuInfo.getPageLength()));
			reqLnsJsonNode.put("/__body_data", "finDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> SEND.lnsJsonNode: {}", reqLnsJsonNode.toPrettyString());
		}
		
		return reqLnsJsonNode;
	}
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode getFileFinishRes(LnsJsonNode reqLnsJsonNode) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", StringTools.getDashLine('='), CurrentInfo.get());
		
		LnsJsonNode resLnsJsonNode = null;
		if (Flag.flag) {
			resLnsJsonNode = FiuTools.getDefault();
			resLnsJsonNode.put("/__head_data", "typeCode", "03100050");
			
			resLnsJsonNode.put("/__body_data", "result"     , "00");
			resLnsJsonNode.put("/__body_data", "sequence"   , reqLnsJsonNode.getText("/__body_data", "sequence"));
			resLnsJsonNode.put("/__body_data", "totLength"  , reqLnsJsonNode.getText("/__body_data", "totLength"));
			resLnsJsonNode.put("/__body_data", "finDateTime", StringTools.getYYYYMMDDHHMMSS());
			
			log.info(">>>>> SEND.lnsJsonNode: {}", resLnsJsonNode.toPrettyString());
		}
		
		return resLnsJsonNode;
	}
}
