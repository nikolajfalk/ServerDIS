package endpoints;

import Cryptor;

import database.DBConnector;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.Random;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

/**
 * Created by aleksanderkristiansen on 19/10/2016.
 */
@Path("/authentication")
public class AuthenticationEndpoint {

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            DBConnector db = new DBConnector;

            if(db.authenticate(username, password) ==1){

                String token = buildToken("abcdefghijklmnopqrstuvxyz1234567890@&%!?", 25);

                db.addToken(token, username);


            }

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private void authenticate(String username, String password) throws Exception
        //
        //Authenticate against a database, LDAP, file or whatever
        //Throw an Exception if the credentials are invalid
        //
    }

    private static String buildToken(String chars, int length) {
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < length; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();
    }
}