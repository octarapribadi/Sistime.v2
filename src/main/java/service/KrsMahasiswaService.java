package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import bean.KrsMahasiswaBean;
import dto.KrsMahasiswaDto;
import entity.KrsMahasiswa;
import entity.Skedul;
import entity.User;

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
        List<KrsMahasiswaDto> krsMahasiswaDtos = krsMahasiswaBean.getKrs(idUser);
        if (krsMahasiswaDtos.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
        else
            return Response.ok(krsMahasiswaDtos).build();
    }

    @POST
    @Path("{idUser}")
    public Response addKrs(@PathParam("idUser")long idUser, KrsMahasiswaDto dto){
        KrsMahasiswa krsMahasiswa = new KrsMahasiswa();
        krsMahasiswa.setUser(new User());
        krsMahasiswa.setSkedul(new Skedul());
        krsMahasiswa.setTipeSkedul(dto.getTipeSkedul());

        krsMahasiswaBean.persist(krsMahasiswa);

        return Response.ok().build();
    }
}
