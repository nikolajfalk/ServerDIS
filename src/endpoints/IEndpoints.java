package endpoints;

import javax.ws.rs.core.Response;

/**
 * Created by jesperbruun on 17/10/2016.
 */
public interface IEndpoints {
  public Response get() throws Exception;
  public Response get(int id) throws IllegalAccessException;
  public Response create(String data) throws Exception;
  public Response edit(int id);
  public Response delete(int id);
}
