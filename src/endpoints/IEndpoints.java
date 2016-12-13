package endpoints;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Dette er en interface klasse, den bliver ikke brugt i systemet.
 */
public interface IEndpoints {
  public Response get() throws Exception;
  public Response get(int id) throws Exception;
  public Response create(String data) throws Exception;
  public Response edit(int id, String data) throws Exception;
  public Response delete(int id) throws Exception;
}
