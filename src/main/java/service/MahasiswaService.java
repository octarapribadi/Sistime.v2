package service;

import bean.MahasiswaBean;
import entity.Mahasiswa;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mahasiswa")
public class MahasiswaService {

    @Inject
    MahasiswaBean mahasiswaBean;

    @GET
    @Path("{nim}")
    @RolesAllowed("administrator")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMahasiswaByNim(@PathParam("nim") String nim) {
        Mahasiswa dto = mahasiswaBean.getMahasiswaByNim(nim);
        if (dto != null)
            return Response.ok(dto, MediaType.APPLICATION_JSON).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
    }
}
