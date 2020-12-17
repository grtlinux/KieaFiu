package org.tain.working.gpki;

import java.io.File;

import com.gpki.gpkiapi.GpkiApi;
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.cms.EnvelopedData;
import com.gpki.gpkiapi.exception.GpkiApiException;
import com.gpki.gpkiapi.storage.Disk;
import kofiu.rep.system.pki.SignVerify;
import signgate.crypto.util.Base64Util;

public class FiuSignModule {
    
    
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
	// TODO Auto-generated method stub
		String BNK_HOME		= args[0];
		String SOURCE_PATH	= args[1];
		String SOURCE_FILE	= args[2];
		String TARGET_FILE	= args[3];

		/* GPKI 정보설정 */
    	String GPKI_CERT_PATH 	= BNK_HOME + "/kofiu/encrypt/gpki_cert/SVR1160131005_env.cer";	// GPKI암호키분배용 인증서 경로
    	String GPKI_ALGORITHM 	= "ARIA";							// 암호화 알고리즘
	
    	/* NPKI 정보 설정 */
    	String LICENSE_PATH 	= BNK_HOME + "/kofiu/encrypt/organ/license.kica";			// 라인선스 경로
    	String CERT_PATH 		= BNK_HOME + "/kofiu/encrypt/organ/cert/npki/signCert.der";		// NPKI인증서 경로
    	String CERT_KEY_PATH 	= BNK_HOME + "/kofiu/encrypt/organ/cert/npki/signPri.key";		// NPKI인증서 개인키 경로
    	String CERT_PASSWD 		= "password_kyobo";							// NPKI인증서 비밀번호
	
     	//System.out.println("*************************************************");
     	//System.out.println("** 전자서명 생성 및 검증 샘플");
     	//System.out.println("*************************************************");
     	
     	// 초기화
     	SignVerify signVerify = new SignVerify(LICENSE_PATH, CERT_PATH, CERT_KEY_PATH, CERT_PASSWD);
 	    
     	String originalFilePath = SOURCE_PATH;				// 원본 파일경로
     	String originalFileNm 	= SOURCE_FILE;				// 원본 파일명
     	String encryptedFileNm 	= TARGET_FILE;		// GPKI 암호화 파일명
 	    
     	// GPKI 암호화
     	gpkiEncryptFile(BNK_HOME, GPKI_CERT_PATH, originalFilePath, originalFileNm, encryptedFileNm, GPKI_ALGORITHM);
			
     	// 암호화파일 해쉬값
     	byte[] hashBytes = signVerify.getHashwithSHA256(originalFilePath + File.separator + encryptedFileNm);
     	//System.out.println("Hash size :" + hashBytes.length);
     	//System.out.println("Hash Base64 :" + Base64Util.encode(hashBytes));
 	
     	// 전자서명
     	String signedMessage = signVerify.makeSignature(hashBytes);
     	//byte[] p7signedMessage = signVerify.makeSignature(hashBytes);
     	//System.out.println("signed message :" + signedMessage);
     	
     	// 전자서명 복호화 및 검증
     	byte[] p7signedMessage = signedMessage.getBytes();
     	boolean bVerify = signVerify.decryptSigned(p7signedMessage);
     	//System.out.println("검증결과 : " + bVerify);
     	
     	// 전문헤더 pemFormat 설정
     	String encodeMessage = Base64Util.encode(signedMessage);
     	//System.out.println("encodeMessage : " + encodeMessage);
     	System.out.println(encodeMessage);
 	    
    }
    
    public static void gpkiEncryptFile(String BNK_HOME, String gpkiCertPath, String originalFilePath, String originalFileNm, String encryptedFileNm, String algorithm) throws GpkiApiException {
	
	//System.out.println("### Initiate GpkiApi with the license");
	GpkiApi.init(BNK_HOME + "/kofiu/encrypt/conf"); // GPKI 라인선스 경로(04.GPKI conf)
	
	X509Certificate cert = Disk.readCert(gpkiCertPath);
	EnvelopedData envData = new EnvelopedData(algorithm);		
	envData.addRecipient(cert);
	
	String originalFullPath = originalFilePath + "/" + originalFileNm;
	String encryptedFullPath = originalFilePath + "/" + encryptedFileNm;
	
	envData.generate_File(originalFullPath, encryptedFullPath);
	
    }
}
