package rest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import model.MataKuliah;
import rest.bean.MataKuliahCDI;
import rest.model.MataKuliahDTO;

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
    MataKuliahCDI mataKuliahCDI;

    @GET
    public Response getMatakuliah() {
        List<MataKuliah> matakuliahs = mataKuliahCDI.getMataKuliah();
        List<MataKuliahDTO> mataKuliahDTOS = new ArrayList<>();
        matakuliahs.forEach(m->{
            MataKuliahDTO matkul = new MataKuliahDTO();
            matkul.setId(m.getId());
            matkul.setSemester(m.getSemester());
            matkul.setSks(m.getSks());
            matkul.setKodeMatakuliah(m.getKodeMatakuliah());
            matkul.setNamaMatakuliah(m.getNamaMatakuliah());
            matkul.setTipeMataKuliah(m.getIdTipematakuliah().getTipeMatakuliah());
            matkul.setKeterangan(m.getKeterangan());
            mataKuliahDTOS.add(matkul);
        });
        return Response.ok(mataKuliahDTOS, MediaType.APPLICATION_JSON).build();
    }


}
