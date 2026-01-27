package service;

import com.fasterxml.jackson.annotation.JsonInclude;
import entity.MataKuliah;
import bean.MataKuliahBean;
import dto.MataKuliahDto;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/matakuliah")
@Produces(MediaType.APPLICATION_JSON)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MataKuliahService {

    @Inject
    MataKuliahBean mataKuliahBean;

    @GET
    public Response getMatakuliah() {
        List<MataKuliah> matakuliahs = mataKuliahBean.getMataKuliah();
        List<MataKuliahDto> mataKuliahDtos = new ArrayList<>();
        matakuliahs.forEach(m->{
            MataKuliahDto matkul = new MataKuliahDto();
            matkul.setId(m.getId());
            matkul.setSemester(m.getSemester());
            matkul.setSks(m.getSks());
            matkul.setKodeMatakuliah(m.getKodeMatakuliah());
            matkul.setNamaMatakuliah(m.getNamaMatakuliah());
            matkul.setTipeMataKuliah(m.getIdTipematakuliah().getTipeMatakuliah());
            matkul.setKeterangan(m.getKeterangan());
            mataKuliahDtos.add(matkul);
        });
        return Response.ok(mataKuliahDtos, MediaType.APPLICATION_JSON).build();
    }


}
