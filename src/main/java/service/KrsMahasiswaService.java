package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import business.KrsMahasiswaBean;
import dto.KrsMahasiswaDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/krs")
@Produces(MediaType.APPLICATION_JSON)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class KrsMahasiswaService {

    @Inject
    KrsMahasiswaBean krsMahasiswaBean;

    @GET
    @Path("{idUser}")

    public Response getKrs(@PathParam("idUser") long idUser) {
        List<KrsMahasiswaDTO> krsMahasiswaDTOS = krsMahasiswaBean.getKrs(idUser);
        if (krsMahasiswaDTOS.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
        else
            return Response.ok(krsMahasiswaDTOS).build();
    }
}
