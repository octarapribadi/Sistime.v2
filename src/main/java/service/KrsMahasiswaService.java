package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import bean.KrsMahasiswaBean;
import dto.KrsMahasiswaDto;
import entity.KrsMahasiswa;
import entity.Skedul;
import entity.User;

import javax.annotation.security.RolesAllowed;
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
    KrsMahasiswaBean krsMahasiswaBean;

    @GET
    @Path("{idUser}")
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response getKrs(@PathParam("idUser") long idUser) {
        List<KrsMahasiswaDto> krsMahasiswaDtos = krsMahasiswaBean.getKrs(idUser);
        if (krsMahasiswaDtos.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).entity("{}").build();
        else
            return Response.ok(krsMahasiswaDtos).build();
    }

    @POST
    @Path("{idUser}")
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response addKrs(@PathParam("idUser") long idUser, List<KrsMahasiswaDto> dtos) {
        List<KrsMahasiswa> krsMahasiswas = new ArrayList<>();
        dtos.forEach(dto -> {
            KrsMahasiswa krsMahasiswa = new KrsMahasiswa();

            krsMahasiswa.setUser(new User());
            krsMahasiswa.getUser().setId(dto.getIdUser());

            krsMahasiswa.setSkedul(new Skedul());
            krsMahasiswa.getSkedul().setId(dto.getIdSkedul());

            krsMahasiswa.setTipeSkedul(dto.getTipeSkedul());
            krsMahasiswa.setKeterangan(dto.getKeterangan());

            krsMahasiswas.add(krsMahasiswa);
        });
        krsMahasiswaBean.persist(krsMahasiswas);
        return Response.ok().entity(dtos).build();
    }
}
