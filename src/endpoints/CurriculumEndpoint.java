package endpoints;

import com.google.gson.Gson;
import controllers.CurriculumController;
import model.Book;
import model.Curriculum;
import Encrypters.Crypter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by magnusrasmussen on 17/10/2016.
 */
@Path("/curriculum")
public class CurriculumEndpoint implements IEndpoints{

    Gson gson = new Gson();

    CurriculumController curriculumController;


    public CurriculumEndpoint(){

        curriculumController = new CurriculumController();
    }


    /**
     * Metode til at hente alle bøgerne på et semester
     * @param curriculumID
     * @return
     * @throws IllegalAccessException
     */
    @GET
    @Path("/curriculum/{curriculumId}/books")
    @Produces("application/json")
    public Response getCurriculum(@PathParam("curriculumId") int curriculumID) throws IllegalAccessException {

        if (curriculumController.getCurriculums() != null) {
            return Response
                    .status(200)
                    .entity(new Gson().toJson(curriculumController.getCurriculum(curriculumID)))
                    .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                    .build(); //kør
        } else {
            return Response
                //error response
                .status(400)
                .build();
    }
    }

    /**
     * Metode til at hente alle semestre
     * @return
     * @throws IllegalAccessException
     */
    @GET
    @Produces("application/json")
    public Response get() throws IllegalAccessException {

        if (curriculumController.getCurriculums() != null) {
            return Response
                    .status(200)
                    .entity(curriculumController.getCurriculums())
                    .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                    .build(); //kør
        } else {
            return Response
                    //error response
                    .status(400)
                    .build();
        }
    }

    /**
     * Metode til at hente et bestemt semester
     * @param id
     * @return
     * @throws IllegalAccessException
     */
    @GET
    @Path("/curriculum/{curriculumId}")
    @Produces("application/json")
    public Response get(@PathParam("curriculumId") int id) throws IllegalAccessException {


        if (curriculumController.getCurriculums() != null) {
            return Response
                    .status(200)
                    .entity(new Gson().toJson(curriculumController.getCurriculum(id)))
                    .header("Access-Control-Allow-Origin", "*") //Skal måske være der
                    .build(); //kør
        } else {
            return Response
                    //error response
                    .status(400)
                    .build();
        }
    }

    /**
     * Metode til at oprette nyt semester
     * @param data
     * @return
     * @throws Exception
     */
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
        else return Response.status(400).entity("{\"message\":\"Failed.\"}").build();

    }


    /**
     * Metode til at ændre et semester
     * @param id
     * @return
     */
    @PUT
    @Path("/curriculum/{curriculumId}")
    @Produces("application/json")
    public Response edit(@PathParam("curriculumId") int id) {
        if(curriculumController.editCurriculum(id)) {
            return null;
        }
        else return null;
    }


    /**
     * Metode til at slette et semester
     * @param id
     * @return
     * @throws SQLException
     */
    @DELETE
    @Path("/curriculum/{curriculumId}")
    @Produces("application/json")
    public Response delete(@PathParam("curriculumId") int id) throws SQLException {
        if(curriculumController.deleteCurriculum(id)) {
            return Response.status(200).entity("{\"message\":\"Curriculum was deleted\"}").build();
        }
        else return Response.status(400).entity("{\"message\":\"Failed. Curriculum was not deleted\"}").build();
    }
}
