import Controller.CurriculumController;
import com.google.gson.Gson;
import model.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


/**
 * Created by magnusrasmussen on 17/10/2016.
 */
@Path("/curriculum")
public class CurriculumEndpoint {

    Gson gson = new Gson();

    CurriculumController curriculumController = new CurriculumController();


    public CurriculumEndpoint(){

    }

    @GET
    @Path("/curriculum/{curriculumId}/books")
    @Produces("application/json")
    public Response getCurriculum(@PathParam("curriculumId") int curriculumID){

        ArrayList<Book> curriculum = CurriculumController.getCurriculum(curriculumID);


        return Response
                .status(200)
                .entity(new Gson().toJson(curriculum))
                .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                .build(); //kør
    }
}
