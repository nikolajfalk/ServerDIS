package endpoints;

import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by jesperbruun on 17/10/2016.
 */
public interface IEndpoints {
  public Response get();
  public Response get(int id);
  public Response create(String data) throws Exception;
  public Response edit(int id);
  public Response delete(int id) throws SQLException;
}
