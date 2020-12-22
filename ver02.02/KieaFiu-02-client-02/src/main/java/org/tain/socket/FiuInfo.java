package org.tain.socket;

import java.io.File;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tain.properties.ProjEnvParamProperties;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@Setter
@Getter
@Slf4j
public class FiuInfo {

	@Autowired
	private ProjEnvParamProperties projEnvParamProperties;
	
	private String name = "Name of FiuInfo";
	
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
	private String fileName;  // ~.env
	
	private final int unitLength = 4000;
	private String fileData;
	private int fileLength;  // total length
	private int sentLength;  // sent length
	private int totPage;     // 총 패이지 갯수
	private int idxPage;     // 현재 페이지 인덱스
	private int lenPage;     // 현재 갯수
	
	private final int unitSize = 400;
	private String pemData;    // pem file
	private byte[] bFileData;  // file data by byte
	private int lenFile;       // length of file
	private int idxCurr;       // index of current with unitSize
	private int idxMax;        // index of max with unitSize
	private int offBeg;        // offset of begin, size of before send
	private int offEnd;        // offset of end, size of after send
	private boolean flgRemain; // flag whether remaining bytes or not
	
	//////////////////////////////////////////////////////////////////
	
	public void set() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
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
	}
	
	public boolean getFile() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (!Flag.flag) {
			/*
			File path = new File(this.fromPath);
			File[] files = path.listFiles();
			Arrays.sort(files);
			for (final File fileEntry : files) {
				if (fileEntry.isFile()) {
					if (Flag.flag) log.info(">>>>> [{}] [{}]", fileEntry.getParent(), fileEntry.getName());
					
					if (!StringTools.isExtension(fileEntry.getName(), "env"))
						continue;
					
					this.filePath = fileEntry.getParent();
					this.fileName = fileEntry.getName();
					this.fileData = StringTools.stringFromFile(this.filePath + "/" + this.fileName);
					this.fileLength = this.fileData.length();
					this.sentLength = 0;
					this.totPage = (this.fileLength + this.unitLength - 1) / this.unitLength;
					this.idxPage = 0;
					
					log.info("======================= FiuInfo: {} ==========================", fileEntry.getName());
					log.info(">>>>> filePath   = {}", this.filePath);
					log.info(">>>>> fileName   = {}", this.fileName);
					//log.info(">>>>> fileData = {}", this.fileData); unitLength
					log.info(">>>>> fileLength = {}", this.fileLength);
					log.info(">>>>> unitLength = {}", this.unitLength);
					log.info(">>>>> totPage    = {}", this.totPage);
					log.info(">>>>> sentLength = {}", this.sentLength);
					log.info(">>>>> idxPage    = {}", this.idxPage);
					log.info("--------------------------------------------------------------");
					
					return true;
				}
			}
			*/
		}
		
		if (Flag.flag) {
			/*
				private final int unitSize = 4000;
				private byte[] bFileData;  // file data by byte
				private int lenFile;       // length of file
				private int idxCurr;       // index of current with unitSize
				private int idxMax;        // index of max with unitSize
				private int offBeg;        // offset of begin
				private int offEnd;        // offset of end
				private boolean flgRemain; // flag whether remaining bytes or not
			*/
			File path = new File(this.fromPath);
			File[] files = path.listFiles();
			Arrays.sort(files);
			for (final File fileEntry : files) {
				if (fileEntry.isFile()) {
					if (Flag.flag) log.info(">>>>> [{}] [{}]", fileEntry.getParent(), fileEntry.getName());
					
					if (!StringTools.isExtension(fileEntry.getName(), "env"))
						continue;
					
					this.filePath = fileEntry.getParent();
					this.fileName = fileEntry.getName();
					
					this.bFileData = StringTools.byteFromFile(this.filePath + File.separator + this.fileName);
					this.lenFile = this.bFileData.length;
					this.idxCurr = 0;
					this.idxMax = (this.lenFile + this.unitSize - 1) / this.unitSize;
					this.offBeg = this.idxCurr * this.unitSize;
					this.offEnd = Math.min((this.idxCurr + 1) * this.unitSize, this.lenFile);
					this.flgRemain = true;
					
					log.info("======================= FiuInfo: {} ==========================", fileEntry.getName());
					log.info(">>>>> filePath   = {}", this.filePath);
					log.info(">>>>> fileName   = {}", this.fileName);
					//log.info(">>>>> fileData = {}", this.fileData); unitLength
					log.info(">>>>> lenFile    = {}", this.lenFile);
					log.info(">>>>> idxCurr    = {}", this.idxCurr);
					log.info(">>>>> idxMax     = {}", this.idxMax);
					log.info(">>>>> offBeg     = {}", this.offBeg);
					log.info(">>>>> offEnd     = {}", this.offEnd);
					log.info(">>>>> flgRemain  = {}", this.flgRemain);
					log.info("--------------------------------------------------------------");
					
					return true;
				}
			}
		}
		
		if (Flag.flag) {
			log.info(">>>>> FIU INFO.getFile() = There's no file to transfer on FIU.");
		}
		
		return false;
	}
	
	public String getCurrentPage() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (this.idxPage >= this.totPage)
			return null;
		
		String strReturn = null;
		if (Flag.flag) {
			int beg = this.idxPage * this.unitLength;
			this.idxPage ++;
			int end = Math.min(this.idxPage * this.unitLength, this.fileLength);
			strReturn = this.fileData.substring(beg, end);
			this.lenPage = end - beg;
		}
		
		return strReturn;
	}
	
	public boolean writeFile(String strData) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			
		}
		
		return true;
	}
	
	public boolean moveFile() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		boolean isSuccess = false;
		if (Flag.flag) {
			File fromFile = new File(this.fromPath + "/" + this.fileName);
			File toFile = new File(this.toPath + "/" + this.fileName);
			isSuccess = fromFile.renameTo(toFile);
		}
		
		return isSuccess;
	}
}
