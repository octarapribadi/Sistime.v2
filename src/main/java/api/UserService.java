package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import ejb.UserManager;
import model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
@Produces("application/json")
public class UserService{
    @EJB
    UserManager userManager;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Response getUser(@PathParam("username") String username){
        if(username.equals("2244068")) {
            User user = userManager.findUser(username);
            return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
        }
        else
            return Response.noContent().build();
    }
}
