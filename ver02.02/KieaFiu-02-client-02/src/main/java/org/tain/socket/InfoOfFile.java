package org.tain.socket;

import java.io.File;

import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils.StringTools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InfoOfFile {

	public static String name = "Hello, world!!! 0";
	
	public static String sendPath = null;
	
	public static String fileName = null;
	
	public static String sentPath = null;
	
	/////////////////////////////////////////////////
	
	public static String data = null;
	
	public static int length = 0;
	public static int pos = 0;
	
	public static int sentLength = 0;
	
	/////////////////////////////////////////////////
	
	public static boolean get(String sendPath, String sentPath, String strExtention) {
		log.info("KANG-20201111 >>>>> {} {} {} {}", CurrentInfo.get(), sendPath, sentPath, strExtention);
		
		if (Flag.flag) {
			File fileSendPath = new File(sendPath);
			
			for (final File fileEntry : fileSendPath.listFiles()) {
				if (fileEntry.isFile()) {
					log.info("##### fileEntry >>>>> {} {} {} {}"
							, fileEntry.getParent()
							, fileEntry.getName()
							, fileEntry.getPath()
							, StringTools.getExtension(fileEntry.getName()));
					InfoOfFile.sendPath = sendPath;
					InfoOfFile.sentPath = sentPath;
					InfoOfFile.fileName = fileEntry.getName();
					InfoOfFile.data = StringTools.stringFromFile(fileEntry.getPath());
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean move() {
		log.info("KANG-20201111 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			File fileFrom = new File(sendPath + File.separator + fileName);
			File fileTo = new File(sentPath + File.separator + fileName);
			boolean isSuccess = fileFrom.renameTo(fileTo);
			if (isSuccess)
				log.info("SUCCESS >>>>> {} => {}", fileFrom.getPath(), fileTo.getPath());
			else
				log.info("FAIL >>>>> {} => {}", fileFrom.getPath(), fileTo.getPath());
				
			return true;
		}
		
		return false;
	}
}
