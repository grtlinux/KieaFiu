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
	
	private final int unitLength = 2000;
	private String fileData;
	private int fileLength;  // total length
	private int sentLength;  // sent length
	
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
		
		if (Flag.flag) {
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
					
					log.info("======================= FiuInfo: {} ==========================", fileEntry.getName());
					log.info(">>>>> filePath = {}", this.filePath);
					log.info(">>>>> fileName = {}", this.fileName);
					log.info("--------------------------------------------------------------");
					
					this.fileData = StringTools.stringFromFile(this.filePath + "/" + this.fileName);
					this.fileLength = this.fileData.length();
					this.sentLength = 0;
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean writeFile(String strData) {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			
		}
		
		return true;
	}
}
