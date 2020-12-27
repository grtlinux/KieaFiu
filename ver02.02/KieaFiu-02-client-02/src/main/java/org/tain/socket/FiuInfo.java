package org.tain.socket;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	private String home;
	private String base;
	private String sendPath;
	
	private int lstIndex = -1;
	private List<FiuInfoFile> lstFiuInfoFile = new ArrayList<>();
	
	//////////////////////////////////////////////////////////////////
	
	public boolean setFiuInfoFiles() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.lstIndex = -1;
			this.lstFiuInfoFile.clear();
		}
		
		if (Flag.flag) {
			this.home = this.projEnvParamProperties.getHome();
			this.base = this.projEnvParamProperties.getBase();
			this.sendPath = this.home + this.base + this.projEnvParamProperties.getSendPath();
			
			log.info(">>>>> home     = {}", this.home);
			log.info(">>>>> base     = {}", this.base);
			log.info(">>>>> sendPath = {}", this.sendPath);
		}
		
		if (Flag.flag) {
			File path = new File(this.sendPath);
			File[] files = path.listFiles();
			Arrays.sort(files);
			for (final File fileEntry : files) {
				if (fileEntry.isFile()) {
					if (!StringTools.isExtension(fileEntry.getName(), "SND"))
						continue;
					
					if (Flag.flag) log.info(">>>>> [{}] [{}]", fileEntry.getParent(), fileEntry.getName());
					
					this.lstFiuInfoFile.add(new FiuInfoFile(this.projEnvParamProperties, fileEntry.getParent(), fileEntry.getName()));
				}
			}
		}
		
		if (this.lstFiuInfoFile.size() != 0) {
			log.info(">>>>> FIU lstIndex = {}, lstFiuInfoFiles.size() = {}", this.lstIndex, this.lstFiuInfoFile.size());
			return true;
		} else {
			log.info(">>>>> FIU INFO.getFile() = There's no file to transfer on FIU.");
			return false;
		}
	}
	
	public boolean setFiuInfoFileNext() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.lstIndex ++;
			if (this.lstIndex < this.lstFiuInfoFile.size()) {
				log.info(">>>>> FIU lstIndex = {}, lstFiuInfoFiles.size() = {}", this.lstIndex, this.lstFiuInfoFile.size());
				return true;
			}
		}
		return false;
	}
	
	public FiuInfoFile getFiuInfoFile() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (this.lstIndex < this.lstFiuInfoFile.size()) {
				return this.lstFiuInfoFile.get(this.lstIndex);
			}
		}
		return null;
	}
}
