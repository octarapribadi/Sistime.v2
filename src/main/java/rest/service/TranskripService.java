package rest.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import repo.KrsMahasiswaManager;
import repo.MahasiswaManager;
import repo.Nilai2Manager;
import model.*;
import org.jboss.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

class Transkrip {
    long krsId;
    String kodeMatkul, matkul;
    int sks;
    Map<String, Double> nilais = new HashMap<>();

    public long getKrsId() {
        return krsId;
    }

    public void setKrsId(long krsId) {
        this.krsId = krsId;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public void setKodeMatkul(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public Map<String, Double> getNilais() {
        return nilais;
    }

    public void setNilais(Map<String, Double> nilais) {
        this.nilais = nilais;
    }
}

@Path("/transkrip")
@Produces(MediaType.APPLICATION_JSON)
public class TranskripService {

    @EJB
    MahasiswaManager mahasiswaManager;

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    @EJB
    Nilai2Manager nilai2Manager;

    @GET
    @Path("{nim}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Response getTranskrip(@PathParam("nim") String nim) {
        try {
            Mahasiswa selectedMahasiswa = mahasiswaManager.findMahasiswaByNim(nim);
            if (selectedMahasiswa != null) {
                List<Transkrip> transkrips = new ArrayList<>();
                List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(selectedMahasiswa.getUser().getId());
                List<Nilai2> nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
                for (KrsMahasiswa krs : krsMahasiswas) {
                    Transkrip transkrip = new Transkrip();
                    transkrip.krsId = krs.getId();
                    transkrip.kodeMatkul = krs.getSkedul().getIdMatakuliah().getKodeMatakuliah();
                    transkrip.matkul = krs.getSkedul().getIdMatakuliah().getNamaMatakuliah();
                    transkrip.sks = krs.getSkedul().getIdMatakuliah().getSks();
                    for (Nilai2 n : nilai2s) {
                        if (Objects.equals(n.getKrsMahasiswa().getId(), krs.getId())) {
                            transkrip.nilais.put(n.getJenisNilai().getJenis(), n.getNilai());
                        }
                    }
                    transkrips.add(transkrip);
                }
                return Response.ok(transkrips, MediaType.APPLICATION_JSON).build();
            }
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception ex) {
            Logger.getLogger(TranskripService.class).error(ex.getMessage());
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

}
