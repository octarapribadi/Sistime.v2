package rest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import ejb.KrsMahasiswaManager;
import ejb.UserManager;
import model.KrsMahasiswa;
import model.User;
import rest.bean.KrsMahasiswaCDI;
import rest.model.KrsMahasiswaDTO;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/krs")
@Produces(MediaType.APPLICATION_JSON)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class KrsMahasiswaService {

    @Inject
    KrsMahasiswaCDI krsMahasiswaCDI;

    @GET
    @Path("{idUser}")

    public Response getKrs(@PathParam("idUser") long idUser) {
        List<KrsMahasiswaDTO> krsMahasiswaDTOS = krsMahasiswaCDI.getKrs(idUser);
        if (krsMahasiswaDTOS.isEmpty())
            return Response.noContent().build();
        else
            return Response.ok(krsMahasiswaDTOS).build();
    }
}
