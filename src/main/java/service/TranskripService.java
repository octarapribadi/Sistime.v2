package service;

import bean.TranskripBean;
import dto.TranskripDTO;
import entity.Mahasiswa;
import org.jboss.logging.Logger;
import repo.MahasiswaManager;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/transkrip")
@Produces(MediaType.APPLICATION_JSON)
public class TranskripService {
    @Inject
    TranskripBean transkripBean;

    @Inject
    MahasiswaManager mahasiswaManager;

    @GET
    @Path("{idUser}")
    @RolesAllowed("mahasiswa")
    public Response getTranskrip(@PathParam("idUser") long idUser) {
        try {
            Mahasiswa selectedMahasiswa = mahasiswaManager.findMahasiswaByIdUser(idUser);
            if (selectedMahasiswa != null) {
                List<TranskripDTO> transkrips = transkripBean.getTranskripByIdUser(idUser);
                return Response.ok(transkrips, MediaType.APPLICATION_JSON).build();
            } else
                return Response.status(Response.Status.NOT_FOUND).entity("[]").build();
        } catch (Exception ex) {
            Logger.getLogger(TranskripService.class).error(ex.getMessage());
            return Response.serverError().build();
        }
    }

}
