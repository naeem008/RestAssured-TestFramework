package api.endpoints;
	
/*https://petstore.swagger.io
https://petstore.swagger.io/v2/user
https://petstore.swagger.io/v2/user/{username}
https://petstore.swagger.io/v2/user/{username}
https://petstore.swagger.io/v2/user/{username}

 * 
 * 
 * 
 */
public class Routes {
	
	 // Base URL
    public static String base_url = "https://petstore.swagger.io/v2";

    // ------------------------
    // User module endpoints
    // ------------------------

    public static String post_url   = base_url + "/user";                 // Create user (POST)
    public static String get_url    = base_url + "/user/{username}";     // Get user (GET)
    public static String update_url = base_url + "/user/{username}";     // Update user (PUT)
    public static String delete_url = base_url + "/user/{username}";     // Delete user (DELETE)

	
}
