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

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Getter
@Slf4j
public class FiuSocket {

	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	@Autowired
	private FiuInfo fiuInfo;
	
	private LnsStream lnsStream = null;
	private String strStream = null;
	private String typeCode = null;
	private LnsMstInfo lnsMstInfo = null;
	private JsonNode jsonNode = null;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public LnsJsonNode recv(LnsSocketTicket lnsSocketTicket) throws Exception {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (Flag.flag) {
				this.lnsStream = lnsSocketTicket.recvStream();
				log.info(">>>>> 1-RECV.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(this.lnsStream));
			}
			
			if (Flag.flag) {
				this.strStream = this.lnsStream.getData();
				log.info(">>>>> 2-RECV.strStream = {}", this.strStream);
			}
			
			if (Flag.flag) {
				this.typeCode = this.strStream.substring(42, 50);
				log.info(">>>>> 3-RECV.typeCode = {}", this.typeCode);
			}
			
			if (Flag.flag) {
				this.lnsMstInfo = this.mapperReaderJob.get(this.typeCode);
				log.info(">>>>> 4-RECV.lnsMstInfo = {}", this.lnsMstInfo);
			}
			
			if (Flag.flag) {
				this.jsonNode = new LnsStreamToJson(lnsMstInfo, this.strStream).get();
				log.info(">>>>> 5-RECV.jsonNode = {}", this.jsonNode.toPrettyString());
			}
		}
		
		return new LnsJsonNode(this.jsonNode);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void send(LnsSocketTicket lnsSocketTicket, LnsJsonNode resLnsJsonNode) throws Exception {
		
		String strStream = null;
		if (Flag.flag) {
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(resLnsJsonNode.getText("/__head_data", "typeCode"));
			strStream = new LnsJsonToStream(lnsMstInfo, resLnsJsonNode.get()).get();
			
			log.info(">>>>> SEND.strStream: [{}]", strStream);
		}
		
		if (Flag.flag) {
			LnsStream lnsStream = new LnsStream(strStream);
			lnsSocketTicket.sendStream(lnsStream);
			log.info(">>>>> SEND.lnsStream = {}", JsonPrint.getInstance().toPrettyJson(lnsStream));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void sendData(LnsSocketTicket lnsSocketTicket) throws Exception {
		byte[] bHead = null;
		if (Flag.flag) {
			// header
			LnsJsonNode lnsJsonNode = FiuTools.getDefault();
			lnsJsonNode.put("/__head_data", "typeCode", "03000030");
			lnsJsonNode.put("/__body_data", "sequence"  , String.format("%07d" , this.fiuInfo.getPageSeq()));
			lnsJsonNode.put("/__body_data", "sentLength", String.format("%010d", this.fiuInfo.getPageSentLength()));
			lnsJsonNode.put("/__body_data", "dataLength", String.format("%04d" , this.fiuInfo.getPageLength()));
			
			LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("/__head_data", "typeCode"));
			String strStream = new LnsJsonToStream(lnsMstInfo, lnsJsonNode.get()).get();
			
			bHead = strStream.substring(4).getBytes("euc-kr");
		}
		
		byte[] bBody = null;
		if (Flag.flag) {
			// body
			bBody = this.fiuInfo.getPageData();
		}
		
		byte[] bData = null;
		if (Flag.flag) {
			// combination
			int lenHead = bHead.length;
			int lenBody = bBody.length;
			int lenData = 4 + lenHead + lenBody;
			byte[] bLen = String.format("%04d", lenData).getBytes();
			
			bData = new byte[lenData];
			System.arraycopy(bLen, 0, bData, 0, 4);                      // bLen
			System.arraycopy(bHead, 0, bData, 4, lenHead);           // bHeader
			System.arraycopy(bBody, 0, bData, 4 + lenHead, lenBody);   // bBody
		}
		
		if (Flag.flag) {
			lnsSocketTicket.sendBytes(bData);
			log.info(">>>>> SEND.bData = {}", StringTools.getByteString(bData));
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
}
