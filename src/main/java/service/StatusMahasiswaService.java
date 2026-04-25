package service;

import bean.StatusMahasiswaBean;
import dto.ErrorResponse;
import dto.StatusMahasiswaDto;
import entity.StatusMahasiswa;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/statusmahasiswa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StatusMahasiswaService {

    @Inject
    StatusMahasiswaBean statusMahasiswaBean;

    @GET
    @Path("/iduser/{iduser}")
    public Response getStatusMahasiswaByIdUser(@PathParam("iduser") Long idUser){
        StatusMahasiswa statusMahasiswa = statusMahasiswaBean.getStatusMahasiswaByIdUser(idUser);

        if(statusMahasiswa!=null){
            StatusMahasiswaDto dto = new StatusMahasiswaDto();
            dto.setId(statusMahasiswa.getId());
            dto.setKeterangan(statusMahasiswa.getKeterangan());
            dto.setIdUser(statusMahasiswa.getUser().getId());
            dto.setKodeKelas(statusMahasiswa.getKodeKelas().getKodeKelas());
            dto.setStatus(statusMahasiswa.getStatus());
            dto.setNim(statusMahasiswa.getNim());
            return Response.ok().entity(dto).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Userid tidak ditemukan!", Response.Status.NOT_FOUND.getStatusCode()))
                    .build();
    }

}
