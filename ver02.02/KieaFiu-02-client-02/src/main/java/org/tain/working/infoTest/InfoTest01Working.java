package org.tain.working.infoTest;

import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.tain.mapper.LnsJsonNode;
import org.tain.mapper.LnsMstInfo;
import org.tain.socket.FiuInfo;
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
	
	@Autowired
	private FiuInfo fiuInfo;
	
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
			System.out.println("3 >>>>> " + strData);
			
			byte[] bData2 = StringTools.getBytesFromString(strData);
			System.out.println("3 >>>>> " + StringTools.getByteString(bData2));
		}
		
		if (Flag.flag) {
			// data = header + body
			byte[] bData = null;
			
			byte [] bHeader = {
				(byte)0x00, (byte)0x00, (byte)0x4E, (byte)0x20,
				(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2F,
				(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
			};
			String strBody = "abcdefg";
			byte[] bBody = strBody.getBytes(); // 문자열을 바이트 배열로 변환
			
			bData = new byte[bHeader.length + bBody.length]; // body와 header를 담을 바이트배열생성 
			
			// data 배열의 0번째 인덱스에 header 배열의 0번째 인덱스부터 header 의 길이만큼 복사
			System.arraycopy(bHeader, 0, bData, 0, bHeader.length);
			
			// data 배열에 header.length 만큼의 인덱스에 temp 배열의 0번째 인덱스부터 body 의 길이만큼 복사
			System.arraycopy(bBody, 0, bData, bHeader.length, bBody.length);
			
			System.out.println("4 >>>>> " + StringTools.getByteString(bData));
		}
		
		if (Flag.flag) {
			// dst.dat.env
			this.fiuInfo.set();
			this.fiuInfo.getFile();
			
			int iUnit = 32;
			byte[] bFileData = this.fiuInfo.getBFileData();
			for (int i=0; i < bFileData.length; i += iUnit) {
				int iBeg = i;
				int iEnd = Math.min(i + iUnit, bFileData.length);
				int iLen = iEnd - iBeg;
				byte[] bSplit = Arrays.copyOfRange(bFileData, iBeg, iEnd);
				System.out.printf("5 >>>>> (%03d) %s%n", iLen, StringTools.getByteString(bSplit));
			}
		}
	}
}
