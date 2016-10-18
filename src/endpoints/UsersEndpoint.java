package endpoints; /**
 * Created by mortenlaursen on 09/10/2016.
 */

import com.google.gson.Gson;
import controllers.UserController;
import database.DBConnector;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

// The Java class will be hosted at the URI path "/users"
@Path("/users")
public class UsersEndpoint implements IEndpoints {
    UserController controller = new UserController();

    public UsersEndpoint() {
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response get() {
        if (controller.getUsers() != null) {
            return Response
                    .status(200)
                    .entity(new Gson().toJson(DBConnector.getUsers()))
                    .build();
        }
        else {
            return Response
                    //error response
                    .status(400)
                    .build();
        }
    }

    @Path("/users/{id}")
    @Produces("application/json")
    @GET
    public Response get(@PathParam("id") int userId) {
        if (controller.getUser(userId)!=null) {
            return null;
        }
        return null;
    }

    @Path("/users/{id}")
    @PUT
    public Response edit(@PathParam("id") int userId) {
        if(controller.editUser(userId)) {
            return null;
        }
        else return null;
    }

    @POST
    @Produces("application/json")
    public Response create(String data) throws Exception {
        if (controller.addUser(data)) {
            //demo to check if it returns this on post.
            return Response
                    .status(200)
                    .entity(new Gson().toJson(controller.getUsers()))
                    .build();
        }
        else return null;
    }

    @Path("/users/{id}")
    @DELETE
    public Response delete (@PathParam("id") int userId) throws SQLException {
        if(controller.deleteUser(userId)) {
            return null;
        }
        else return null;
    }
}

