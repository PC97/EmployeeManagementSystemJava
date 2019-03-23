package com.st.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetDbCredentials {
    private InputStream inputStream;
	private String addr, id, pass;
 
    public String getAddr() {
		return addr;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	GetDbCredentials() throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			addr = prop.getProperty("address");
			id = prop.getProperty("user");
			pass = prop.getProperty("pass");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
}
