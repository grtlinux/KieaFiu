package org.tain.properties;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesMain {

	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
		for(Enumeration<?> en = props.propertyNames(); en.hasMoreElements();) {
			String key = (String)en.nextElement();
			String value = props.getProperty(key);
			System.out.println("## " + key + "=" + value);
		}
		System.out.println(">>>>> user.dir      = " + String.valueOf(System.getProperty("user.dir")));
		System.out.println(">>>>> user.name     = " + String.valueOf(System.getProperty("user.name")));
		
		Properties properties = new Properties();
		properties.load(new FileInputStream("./echo.properties"));
		System.out.println(">>>>> name          = " + String.valueOf(properties.getProperty("name")));
		System.out.println(">>>>> server.prompt = " + String.valueOf(properties.getProperty("server.prompt")));
		System.out.println(">>>>> client.prompt = " + String.valueOf(properties.getProperty("client.prompt")));
	}
}
