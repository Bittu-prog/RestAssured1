package pom.PayPal.Utilities.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class TestProperties 
{
	/**
	 * This method is used to intialize the property file
	 * Author Name:- Pushpraj Singh
	 */
	private Properties properties;
	public TestProperties(String path)
	{
		FileInputStream fis=null;
		try {
			fis=new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		properties=new Properties();
		try {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to fetch the data from properties file 
	 * @param key
	 * @return
	 */
	public String getDataFormatProperty(String key)
	{
		return properties.getProperty(key).trim();		
	}

}

