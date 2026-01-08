package service;

import entity.User;
import repo.UserManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    @Inject
    UserManager userManager;

    @GET
    @Path("{username}")
    public Response getUser(@PathParam("username") String username) {
        User user = userManager.findUser(username);
        if (user != null)
            return Response.ok(user, MediaType.APPLICATION_JSON_TYPE).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
    }
}
