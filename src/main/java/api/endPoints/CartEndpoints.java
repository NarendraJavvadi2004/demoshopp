// CartEndpoints.java
package api.endPoints;

import uiApi.utilities.ConfigReader;

public class CartEndpoints {
    public static String baseUrl = ConfigReader.getProperty("api.baseUrl");
    public static String getAll = baseUrl + "/carts";
    public static String getById = baseUrl + "/carts/{id}";
    public static String create = baseUrl + "/carts";
    public static String update = baseUrl + "/carts/{id}";
    public static String delete = baseUrl + "/carts/{id}";
}
