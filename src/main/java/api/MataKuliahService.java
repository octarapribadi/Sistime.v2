package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import ejb.MataKuliahManager;
import model.MataKuliah;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

class MataKuliahDTO{
    Long id, idTipematakuliah;
    String kodeMatakuliah, namaMatakuliah, keterangan;
    Integer sks, semester;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTipematakuliah() {
        return idTipematakuliah;
    }

    public void setIdTipematakuliah(Long idTipematakuliah) {
        this.idTipematakuliah = idTipematakuliah;
    }

    public String getKodeMatakuliah() {
        return kodeMatakuliah;
    }

    public void setKodeMatakuliah(String kodeMatakuliah) {
        this.kodeMatakuliah = kodeMatakuliah;
    }

    public String getNamaMatakuliah() {
        return namaMatakuliah;
    }

    public void setNamaMatakuliah(String namaMatakuliah) {
        this.namaMatakuliah = namaMatakuliah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}

@Path("/matakuliah")
@Produces("application/json")
public class MataKuliahService {

    @EJB
    MataKuliahManager mataKuliahManager;

    @GET
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Response getMatakuliah() {
        List<MataKuliah> matakuliahs = mataKuliahManager.findAllMataKuliah();
        List<MataKuliahDTO> mataKuliahDTOS = new ArrayList<>();
        matakuliahs.forEach(m->{
            MataKuliahDTO matkul = new MataKuliahDTO();
            matkul.setId(m.getId());
            matkul.setSemester(m.getSemester());
            matkul.setSks(m.getSks());
            matkul.setKodeMatakuliah(m.getKodeMatakuliah());
            matkul.setNamaMatakuliah(m.getNamaMatakuliah());
            matkul.setIdTipematakuliah(m.getIdTipematakuliah().getId());
            matkul.setKeterangan(m.getKeterangan());
            mataKuliahDTOS.add(matkul);
        });
        return Response.ok(mataKuliahDTOS, MediaType.APPLICATION_JSON).build();
    }


}
