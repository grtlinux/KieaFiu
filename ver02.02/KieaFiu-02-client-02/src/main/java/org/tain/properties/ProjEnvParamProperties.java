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
	private String infoPath;
	private String dataPath;
	
	private String sendPath;
	private String sentPath;
	private String recvPath;
	private String fileExt;
	
	private String transactionCode;
	private String systemName;
	private String bogoCode;
	private String userId;
	private String docCode;
	private String annMsgCode;
	private String midOrgCode;
	
	private String dummy;  // null
}
