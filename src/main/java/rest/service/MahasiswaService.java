package rest.service;

import ejb.MahasiswaManager;
import ejb.UserManager;
import model.Mahasiswa;
import model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mahasiswa")
@Produces("application/json")
public class MahasiswaService {
    @EJB
    MahasiswaManager mahasiswaManager;

    @EJB
    UserManager userManager;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getMahasiswa(@PathParam("username") String username) {
        User user = userManager.findUser(username);
        if (user != null) {
            Mahasiswa mahasiswa = mahasiswaManager.findMahasiswaByIdUser(user.getId());
            if (mahasiswa != null)
                return Response.ok(mahasiswa, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
