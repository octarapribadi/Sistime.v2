package service;

import dto.ErrorResponse;
import dto.TahunAjaranDto;
import entity.TahunAjaran;
import repo.TahunAjaranManajer;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tahunajaran")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TahunAjaranService {
    @Inject
    TahunAjaranManajer tahunAjaranManajer;
    @Inject
    private TranskripService transkripService;

    @GET
    @RolesAllowed({"administrator", "mahasiswa"})
    public Response getTahunAjaran() {
        List<TahunAjaran> tahunAjarans = tahunAjaranManajer.findAllTahunAjaran();
        List<TahunAjaranDto> tahunAjaranDtos = new ArrayList<>();
        tahunAjarans.forEach(t -> {
            TahunAjaranDto dto = new TahunAjaranDto();
            dto.setId(t.getId());
            dto.setKeterangan(t.getKeterangan());
            dto.setSemester(t.getSemester());
            dto.setAktif(t.getAktif());
            dto.setTahun(t.getTahun());
            tahunAjaranDtos.add(dto);
        });
        return Response.ok(tahunAjaranDtos).build();
    }

    @POST
    @RolesAllowed({"administrator"})
    @Transactional
    public Response setTahunAjaran(TahunAjaranDto dto) {
        for (TahunAjaran t : tahunAjaranManajer.findAllTahunAjaran()) {
            if (t.getTahun().equals(dto.getTahun()) && t.getSemester().equals(dto.getSemester().toUpperCase().trim())) {
                return Response.status(Response.Status.CONFLICT)
                        .entity(new ErrorResponse("Tahun dan semester sudah ada", Response.Status.CONFLICT.getStatusCode()))
                        .build();
            }
        }
        TahunAjaran tahunAjaran = new TahunAjaran();
        tahunAjaran.setTahun(dto.getTahun());
        tahunAjaran.setAktif(dto.getAktif());
        tahunAjaran.setSemester(dto.getSemester().toUpperCase());
        tahunAjaran.setKeterangan(dto.getKeterangan());

        tahunAjaranManajer.persist(tahunAjaran);

        dto.setId(tahunAjaran.getId());
        dto.setSemester(tahunAjaran.getSemester());
        return Response.status(Response.Status.CREATED)
                .entity(dto)
                .build();
    }

    @DELETE
    @Path("/id/{id}")
    @RolesAllowed({"administrator"})
    @Transactional
    public Response removeTahunAjaran(@PathParam("id") Long idTahunAjaran) {
        TahunAjaran tahunAjaran = tahunAjaranManajer.findTahunAjaranById(idTahunAjaran);
        if (tahunAjaran != null) {
            tahunAjaranManajer.remove(tahunAjaran);
            return Response.ok()
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("id tahun ajaran tidak ditemukan", Response.Status.NOT_FOUND.getStatusCode()))
                .build();
    }

    @PATCH
    @Path("/id/{id}")
    @RolesAllowed({"administrator"})
    @Transactional
    public Response mergeTahunAjaran(@PathParam("id")Long idTahunAjaran, TahunAjaranDto dto){
        TahunAjaran tahunAjaran = tahunAjaranManajer.findTahunAjaranById(idTahunAjaran);
        if(tahunAjaran==null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("id tahun ajaran tidak ditemukan",Response.Status.NOT_FOUND.getStatusCode()))
                    .build();

        tahunAjaran.setKeterangan(dto.getKeterangan()==null?null:dto.getKeterangan());
        tahunAjaran.setSemester(dto.getSemester()==null?null:dto.getSemester().toUpperCase());
        tahunAjaran.setAktif(dto.getAktif()==null?null:dto.getAktif());
        tahunAjaran.setTahun(dto.getTahun()==null?null:dto.getTahun());
        tahunAjaranManajer.merge(tahunAjaran);

        return Response.ok().build();
    }

}
