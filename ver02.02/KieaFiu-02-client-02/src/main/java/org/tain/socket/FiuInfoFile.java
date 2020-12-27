package org.tain.socket;

import java.io.File;
import java.util.Arrays;

import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class FiuInfoFile {

	private ProjEnvParamProperties projEnvParamProperties;
	
	private String home;
	private String base;
	private String infoPath;
	private String dataPath;
	
	private String sendPath;
	private String sentPath;
	private String recvPath;
	
	private String fromPath;
	private String toPath;
	
	private String filePath;
	private String fileName;     // ~.SND
	private String fileEnvName;  // ~.env
	private String filePemName;  // ~.pem
	
	private final int unitSize = 5000;
	private byte[] bEnvData;      // file data by byte
	private int iEnvLen;          // length of file
	private String strPemData;    // pem file
	
	private int pageIdx;         // 0, 1
	private int pageSeq;         // 1, 2
	private int pageLength;      // data length to send
	private int pageSentLength;  // data total length sent
	private int pageCount;       // count of page
	private byte[] pageData;     // page data
	
	private int offBeg;        // offset of begin, size of before send
	private int offEnd;        // offset of end, size of after send
	
	//////////////////////////////////////////////////////////////////
	
	public FiuInfoFile(ProjEnvParamProperties projEnvParamProperties, String filePath, String fileName) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			log.info("======================= FiuInfo =============================");
		}
		
		if (Flag.flag) {
			this.projEnvParamProperties = projEnvParamProperties;
			this.filePath = filePath;
			this.fileName = fileName;
			this.fileEnvName = fileName + ".env";
			this.filePemName = fileName + ".pem";
			
			log.info(">>>>> filePath     = {}", filePath);
			log.info(">>>>> fileName     = {}", fileName);
			log.info(">>>>> fileEnvName  = {}", fileEnvName);
			log.info(">>>>> filePemName  = {}", filePemName);
		}
		
		if (Flag.flag) {
			this.home = this.projEnvParamProperties.getHome();
			this.base = this.projEnvParamProperties.getBase();
			this.infoPath = this.home + this.base + this.projEnvParamProperties.getInfoPath();
			this.dataPath = this.home + this.base + this.projEnvParamProperties.getDataPath();
			this.sendPath = this.home + this.base + this.projEnvParamProperties.getSendPath();
			this.sentPath = this.home + this.base + this.projEnvParamProperties.getSentPath();
			this.recvPath = this.home + this.base + this.projEnvParamProperties.getRecvPath();
			this.fromPath = this.sendPath;
			this.toPath = this.sentPath;
			
			log.info(">>>>> home     = {}", this.home);
			log.info(">>>>> base     = {}", this.base);
			log.info(">>>>> infoPath = {}", this.infoPath);
			log.info(">>>>> dataPath = {}", this.dataPath);
			log.info(">>>>> sendPath = {}", this.sendPath);
			log.info(">>>>> sentPath = {}", this.sentPath);
			log.info(">>>>> recvPath = {}", this.recvPath);
			log.info(">>>>> fromPath = {}", this.fromPath);
			log.info(">>>>> toPath   = {}", this.toPath);
		}
		
		if (Flag.flag) {
			this.bEnvData = StringTools.byteFromFile(this.filePath + File.separator + this.fileEnvName);
			this.iEnvLen = this.bEnvData.length;
			this.strPemData = StringTools.stringFromFile(this.filePath + File.separator + this.filePemName);
			log.info(">>>>> bEnvData.iEnvLen   = {}", this.iEnvLen);
		}
		
		if (Flag.flag) {
			log.info("--------------------------------------------------------------");
		}
	}
	
	//////////////////////////////////////////////////////////////////
	
	public void setInitPage() {
		log.info("KANG-20201222 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.pageIdx = 0;
			
			this.offBeg = this.pageIdx * this.unitSize;
			this.offEnd = Math.min((this.pageIdx + 1) * this.unitSize, this.iEnvLen);
			this.pageCount = (this.iEnvLen + this.unitSize - 1) / this.unitSize;
			
			this.pageLength = this.offEnd - this.offBeg;
			this.pageSentLength = this.offBeg;
			this.pageSeq = this.pageIdx + 1;
			this.pageData = Arrays.copyOfRange(this.bEnvData, this.offBeg, this.offEnd);
		}
	}
	
	public boolean setNextPage() {
		log.info("KANG-20201222 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (this.pageIdx >= (this.pageCount - 1))
				return false;
			
			this.pageIdx += 1;
			
			this.offBeg = this.pageIdx * this.unitSize;
			this.offEnd = Math.min((this.pageIdx + 1) * this.unitSize, this.iEnvLen);
			
			this.pageLength = this.offEnd - this.offBeg;
			this.pageSentLength = this.offBeg;
			this.pageSeq = this.pageIdx + 1;
			this.pageData = Arrays.copyOfRange(this.bEnvData, this.offBeg, this.offEnd);
		}
		
		return true;
	}
	
	public boolean setPrevPage() {
		log.info("KANG-20201222 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (this.pageIdx <= 0)
				return false;
			
			this.pageIdx -= 1;
			
			this.offBeg = this.pageIdx * this.unitSize;
			this.offEnd = Math.min((this.pageIdx + 1) * this.unitSize, this.iEnvLen);
			
			this.pageLength = this.offEnd - this.offBeg;
			this.pageSentLength = this.offBeg;
			this.pageSeq = this.pageIdx + 1;
			this.pageData = Arrays.copyOfRange(this.bEnvData, this.offBeg, this.offEnd);
		}
		
		return true;
	}
	
	public void printFiuInfo() {
		log.info("======================= FiuInfo =============================");
		log.info(">>>>> filePath       = {}", this.filePath);
		log.info(">>>>> fileName       = {}", this.fileName);
		log.info(">>>>> unitSize       = {}", this.unitSize);
		log.info(">>>>> iEnvLen        = {}", this.iEnvLen);
		log.info(">>>>> pageIdx        = {}", this.pageIdx);
		log.info(">>>>> pageSeq        = {}", this.pageSeq);
		log.info(">>>>> pageLength     = {}", this.pageLength);
		log.info(">>>>> pageSentLength = {}", this.pageSentLength);
		log.info(">>>>> pageCount      = {}", this.pageCount);
		log.info(">>>>> offBeg         = {}", this.offBeg);
		log.info(">>>>> offEnd         = {}", this.offEnd);
		log.info("--------------------------------------------------------------");
	}
	
	//////////////////////////////////////////////////////////////////
	
	public boolean moveFile() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		boolean isSuccess = false;
		/*
		if (Flag.flag) {
			// SND file
			File fromFile = new File(this.fromPath + "/" + this.fileName);
			File toFile = new File(this.toPath + "/" + this.fileName);
			isSuccess = fromFile.renameTo(toFile);
		}
		if (Flag.flag) {
			// env file
			File fromFile = new File(this.fromPath + "/" + this.fileEnvName);
			File toFile = new File(this.toPath + "/" + this.fileEnvName);
			isSuccess = fromFile.renameTo(toFile);
		}
		if (Flag.flag) {
			// pem file
			File fromFile = new File(this.fromPath + "/" + this.filePemName);
			File toFile = new File(this.toPath + "/" + this.filePemName);
			isSuccess = fromFile.renameTo(toFile);
		}
		*/
		
		return isSuccess;
	}
}
