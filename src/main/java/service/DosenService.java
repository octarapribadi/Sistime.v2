package service;

import bean.DosenBean;
import dto.DosenDto;
import entity.Dosen;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/dosen")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DosenService {

    @Inject
    DosenBean dosenBean;

    @GET
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response getDosen(){
        List<Dosen> dosens = dosenBean.getDosen();
        List<DosenDto> dtos = new ArrayList<>();
        dosens.forEach(d->{
            DosenDto dto = new DosenDto();
            dto.setId(d.getId());
            dto.setNamaDosen(d.getNamaDosen());
            dto.setAlamat(d.getAlamat());
            dto.setKeterangan(d.getKeterangan());
            dto.setNoTelepon(d.getNoTelepon());
            dto.setEmail(d.getEmail());
            dtos.add(dto);
        });
        return Response.ok().entity(dtos).build();
    }
}
