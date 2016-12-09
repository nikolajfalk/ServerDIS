package endpoints;

import Encrypters.*;
import com.google.gson.Gson;
import controllers.TokenController;
import controllers.UserController;
import model.User;
import model.UserLogin;
import endpoints.CORSFilter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Endpoint klasse indeholder alle user-endpoints. Klassen står for kald af metoder fra controller-klasse
 * og kommunikation med klient.
 */

@Path("/user")
public class UsersEndpoint  {
    UserController controller = new UserController();
    TokenController tokenController = new TokenController();

    public UsersEndpoint() {
    }

    /**
     * Metode til at hente alle brugere i systemet.
     * @return
     * @throws IllegalAccessException
     */
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("application/json")

    public Response get(@HeaderParam("authorization") String authToken) throws SQLException {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
            if (controller.getUsers() != null) {
                return Response
                        .status(200)
                        .entity(new Gson().toJson(Crypter.encryptDecryptXOR(new Gson().toJson(controller.getUsers()))))
                        .build();
            } else {
                return Response
                        //error response
                        .status(400)
                        .entity("{\"message\":\"failed\"}")
                        .build();
            }
        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();

    }

    /**
     * Metode til at hente en specfik bruger.
     * @param authToken
     * @param userId
     * @return
     * @throws SQLException
     */
    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Response get(@HeaderParam("authorization") String authToken, @PathParam("id") int userId) throws SQLException {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
            if (controller.getUser(userId)!=null) {
                return Response
                        .status(200)
                        .entity(new Gson().toJson(Crypter.encryptDecryptXOR(new Gson().toJson(controller.getUser(userId)))))
                        .build();
            }
            return Response
                    .status(400)
                    .entity("{\"message\":\"failed\"}")
                    .build();

        } else return Response
                .status(400)
                .entity("{\"message\":\"failed\"}")
                .build();
    }

    /**
     * Metoden til ændre en bruger
     * @param authToken
     * @param id
     * @param data
     * @return
     * @throws SQLException
     */
    @PUT
    @Path("/{Id}")
    @Produces("application/json")

    public Response edit(@HeaderParam("authorization") String authToken, @PathParam("Id") int id, String data) throws SQLException {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
           // String s = new Gson().fromJson(data,String.class);
            //String decrypt = Crypter.encryptDecryptXOR(s);
            if (controller.getUser(id) != null) {
                if (controller.editUser(id, data)) {
                    return Response
                            .status(200)
                            .entity("{\"message\":\"Success! User edited\"}")
                            .build();
                } else {
                    return Response
                            .status(400)
                            .entity("{\"message\":\"failed\"}")
                            .build();
                }
            } else {
                return Response
                        .status(400)
                        .entity("{\"message\":\"failed. No such user\"}")
                        .build();
            }

        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();


    }

    /**
     * Metode til at oprette en bruger.
     * @param data
     * @return
     * @throws Exception
     */
    @POST
    @Produces("application/json")
    public Response create(String data) throws Exception {
        //String s = new Gson().fromJson(data,String.class);
        //String decrypt = Crypter.encryptDecryptXOR(s);
        if (controller.addUser(data)) {
            //demo to check if it returns this on post.
            return Response
                    .status(200)
                    .entity("{\"message\":\"Success! User added\"}")
                    .build();
        }
        else return Response.status(400).entity("{\"message\":\"failed\"}").build();
    }

    /**
     * Metode til at ændre en bruger.
     * @param authToken
     * @param userId
     * @return
     * @throws SQLException
     */
    @Path("delete/{id}")
    @PUT
    @Produces("application/json")
    public Response delete (@HeaderParam("authorization") String authToken, @PathParam("id") int userId) throws SQLException {

        User user = tokenController.getUserFromTokens(authToken);

        if (user != null){
            if(controller.deleteUser(userId)) {
                return Response.status(200).entity("{\"message\":\"Success! User deleted\"}").build();
            }
            else return Response.status(400).entity("{\"message\":\"failed\"}").build();
        }else return Response.status(400).entity("{\"message\":\"failed\"}").build();

    }

    /**
     * Metode til at logge ind.
     * @param data
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/login")
    @Produces("application/json")
    public Response login(String data) throws SQLException {
        String decrypt = Crypter.encryptDecryptXOR(data); //Fjernes når din klient krypterer.
        decrypt = Crypter.encryptDecryptXOR(decrypt);

        UserLogin userLogin = new Gson().fromJson(decrypt, UserLogin.class);

        String token = tokenController.authenticate(userLogin.getUsername(), userLogin.getPassword());

        if (token != null) {
            //demo to check if it returns this on post.
            return Response
                .status(200)
                .entity(new Gson().toJson(token))
                .build();
        } else return Response
            .status(401)
            .build();
    }

    /**
     * Metode til at lukke ud.
     * @param data
     * @return
     * @throws SQLException
     */
    @POST
    @Path("/logout")
    public Response logout (String data) throws SQLException {
        if(tokenController.deleteToken(data)) {
            return Response
                    .status(200)
                    .entity("Success!")
                    .build();

        } else return Response
                    .status(400)
                    .entity("Failure")
                    .build();
    }

    /**
     * Metode til hente den bruger som er logget ind.
     * @param authToken
     * @return
     * @throws SQLException
     */

    @Path("/currentuser")
    @Produces("application/json")
    @GET
    public Response getUser(@HeaderParam("authorization") String authToken) throws SQLException {
        User user = tokenController.getUserFromTokens(authToken);

        int userId = user.getUserID();

        if(controller.getUser(userId) != null) {

            return Response
                .status(200)
                .entity(new Gson().toJson(Crypter.encryptDecryptXOR(new Gson().toJson(user))))
                .build();

         } else return Response
            .status(400)
            .entity("{\"message\":\"failed\"}")
            .build();
    }
}

