package service;

import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hash")
public class HashService {

    @POST
    public Response hash(String message){
        String hashWithSalt = BCrypt.hashpw(message, BCrypt.gensalt(10));
        return Response.ok(hashWithSalt).build();
    }
}
