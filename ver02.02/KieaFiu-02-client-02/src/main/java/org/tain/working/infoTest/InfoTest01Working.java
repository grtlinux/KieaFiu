package org.tain.working.infoTest;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsMstInfo;
import org.tain.task.MapperReaderJob;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.JsonPrint;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InfoTest01Working {

	@Autowired
	private MapperReaderJob mapperReaderJob;
	
	public void test01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			LnsJsonNode lnsJsonNode = null;
			if (Flag.flag) {
				lnsJsonNode = new LnsJsonNode();
				lnsJsonNode.put("reqResType", "0700200");
			}
			if (Flag.flag) {
				
				LnsMstInfo lnsMstInfo = this.mapperReaderJob.get(lnsJsonNode.getText("reqResType"));
				log.info("MAPPER.get = {}", JsonPrint.getInstance().toPrettyJson(lnsMstInfo));
			}
		}
	}
	
	public void test02() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String text = "ktko";
			byte[] targetBytes = text.getBytes();
			
			// Base64 인코딩
			///////////////////////////////////////////////////
			Encoder encoder = Base64.getEncoder();
			byte[] encodedBytes = encoder.encode(targetBytes);
			
			// Base64 디코딩
			///////////////////////////////////////////////////
			Decoder decoder = Base64.getDecoder();
			byte[] decodedBytes = decoder.decode(encodedBytes);
			
			System.out.println("1. 인코딩 전 : " + text);
			System.out.println("1. 인코딩 text : " + new String(encodedBytes));
			System.out.println("1. 디코딩 text : " + new String(decodedBytes));
		}
		
		if (Flag.flag) {
			String text = "ktko";
			
			// Base64 인코딩
			///////////////////////////////////////////////////
			byte[] encodedBytes = Base64Utils.encode(text.getBytes());
			
			// Base64 디코딩
			///////////////////////////////////////////////////
			byte[] decodedBytes = Base64Utils.decode(encodedBytes);
			
			System.out.println("2. 인코딩 전 : " + text);
			System.out.println("2. 인코딩 text : " + new String(encodedBytes));
			System.out.println("2. 디코딩 text : " + new String(decodedBytes));
		}
		
		if (Flag.flag) {
			// byte to hex and hex to byte
			byte[] bData = new byte[] {0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xff, 0x11};
			//byte[] bData = {0x01, 0x02, 0x03, 0x04, 0x00, (byte) 0xff, 0x11};
			String strData = StringTools.getStringFromBytes(bData);
			System.out.println(">>>>> " + strData);
			
			byte[] bData2 = StringTools.getBytesFromString(strData);
			System.out.println(">>>>> " + StringTools.getByteString(bData2));
		}
	}
}
