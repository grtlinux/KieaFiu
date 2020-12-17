package org.tain.working.gpki;

/*------------------------------------------------------------------------------
 * PROJ   : FIU 차세대 정보시스템 구축사업
 * NAME   : SignVerify.java
 * DESC   : 
 * Author : FIUDEV24
 * VER    : 1.0
 * Copyright 2019 LG CNS All rights reserved
 *------------------------------------------------------------------------------
 *                  변         경         사         항                       
 *------------------------------------------------------------------------------
 *    DATE       AUTHOR                      DESCRIPTION                        
 * ----------    ------  --------------------------------------------------------- 
 * 2020. 5. 11.   FIUDEV24    최초 프로그램 작성                                     
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import kofiu.framework.exception.FIUException;
//import kofiu.framework.util.PropertiesUtil;
import signgate.crypto.auth.KICAAuth;
import signgate.crypto.util.CertUtil;
import signgate.crypto.util.MDUtil;
import signgate.crypto.util.PKCS7Util;

/**
 * SignVerify.java 클래스
 *
 * @author FIUDEV24
 * @since  2020. 5. 11.
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자                  수정내용
 *  -------    -----------    ----------------------
 *   2020. 5. 11.   FIUDEV24         초기 생성
 * </pre>
 *
 */

public class SignVerify {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SignVerify.class);
	
	private byte[] signCert;
	private byte[] signPrivKey;
	private String certPsd;
	
	public SignVerify(String licenseFilePath, String certFilePath, String certKeyFilePath, String certPasswd) throws Exception{
		try {
			KICAAuth.init(licenseFilePath);
			
			if(certFilePath != null && certKeyFilePath != null && certPasswd != null){
			this.signCert = getFileByte(certFilePath);
			this.signPrivKey = getFileByte(certKeyFilePath);
			this.certPsd = certPasswd;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("COM00004001");
		}
	}
	
	/**
	 * SHA256 해쉬 값 수행
	 * 
	 * @param orgFileName 원문 파일 이름
	 * @return byte[]     SHA256 해쉬 값
	 * @throws Exception
	 */
	public byte[] getHashwithSHA256(String orgFileName) throws Exception {
		byte[] temp = null;
		
		try {
			byte[] origin = getFileByte(orgFileName);
			MDUtil mu = new MDUtil("SHA-256");
			mu.update(origin);
			temp = mu.digest();
		} catch (Exception e) {
			LOGGER.debug("ERROR getHashwithSHA256");
			throw new Exception("COM00004002");
		}
		
		return temp;
	}
	
	/**
	 * 전자서명 수행
	 * 
	 * @param orginlMsg 원문 메시지
	 * @return byte[]   서명 메시지
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public String makeSignature(byte[] orginlMsg) throws Exception {
		CertUtil certutil = null;
		String signedMsg = null;
		
		try {
			certutil = new CertUtil(this.signCert);
		} catch (CertificateException e) {
			throw new Exception("[ERROR] 전자서명 : " + certutil.getErrorMsg());
		}
		
		PKCS7Util p7SignUtil = new PKCS7Util();
	
		// 전자서명 수행
		try {
			signedMsg = p7SignUtil.genSignedData(this.signPrivKey, this.certPsd, this.signCert, orginlMsg);
		} catch (Exception e) {
			throw new Exception("[ERROR] 전자서명 : " + e.getMessage());
		}
	
		return signedMsg;
	}
	
	/**
	 * 전자서명 복호화 및 검증
	 * 
	 * @param signedMessage 전자서명 결과 메시지
	 * @return boolean	  검증성공여부
	 * @throws Exception
	 */
	public boolean decryptSigned(byte[] signedMessage) throws Exception {
		boolean bVerify = false;
		int p7Type = 0;
	
		PKCS7Util p7VrfUtil = new PKCS7Util();
		p7Type = p7VrfUtil.getPKCS7Type(signedMessage);
		
		if (p7Type != 1 && p7Type != 2 && p7Type != 3) {
			throw new Exception("COM00004003");
		}
	
		// 전자서명 검증
		bVerify = p7VrfUtil.verify(signedMessage, null, null);
		
		LOGGER.debug("전자서명 검증 결과 : " + bVerify);
		
		// 복호화된 메시지 생성
		if (!bVerify) {
			LOGGER.debug("전자서명 : " + p7VrfUtil.getErrorMsg());
			throw new Exception("COM00004004");
		}
		
		return bVerify;
	}
	
	/**
	 * 전자서명 복호화 및 검증(해쉬비교)
	 * 
	 * @param signedMessage 전자서명 결과 메시지
	 * @param orgBytes      해쉬수행 원문 데이터
	 * @return boolean      검증성공여부
	 * @throws Exception
	 */
	public boolean decryptSigned(byte[] signedMessage, byte[] orgBytes) throws Exception {
		boolean bVerify = false;
		byte[] recvData = null;
		int p7Type = 0;
	
		PKCS7Util p7VrfUtil = new PKCS7Util();
		p7Type = p7VrfUtil.getPKCS7Type(signedMessage);
		
		if (p7Type != 1 && p7Type != 2 && p7Type != 3) {
			LOGGER.debug("p7Type : " + p7Type);
			//throw new Exception("ERROR p7Type :: " + p7Type);
			throw new Exception("COM00004003");
		}
	
		// 전자서명 검증
		bVerify = p7VrfUtil.verify(signedMessage, null, null);
		
		LOGGER.debug("전자서명 검증 결과 : " + bVerify);
		
		// 복호화된 메시지 생성
		if (bVerify) {
			recvData = p7VrfUtil.getRecvData();
		} else {
			LOGGER.debug("전자서명 : " + p7VrfUtil.getErrorMsg());
			throw new Exception("COM00004004");
		}
		
		// 서명한 원문과 서명값에서 추출한 원문 일치여부 확인
		if (Arrays.equals(orgBytes, recvData)) {
			LOGGER.debug("원문 해쉬값 검증에 성공하였습니다.");
		} else {
			LOGGER.debug("전자서명문의 원문 해쉬값과 입력된 해쉬값이 일치하지 않습니다.");
			throw new Exception("COM00004005");
		}
		
		// 서명문에서 인증서를 추출하여, 인증서 검증 수행
		if("prd".equals(PropertiesUtil.getString("spring.profiles.active"))){
			
			Set certSet = p7VrfUtil.getCertificateSet();
			Iterator it = certSet.iterator(); 
			byte[] signcert2 = null;
			
			while (it.hasNext()) {
				try {
					X509Certificate cert = (X509Certificate) it.next();
					signcert2 = cert.getEncoded();
					CertUtil cu = new CertUtil(signcert2);
					
					// 인증서 유효성 검증 수행 
					if (cu.isValid("./crl")) {
					LOGGER.debug("인증서 유효성 검증 성공에 성공하였습니다.");
					} else { 
					LOGGER.debug("인증서 유효성 검증에 실패하였습니다. : " + cu.getErrorMsg());
					throw new Exception("COM00004006");
					}
						
				} catch (Exception e) {
					LOGGER.debug("인증서 유효성 검증에 실패하였습니다. : " + e.getMessage());
					throw new Exception("COM00004006");
				}
			}
		}
		
		return bVerify;
	}
	
	/**
	 * 파일 데이터
	 * 
	 * @param fileName 파일명
	 * @return byte[]  파일내용
	 * @throws Exception
	 */
	public byte[] getFileByte(String fileName) throws Exception {
		byte[] bytes = null;
		int offset = 0;
		int numRead = 0;
		BufferedInputStream bin = null;
		FileInputStream fis = null;
		
		File f = new File(fileName);
		
		try {
			bytes = new byte[(int) f.length()];
			fis = new FileInputStream(f);
			bin = new BufferedInputStream(fis);
			while (offset < bytes.length && (numRead = bin.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			
		} catch (IOException e) {
			throw new Exception("파일데이터 오류 : " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("파일데이터 오류 : " + e.getMessage());
		} finally {
			if (bin != null) {
				try {
					bin.close();
				} catch (IOException e) {
					LOGGER.debug("파일데이터 오류");
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.debug("파일데이터 오류");
				}
			}
		}
		
		return bytes;
	}
}