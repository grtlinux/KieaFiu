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
	
	public void sendFileStartReq(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03000020");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileStartReq(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileStartRes(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03100020");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileStartRes(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileData(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03000030");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileData(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileCheckReq(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03000040");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileCheckReq(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileCheckRes(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03100040");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileCheckRes(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileFinishReq(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03000050");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileFinishReq(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void sendFileFinishRes(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
				LnsJsonNode lnsJsonNode = null;
				if (Flag.flag) {
					lnsJsonNode = new LnsJsonNode("{\"__head_data\": {}, \"__body_data\": {}}");
					lnsJsonNode.put("/__head_data", "length", "0000");
					lnsJsonNode.put("/__head_data", "transactionCode", "DBOGOXFIU");
					lnsJsonNode.put("/__head_data", "systemName", "FIU");
					lnsJsonNode.put("/__head_data", "bogoCode", "G00001");
					lnsJsonNode.put("/__head_data", "userId", "Testuser01");
					lnsJsonNode.put("/__head_data", "typeCode", "03100050");
					lnsJsonNode.put("/__head_data", "reqresGubun", "S");
					lnsJsonNode.put("/__head_data", "orgGubun", "B");
					lnsJsonNode.put("/__head_data", "sequenceYn", "Y");
					lnsJsonNode.put("/__head_data", "resCode", "000");
					lnsJsonNode.put("/__body_data", "openDataTime", "yyyyMMddHHmmss");
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
	
	public void recvFileFinishRes(LnsSocketTicket lnsSocketTicket) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			try {
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
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				//
			}
		}
	}
}