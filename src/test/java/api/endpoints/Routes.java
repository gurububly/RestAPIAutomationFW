package api.endpoints;

public class Routes {

    /*
     User module endpoints
     */

    public static String base_url = "https://petstore.swagger.io/v2";
    public static String user_postURL = base_url + "/user";
    public static String user_getURL = base_url + "/user/{username}";
    public static String user_updateURL = base_url + "/user/{username}";
    public static String user_deleteURL = base_url + "/user/{username}";

    /*
     Store module endpoints
     */



    /*
     pet module endpoints
     */

    public static String pet_postimageURL = base_url + "/pet/{petId}/uploadImage";
    public static String pet_postURL = base_url + "/pet";
    public static String pet_updateURL = base_url + "/pet";
    public static String pet_getURL = base_url + "/pet/{petId}";
    public static String pet_deleteURL = base_url + "/pet/{petId}";
    public static String pet_statusURL = base_url + "/pet/findByStatus";
}
