package com.nypaas.search.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class PropertiesUtil {

	public static final String DEFAULT_CONFIG = "search.properties";

	/**
	 * load prop file
	 * 
	 * @param propertyFileName
	 * @return
	 */
	public static Properties loadProperties(String propertyFileName) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			URL url = loder.getResource(propertyFileName); // 方式1：配置更新不需要重启JVM
			if (url != null) {
				in = new FileInputStream(url.getPath());
				// in = loder.getResourceAsStream(propertyFileName); // 方式2：配置更新需重启JVM
				if (in != null) {
					prop.load(in);
				}
			}
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return prop;
	}

	/**
	 * load prop file, from disk path
	 * 
	 * @param propertyFileName
	 * @return
	 */
	public static Properties loadFileProperties(String propertyFileName) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			ClassLoader loder = Thread.currentThread().getContextClassLoader();
			URL url = url = new File(propertyFileName).toURI().toURL();
			in = new FileInputStream(url.getPath());
			if (in != null) {
				prop.load(in);
			}
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return prop;
	}

	/**
	 * load prop value of string
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(Properties prop, String key) {
		return prop.getProperty(key);
	}

	/**
	 * load prop value of int
	 * 
	 * @param key
	 * @return
	 */
	public static int getInt(Properties prop, String key) {
		return Integer.parseInt(getString(prop, key));
	}

	/**
	 * load prop value of boolean
	 * 
	 * @param prop
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Properties prop, String key) {
		return Boolean.valueOf(getString(prop, key));
	}

	public static void main(String[] args) {
		Properties prop = loadProperties(DEFAULT_CONFIG);
		System.out.println(getString(prop, "name"));
	}

}