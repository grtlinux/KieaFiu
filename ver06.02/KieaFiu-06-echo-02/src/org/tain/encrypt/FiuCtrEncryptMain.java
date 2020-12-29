package org.tain.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

public class FiuCtrEncryptMain {

	private Properties prop;
	
	private String homePath;
	private String basePath;
	private String confPath;
	
	private String sendPath;
	
	public static void main(String[] args) throws Exception {
		FiuCtrEncryptMain main = new FiuCtrEncryptMain();
		main.process();
	}
	
	/////////////////////////////////////////////////////////////////////
	
	private FiuCtrEncryptMain() throws Exception {
		String ctrPropFile = System.getProperty("ctr.prop.file");
		
		this.prop = new Properties();
		this.prop.load(new FileInputStream(ctrPropFile));
		
		this.homePath = this.prop.getProperty("ctr.home");
		this.basePath = this.homePath + File.separator + this.prop.getProperty("ctr.base");
		this.confPath = this.basePath + File.separator + this.prop.getProperty("ctr.conf");
		
		this.sendPath = this.prop.getProperty("ctr.send");
		
		System.out.println("-Dctr.prop.file = " + ctrPropFile);
		System.out.println("ctr.home = " + this.homePath);
		System.out.println("ctr.base = " + this.basePath);
		System.out.println("ctr.conf = " + this.confPath);
		System.out.println("ctr.send = " + this.sendPath);
	}
	
	private void process() throws Exception {
		File path = new File(this.sendPath);
		File[] files = path.listFiles();
		int sizFiles = files.length;
		Arrays.sort(files);
		for (int i=0; i < sizFiles; i++) {
			File fileEntry = files[i];
			if (fileEntry.isFile()) {
				String fileName = fileEntry.getName();
				int sizFileName = fileName.length();
				if (sizFileName < 5)
					continue;
				
				String fileExt = fileName.substring(sizFileName - 4);
				if (!".SND".equals(fileExt))
					continue;
				
				System.out.println("[FILE] -> " + fileName);
				doJob(fileEntry);
			}
		}
	}
	
	private void doJob(File fileEntry) {
		System.out.println("\t- doJob");
		encryptFile(fileEntry);
		makePem(fileEntry);
	}
	
	private void encryptFile(File fileEntry) {
		System.out.println("\t\t- doJob");
		
	}
	
	private void makePem(File fileEntry) {
		System.out.println("\t\t- doJob");
		
	}
}
