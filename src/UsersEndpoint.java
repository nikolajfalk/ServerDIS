/**
 * Created by mortenlaursen on 09/10/2016.
 */
import com.google.gson.Gson;
import model.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

// The Java class will be hosted at the URI path "/users"
@Path("/users")
public class UsersEndpoint {
    ArrayList<User>allUsers = new ArrayList<>();
    Gson gson = new Gson();
    UserController controller = new UserController();

    public UsersEndpoint() {
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getAllUsers() {
        return gson.toJson(controller.getUsers(1,"demo","demo","ewokf","poejrg",true));
    }

    @Path("/getit")
    @GET
    @Produces("text/plain")
    public String getit() {
        return "Got it!";
    }

    @POST
    @Produces("application/json")
    public void addUser(String data) {
        System.out.println(data);
        User user = (gson.fromJson(data,User.class));
        controller.addUser(user);
        String s = new String(gson.toJson(controller.getUsers(0,null,null,null,null,false)));
        System.out.println(s);
        System.out.println("test");

    }
}

