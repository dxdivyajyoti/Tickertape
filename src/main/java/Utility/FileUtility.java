package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
	
	public static void setValue(String key,String Value) throws Throwable
	{
       String path = "./PriceConfiguration.properties";
		
		File file = new File(path);
		file.createNewFile();
		
		Properties pr = new Properties();
		pr.setProperty(key,Value);
		
		FileOutputStream fis = new FileOutputStream(file);
		pr.store(fis, "Input data");
		
		fis.close();
	}
	
}
