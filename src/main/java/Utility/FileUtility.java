package Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class FileUtility
{
	
	public static String getValue(String key) throws Throwable
	{
		
			FileInputStream fin=new FileInputStream("./Configuration.properties");
			Properties pr=new Properties();
				pr.load(fin);
			
		String	value=pr.getProperty(key);
	      return value;
	}
	
}
