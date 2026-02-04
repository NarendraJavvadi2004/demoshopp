// AuthEndpoints.java
package api.endPoints;

import uiApi.utilities.ConfigReader;

public class AuthEndpoints {
    public static String baseUrl = ConfigReader.getProperty("api.baseUrl");
    public static String login = baseUrl + "/auth/login";
}
