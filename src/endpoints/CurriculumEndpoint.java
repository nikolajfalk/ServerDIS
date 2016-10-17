package endpoints;

import com.google.gson.Gson;
import controllers.CurriculumController;
import model.Book;
import model.Curriculum;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by magnusrasmussen on 17/10/2016.
 */
@Path("/curriculum")
public class CurriculumEndpoint implements IEndpoints{

    Gson gson = new Gson();


    CurriculumController curriculumController;


    public CurriculumEndpoint(){

        curriculumController = new CurriculumController();;
    }

    @GET
    @Path("/curriculum/{curriculumId}/books")
    @Produces("application/json")
    public Response getCurriculum(@PathParam("curriculumId") int curriculumID) throws IllegalAccessException {

        Curriculum curriculum = curriculumController.getCurriculum(curriculumID);


        return Response
                .status(200)
                .entity(new Gson().toJson(curriculum))
                .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                .build(); //kør
    }


    @GET
    @Produces("application/json")
    public Response get() throws Exception {

        ArrayList<Curriculum> curriculumArrayList = curriculumController.getCurriculums();


        return Response
                .status(200)
                .entity(new Gson().toJson(curriculumArrayList))
                .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                .build(); //kør
    }

    @GET
    @Path("/curriculum/{curriculumId}")
    @Produces("application/json")
    public Response get(@PathParam("curriculumId") int id) throws IllegalAccessException {


        Curriculum curriculum = curriculumController.getCurriculum(id);

        return Response
                .status(200)
                .entity(new Gson().toJson(curriculum))
                .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                .build(); //kør
    }


    @POST
    @Produces("application/json")
    public Response create(String data) throws Exception {

        if (curriculumController.addCurriculum(data)) {
            //demo to check if it returns this on post.
            return Response
                    .status(200)
                    .entity(new Gson().toJson(curriculumController.getCurriculums()))
                    .build();
        }
        else return null;
    }


    @PUT
    @Path("/curriculum/{curriculumId}")
    @Produces("application/json")
    public Response edit(@PathParam("curriculumId") int id) {
        if(curriculumController.editCurriculum(id)) {
            return null;
        }
        else return null;
    }



    @DELETE
    @Path("/curriculum/{curriculumId}")
    @Produces("application/json")
    public Response delete(@PathParam("curriculumId") int id) {
        if(curriculumController.deleteCurriculum(id)) {
            return null;
        }
        else return null;
    }
}
