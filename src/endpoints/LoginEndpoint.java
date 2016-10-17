package endpoints;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by aleksanderkristiansen on 17/10/2016.
 */
public class LoginEndpoint {

    String username = "username1234";
    String password = "password1234";

    String usernameAndPassword = username + ":" + password;
    String authorizationHeaderName = "Authorization";
    String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString
            (usernameAndPassword.getBytes());


}
