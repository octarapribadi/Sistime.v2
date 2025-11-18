package api;

import ejb.MahasiswaManager;
import ejb.UserManager;
import model.Mahasiswa;
import model.User;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.http.HttpRequest;
import java.util.List;

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
        if (username.equals("2244068")) {
            User user = userManager.findUser(username);
            Mahasiswa mahasiswa = mahasiswaManager.findMahasiswaByIdUser(user.getId());
            if (mahasiswa != null)
                return Response.ok(mahasiswa, MediaType.APPLICATION_JSON).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
