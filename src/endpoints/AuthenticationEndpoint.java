package endpoints;

import Cryptor;

import Encrypters.Crypter;
import database.DBConnector;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
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
                                     @FormParam("password") String password) throws SQLException {



            // Authenticate the user using the credentials provided
            DBConnector db = new DBConnector;

            User foundUser = db.authenticate(username, password);
            if(foundUser != null) {

                String token = Crypter.buildToken("abcdefghijklmnopqrstuvxyz1234567890@&%!?", 25);

                db.addToken(token, foundUser.getId());

            }

            // Issue a token for the user
            String token = issueToken(foundUser.getId());

            // Return the token on the response



    }


}