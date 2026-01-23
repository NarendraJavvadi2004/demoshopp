package api.endPoints;

import uiApi.utilities.ConfigReader;

public class ProductsEndpoints {
    public static String baseUrl = ConfigReader.getProperty("baseUrl");

    // CRUD Endpoints
    public static String getAll   = baseUrl + "/products";
    public static String getById  = baseUrl + "/products/{id}";
    public static String create   = baseUrl + "/products";
    public static String update   = baseUrl + "/products/{id}";
    public static String delete   = baseUrl + "/products/{id}";
}