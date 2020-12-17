package org.tain.working.gpki;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import kofiu.framework.util.PropertiesUtil;
import kofiu.rep.system.pki.SignVerify;
import signgate.crypto.util.Base64Util;

public class SignVerifyMain {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SignVerifyMain.class);
    
    /* NPKI 정보 설정 */
    //final static String LICENSE_PATH = PropertiesUtil.getString("npki.license.file"); 	// KICA 라인선스
    final static String LICENSE_PATH 	= "C:/FIU-Project/04.Sample/license.kica";		// KICA 라인선스
    
    
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub
	
	LOGGER.debug("*************************************************");
	LOGGER.debug("** 전자서명 검증 샘플");
	LOGGER.debug("*************************************************");
	
	// 초기화
	SignVerify signVerify = new SignVerify(LICENSE_PATH, null, null, null);
	
	String originalFilePath 	= "C:/FIU-Project/04.Sample/file";	// 원본 파일경로
	String originalFile 		= "gpkiEncryptFile.xml";		// 원본 파일경로
	//String pemFormat 		= "pemFormat.txt";			// 테스트 pemFormat(ESB header정보 포함되어짐)
	String pemFormat 		= "LS0tLS1CRUdJTiBQS0NTNy0tLS0tCk1JSUlGUVlKS29aSWh2Y05BUWNDb0lJSUJqQ0NDQUlDQVFFeER6QU5CZ2xnaGtnQlpRTUVBZ0VGQURBdkJna3EKaGtpRzl3MEJCd0dnSWdRZ1NpU2NidVNtYmZaUW5obW9FYkpaN1VBY1FlempGakxWUlF2Z1BId0hJRVNnZ2dYUApNSUlGeXpDQ0JMT2dBd0lCQWdJRUJPQXpTakFOQmdrcWhraUc5dzBCQVFzRkFEQktNUXN3Q1FZRFZRUUdFd0pMClVqRU5NQXNHQTFVRUNnd0VTMGxEUVRFVk1CTUdBMVVFQ3d3TVFXTmpjbVZrYVhSbFpFTkJNUlV3RXdZRFZRUUQKREF4emFXZHVSMEZVUlNCRFFUVXdIaGNOTVRrd056QTRNRFkwTlRBd1doY05NakF3TnpBNE1UUTFPVFU1V2pDQgprakVMTUFrR0ExVUVCaE1DUzFJeERUQUxCZ05WQkFvTUJFdEpRMEV4RXpBUkJnTlZCQXNNQ214cFkyVnVjMlZrClEwRXhGVEFUQmdOVkJBc01ET3VUc2V1aG5lcTRzT3EwZ0RFWk1CY0dBMVVFQ3d3UVMwbERRZXF6b09xd25leUUKdk8yRXNERVJNQThHQTFVRUN3d0k3SVM4N1lTd1VrRXhHakFZQmdOVkJBTU1FZTJWbk95Z2xleWR1Q2pyc3BYcwpuYmdwTUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUFrUDhLN0hxVGRsLzZuQkYxCi85U3BqcTgxYWdxNm5xem0zM0JYVFVZT09hNWxFYUhVT0tCNFpwVXFDTmRIYVBYUisvb3l2VWFpWXNpV1RnKzAKdUFYZlhEa2tPTHYyclhwdEw3Z0FPSTc5UWhWSXZ2dHYwcVlZcWswcnlCd3AvWVVLRFRiUG83UzZmSFBLUmZlWgoxcEdPV0pXRHhDSmVxYzZNQkFtQWc1djFLMzd2MjhOTjdhMlUrdVRSN3NIRS9ZRkUxem01QTNLRmVlQUk2NXlnCmp2OWtHUnZKTEdZdm9KTDBTa1AzWUgxcE5RR0tkUEJRNW9IRC9sSU41RGtkQjc1clI3YVZmbkdUa08ydnRmLy8KTmRUb3ZqMndKNTVhZmc0Y216QXIwYXNRR0wxem8vNldzNGFPNXhVb0NKM3VRSng2OE90ZTY4NTZXRHJrVW5OcQpPUmltOFFJREFRQUJvNElDYmpDQ0Ftb3dnWThHQTFVZEl3U0JoekNCaElBVTJMNDY3RVdaeFo3am5PcUJIOUlkCkVyQTJQb2loYUtSbU1HUXhDekFKQmdOVkJBWVRBa3RTTVEwd0N3WURWUVFLREFSTFNWTkJNUzR3TEFZRFZRUUwKRENWTGIzSmxZU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVTQkRaVzUwY21Gc01SWXdGQVlEVlFRRApEQTFMU1ZOQklGSnZiM1JEUVNBMGdnSVFIVEFkQmdOVkhRNEVGZ1FVd2ltcDU2d3E1bnhzdXdXUUo5NHZ4Vk4wCkU1c3dEZ1lEVlIwUEFRSC9CQVFEQWdiQU1IVUdBMVVkSUFSdU1Hd3dhZ1lLS29NYWpKcEVCUUlCQVRCY01Dd0cKQ0NzR0FRVUZCd0lCRmlCb2RIUndPaTh2ZDNkM0xuTnBaMjVuWVhSbExtTnZiUzlqY0hNdWFIUnRiREFzQmdncgpCZ0VGQlFjQ0FqQWdIaDdIZEFBZ3gzakpuY0Vjc3BRQUlLejF4M2pIZU1tZHdSekhoYkxJc3VRd2dZZ0dBMVVkCkVRU0JnREIrZ1JWd2RYSmxNamN4TTBCemFXZHVaMkYwWlM1amIyMmdaUVlKS29NYWpKcEVDZ0VCb0Znd1Znd1IKN1pXYzdLQ1Y3SjI0S091eWxleWR1Q2t3UVRBL0Jnb3FneHFNbWtRS0FRRUJNREV3Q3dZSllJWklBV1VEQkFJQgpvQ0lFSUNlbm5qUVpJVmgxSFNhNUNHU25Ubk9pQjY4aElTOG1FN3U0SzlUS3doS1pNRjhHQTFVZEh3UllNRll3ClZLQlNvRkNHVG14a1lYQTZMeTlzWkdGd0xuTnBaMjVuWVhSbExtTnZiVG96T0RrdmIzVTlaSEEzY0RFME9EQXoKTEc5MVBXTnliR1J3TEc5MVBVRmpZM0psWkdsMFpXUkRRU3h2UFV0SlEwRXNZejFMVWpCRUJnZ3JCZ0VGQlFjQgpBUVE0TURZd05BWUlLd1lCQlFVSE1BR0dLR2gwZEhBNkx5OXZZM053TG5OcFoyNW5ZWFJsTG1OdmJUbzVNREl3CkwwOURVMUJUWlhKMlpYSXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBQkxGeWhjQlh4VTNuOVFpQk1qRjVrUVIKUmk2N3pFeXlzM0xsRUhZWUpJZStaa3Y5ejJDMDBuUVM0Z0NqdlhVUUJjVkgwVDV3NEhOTHZ4U00yaFNiWlFOcgoxMTh5SjVZaTFVTzRyRkVhcjhDUVUwVGdyenAydTUxZWFFVUZaSTRHNlB2bzFJaGpXeGRidmVZaFk3cWFFamlSCmFjUnRRVHMzZTBiNi9KY3FhZHA4M2dPNHoveUpWdUZoeUZMaFcrc25oOFQzUUphNmphS2tpVk5MZ3pOaDdJMDgKNjNJZW9oZ1JUTUUwRGQyQTRHT2ZzQVlFMFlCczRWVlBBR1ZvRGFuRWhZeW9KdlAydklmRldBTGtJbFl5bkRtMAoxR1AyYUlyM0tKMWVsNzYzR0FMRXR4all6Z0IvZFBOaWppNkJSZVBaMEljR2FOK0VUQUhmQzg0blBJU1lNTTh4CmdnSG1NSUlCNGdJQkFUQlNNRW94Q3pBSkJnTlZCQVlUQWt0U01RMHdDd1lEVlFRS0RBUkxTVU5CTVJVd0V3WUQKVlFRTERBeEJZMk55WldScGRHVmtRMEV4RlRBVEJnTlZCQU1NREhOcFoyNUhRVlJGSUVOQk5RSUVCT0F6U2pBTgpCZ2xnaGtnQlpRTUVBZ0VGQUtCcE1CZ0dDU3FHU0liM0RRRUpBekVMQmdrcWhraUc5dzBCQndFd0hBWUpLb1pJCmh2Y05BUWtGTVE4WERUSXdNRFV5TURBMk5EQXpNRm93THdZSktvWklodmNOQVFrRU1TSUVJSkFiK2dQSGtCb0IKd2pwaFlTZm0xK25YT1IyamdlMzBtTzhMc0w2NnFlUG1NQXNHQ1NxR1NJYjNEUUVCQVFTQ0FRQUdpallYeXBvSQp1RUdYVzV0NnErMTByS3k3Z3QwV2UvRGdaR1JYeVlUMDIyVHBtNHMxZHBvZGI5bUFvaGdhZlFiOWUxMWQ4SXZrCjBmdVBjRVNaU1psTFJKMERWZUNpQlJoQ2lFK3piaDhrcTNMam5qekFqN1RCUGkxdmxjQ2V2QitWUUd0OCtNc04Kc2hVR1FrZ3JBNkdrYTdqZG8xVzNPMk5MWnNXK3VDNzM2VERpcFhQbUJZWXFPV3Y3QmRDckhFTnpiS1hGaU5nWQp0S2JxUnRvayttcUpCRXluUThXb29yc000NXlvYTFSdFJyd2lXRm82MUhsNWpPaHNubFJWS1hXZHRBajFEZURFClZITytGN3FHVTlscEFQUTl6T0EyWnpUNlU1ZjJ4VlBmYkcyU2FKMzlWQUJ2QVRsK2I2V1RTRWNLU0xHdDcyVFMKeGdzaDdUSFNHT2svCi0tLS0tRU5EIFBLQ1M3LS0tLS0K";			
 	    
	/*
	 * ESB 전문 header에 pemFormat(원본파일명(해쉬)+전자서명)정보를 Base64Util.decode 수행한다.  
	 * ESB 파일연계 원본파일을 읽어 해쉬값 변화한다.
	 * pemFormat 과 읽어드린 해쉬값과 비교 검증한다.
	 */
	// TODO : ESB 전문 header에 pemFormat(원본파일명 GPKI 암호화파일(해쉬) + 전자서명)
	//byte[] p7signedMessage = signVerify.getFileByte(originalFilePath + File.separator + pemFormat);
	
	byte[] p7signedMessage = Base64Util.decode(pemFormat);
	
	// 암호화파일 해쉬값
	byte[] hashBytes = signVerify.getHashwithSHA256(originalFilePath + File.separator + originalFile);
	LOGGER.debug("hash size :: " + hashBytes.length);
		
	// 전자서명 검증 및 해쉬검증[보고기관 수신측 검증]
	boolean bVerify = signVerify.decryptSigned(p7signedMessage, hashBytes);
	LOGGER.debug("전사서명 검증 결과 :: " + bVerify);
	
    }   
}