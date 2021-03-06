package org.tain.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "proj-env.param")
@Data
public class ProjEnvParamProperties {

	private String name;  // default
	
	private String home;
	private String base;
	
	private String gpkiPath;
	private String infoPath;
	private String dataPath;
	
	private String sendPath;
	private String sentPath;
	private String recvPath;
	
	private String gpkiCertPath;
	private String gpkiAlgorithm;
	private String licensePath;
	private String certPath;
	private String certKeyPath;
	private String certPassword;
	
	private String dummy;  // null
}
