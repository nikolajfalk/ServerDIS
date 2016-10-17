package endpoints; /**
 * Created by mortenlaursen on 09/10/2016.
 */

import com.google.gson.Gson;
import controllers.UserController;
//import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

// The Java class will be hosted at the URI path "/users"
@Path("/users")
public class UsersEndpoint {
    UserController controller = new UserController();

    public UsersEndpoint() {
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getAllUsers() {
        return controller.getUsers();
    }

    @Path("/users/{id}")
    @Produces("application/json")
    @GET
    public String getUser(@PathParam("id") int userId) {
        return controller.getUser(userId);
    }

    @POST
    @Produces("application/json")
    public void addUser(String data) {
        System.out.println(data);
        controller.addUser(data);
    }
}

