package service;

import org.mindrot.jbcrypt.BCrypt;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

class HashRequest{
    String message;
    String hash;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}

@Path("/testhash")
public class HashTestService {
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response hash(HashRequest hashRequest){
        boolean status = BCrypt.checkpw(hashRequest.message, hashRequest.hash);
        Map<String, Boolean> entity= new HashMap<>();
        entity.put("status",status);
        return Response.ok().entity(entity).build();

    }
}
