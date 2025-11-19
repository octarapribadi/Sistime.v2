package rest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import ejb.MataKuliahManager;
import model.MataKuliah;
import model.TipeMataKuliah;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

class MataKuliahDTO{
    Long id;
    TipeMataKuliah tipeMataKuliah;
    String kodeMatakuliah, namaMatakuliah, keterangan;
    Integer sks, semester;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipeMataKuliah getTipeMataKuliah() {
        return tipeMataKuliah;
    }

    public void setTipeMataKuliah(TipeMataKuliah tipeMataKuliah) {
        this.tipeMataKuliah = tipeMataKuliah;
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
@Produces(MediaType.APPLICATION_JSON)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MataKuliahService {

    @EJB
    MataKuliahManager mataKuliahManager;

    @GET
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
            matkul.setTipeMataKuliah(m.getIdTipematakuliah()!=null?m.getIdTipematakuliah():null);
            matkul.setKeterangan(m.getKeterangan());
            mataKuliahDTOS.add(matkul);
        });
        return Response.ok(mataKuliahDTOS, MediaType.APPLICATION_JSON).build();
    }


}
