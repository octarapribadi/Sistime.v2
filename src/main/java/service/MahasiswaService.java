package service;

import bean.MahasiswaBean;
import dto.MahasiswaDtoo;
import entity.Mahasiswa;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mahasiswa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MahasiswaService {

    @Inject
    MahasiswaBean mahasiswaBean;

    @Inject
    JsonWebToken token;

    @GET
    @Path("/{nim}")
    @RolesAllowed({"mahasiswa"})
    public Response getMahasiswaByNim(@PathParam("nim") String nim) {
        Mahasiswa dto = mahasiswaBean.getMahasiswaByNim(nim);
        if (!token.getClaim("sub").equals(dto.getUser().getId().toString()))
            return Response.status(Response.Status.FORBIDDEN).build();
        return Response.ok(dto).build();
    }

    @GET
    @Path("/iduser/{iduser}")
//    @RolesAllowed({"mahasiswa", "administrator"})
    public Response getMahasiswaByUserId(@PathParam("iduser") long idUser) {
        try {
            Mahasiswa mhs = mahasiswaBean.getMahasiswaByIdUser(idUser);
            MahasiswaDtoo dto = MahasiswaDtoo.fromEntity(mhs);
            return Response.ok(dto).build();
        } catch (NoResultException ex) {
            Logger.getLogger(MahasiswaService.class).error(ex);
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("mahasiswa dengan id-" + idUser + " tidak ditemukan!")
                    .build();
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaService.class).error(ex);
            return Response.serverError().build();
        }
    }

}
