package service;

import bean.MahasiswaBean;
import dto.MahasiswaDto;
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
    @Path("/nim/{nim}")
    @RolesAllowed({"mahasiswa", "administrator"})
    public Response getMahasiswaByNim(@PathParam("nim") String nim) {
        try {
            MahasiswaDto dto = mahasiswaBean.getMahasiswaByNim(nim);
            if(dto==null)
                Response.noContent().build();
            return Response.ok(dto).build();
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaService.class).error(ex);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/iduser/{iduser}")
    @RolesAllowed({"mahasiswa", "administrator"})
    public Response getMahasiswaByUserId(@PathParam("iduser") long idUser) {
        try {
            if (!token.getClaim("sub").equals(String.valueOf(idUser))
                    && !token.getClaim("roles").equals("administrator"))
                return Response.status(Response.Status.FORBIDDEN).build();
            Mahasiswa mhs = mahasiswaBean.getMahasiswaByIdUser(idUser);
            MahasiswaDto dto = MahasiswaDto.fromEntity(mhs);
            return Response.ok(dto).build();
        } catch (NoResultException ex) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaService.class).error(ex);
            return Response.serverError().build();
        }
    }

}
