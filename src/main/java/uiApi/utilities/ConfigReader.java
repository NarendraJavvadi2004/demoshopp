package uiApi.utilities;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;

	public class ConfigReader {
	    private static Properties prop;
	    
  public static Properties initProperties() {
	  prop = new Properties();
	 
	  try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/config.properties")) {
		prop.load(fis);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("File Config is not found!");
	}
	  return prop;
	  
  }
   public  static String getProperty(String key) {
	   if(prop==null) {
		   initProperties();
	   }
	   
	   return prop.getProperty(key);

	}}
