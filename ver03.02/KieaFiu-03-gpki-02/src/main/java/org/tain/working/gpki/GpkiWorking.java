package org.tain.working.gpki;

import java.io.File;

import org.springframework.stereotype.Component;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.gpki.gpkiapi.GpkiApi;
import com.gpki.gpkiapi.cert.X509Certificate;
import com.gpki.gpkiapi.cms.EnvelopedData;
import com.gpki.gpkiapi.storage.Disk;

import kofiu.rep.system.pki.SignVerify;
import lombok.extern.slf4j.Slf4j;
import signgate.crypto.auth.KICAAuth;
import signgate.crypto.util.Base64Util;

@Component
@Slf4j
public class GpkiWorking {

	final String basePath = "/Users/kangmac/KANG/fiu/20201111";
	//final String basePath = "/Users/kangmac/STS/GIT/KieaFiu/ver03.02/KieaFiu-03-gpki-02/src/main/resources";

	/* GPKI 정보설정 */
	//final String GPKI_CERT_PATH = this.basePath + "/info/SVR1160131005_env.cer";  // GPKI암호키분배용 인증서 경로
	final String GPKI_CERT_PATH = this.basePath + "/info/npki/kmCert.der";  // GPKI암호키분배용 인증서 경로
	final String GPKI_ALGORITHM = "ARIA";  // 암호화 알고리즘
	
	/* NPKI 정보 설정 */
	final String LICENSE_PATH  = this.basePath + "/info/license.kica";  // 라인선스 경로
	final String CERT_PATH     = this.basePath + "/info/npki/signCert.der";  // NPKI인증서 경로
	final String CERT_KEY_PATH = this.basePath + "/info/npki/signPri.key";  // NPKI인증서 개인키 경로
	final String CERT_PASSWD   = "signgate1!";  // NPKI인증서 비밀번호
	
	public void test01() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			System.out.println("*************************************************");
			System.out.println("** 전자서명 생성 및 검증 샘플");
			System.out.println("*************************************************");
			
			// 초기화
			SignVerify signVerify = new SignVerify(LICENSE_PATH, CERT_PATH, CERT_KEY_PATH, CERT_PASSWD);
			//SignVerify signVerify = new SignVerify(LICENSE_PATH, null, null, null);
			
			String originalFilePath	= this.basePath + "/info/file";  // 원본 파일경로
			String originalFileNm	= "orginl.xml";  // 원본 파일명
			String encryptedFileNm	= "gpkiEncryptFile.xml";  // GPKI 암호화 파일명
			
			// GPKI 암호화
			gpkiEncryptFile(GPKI_CERT_PATH, originalFilePath, originalFileNm, encryptedFileNm, GPKI_ALGORITHM);
				
			// 암호화파일 해쉬값
			byte[] hashBytes = signVerify.getHashwithSHA256(originalFilePath + File.separator + encryptedFileNm);
			System.out.println("Hash size :" + hashBytes.length);
			System.out.println("Hash Base64 :" + Base64Util.encode(hashBytes));
		
			// 전자서명
			String signedMessage = signVerify.makeSignature(hashBytes);
			//byte[] p7signedMessage = signVerify.makeSignature(hashBytes);
			System.out.println("signed message :" + signedMessage);
			
			// 전자서명 복호화 및 검증
			byte[] p7signedMessage = signedMessage.getBytes();
			boolean bVerify = signVerify.decryptSigned(p7signedMessage);
			System.out.println("검증결과 : " + bVerify);
			
			// 전문헤더 pemFormat 설정
			String encodeMessage = Base64Util.encode(signedMessage);
			System.out.println("encodeMessage : " + encodeMessage);
			
		}
	}
	
	private void gpkiEncryptFile(String gpkiCertPath, String originalFilePath, String originalFileNm, String encryptedFileNm, String algorithm) throws Exception {
		System.out.println("### Initiate GpkiApi with the license");
		//GpkiApi.init(this.basePath + "/info"); // GPKI 라인선스 경로(04.GPKI conf)
		//GpkiApi.init(LICENSE_PATH); // GPKI 라인선스 경로(04.GPKI conf)
		KICAAuth.init(LICENSE_PATH); // GPKI 라인선스 경로(04.GPKI conf)
		System.out.println("");
		
		X509Certificate cert = Disk.readCert(gpkiCertPath);
		EnvelopedData envData = new EnvelopedData(algorithm);		
		envData.addRecipient(cert);
		
		String originalFullPath = originalFilePath + "/" + originalFileNm;
		String encryptedFullPath = originalFilePath + "/" + encryptedFileNm;
		
		envData.generate_File(originalFullPath, encryptedFullPath);
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void test02() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			String path = this.basePath + "/info/conf/gpkiapi.lic";
			System.out.println("### Initiate GpkiApi with the license: " + path);
			GpkiApi.init(path);
		}
	}
}
