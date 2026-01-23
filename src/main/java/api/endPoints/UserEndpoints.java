package api.endPoints;

import uiApi.utilities.ConfigReader;

public class UserEndpoints {

	public static String baseUrl= ConfigReader.getProperty("baseUrl");
	//User module
	public static  String getUrlById =baseUrl+"/users/{id}";
	public static  String postUrl =baseUrl+"/users";
	public static  String UpdateUrl =baseUrl+"/users/{id}";
	public static  String deleteUrl =baseUrl+"/users/{id}";
     
}
