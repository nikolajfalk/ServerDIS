/**
 * Created by mortenlaursen on 09/10/2016.
 */
import com.google.gson.Gson;

import javax.print.attribute.standard.Media;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

// The Java class will be hosted at the URI path "/users"
@Path("/users")
public class UsersEndpoint {
    ArrayList<User>allUsers;
    MySQL mysql;
    Gson gson;

    public UsersEndpoint() {
        allUsers = new ArrayList<>();
        mysql = new MySQL();
        gson = new Gson();
    }

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getAllUsers() {
        return gson.toJson(mysql.getAllUsers());
    }

    @Path("/getit")
    @GET
    @Produces("text/plain")
    public String getit() {
        return "Got it!";
    }
}

