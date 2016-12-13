package endpoints;

import Encrypters.*;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Denne klasse bliver ikke benyttet.
 */
@Path("/crypter")
public class CrypterEndpoint {
    Gson gson;
    Crypter crypter;

    public CrypterEndpoint() {
        gson = new Gson();
        crypter = new Crypter();
    }

    @GET
    @Produces("text/plain")

    public String xor() {
        return Crypter.xor();
    }


    @Path("/getit")
    @GET
    @Produces("text/plain")
    public String getit() {
        return "Got it!";
    }
}