package br.com.mark.spark.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentHelper {

	private static final EnvironmentHelper instance = new EnvironmentHelper();
	private Properties properties = new Properties();

	private EnvironmentHelper() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		properties = new Properties();
		try (InputStream inputStream = classLoader.getResourceAsStream("environment.properties")) {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Runtime initialization
	// By defualt ThreadSafe
	private static EnvironmentHelper getInstance() {
		return instance;
	}

	public static String getKey(String key) {
		String response = getInstance().properties.getProperty(key);
		return (response != null ? response.trim() : null);
//		if (response != null) return response.trim();
//		return null;
	}
}
