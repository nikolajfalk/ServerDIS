package endpoints; /**
 * Created by mortenlaursen on 09/10/2016.
 */

import com.google.gson.Gson;
import controllers.BookController;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

// The Java class will be hosted at the URI path "/Book"
@Path("/Book")
public class BookEndpoint implements IEndpoints {
    BookController controller = new BookController();

    public BookEndpoint() {
    }

    // The Java method will process HTTP GET requests

    @GET
    @Produces("application/json")
    public Response get() throws Exception {
        if (controller.getBooks()!=null) {
            return Response
                    .status(200)
                    .entity(new Gson().toJson(controller.getBooks()))
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .build();
        }
    }


    @Path("/{id}")
    @Produces("application/json")
    @GET
    public Response get(@PathParam("id") int bookId) {
        if (controller.getBook(bookId)!=null) {
            return Response
                    .status(200)
                    .entity(new Gson().toJson(controller.getBook(bookId)))
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .build();
        }
    }

    //Not created yet
    @Path("/{id}")
    @PUT
    public Response edit(@PathParam("id") int bookId) {
        if(controller.editBook(bookId)) {
            return null;
        }
        else return null;
    }

    @POST
    @Produces("application/json")
    public Response create(String data) throws SQLException {
        if (controller.addBook(data)) {
            return Response
                    .status(200)
                    .entity("new user")
                    .build();
        }
        else {
            return Response
                    .status(400)
                    .build();
        }
    }

    @Path("/Book/{id}")
    @DELETE
    public Response delete (@PathParam("id") int bookId) throws SQLException {
        if(controller.deleteBook(bookId)) {
            return null;
        }
        else return null;
    }
}

