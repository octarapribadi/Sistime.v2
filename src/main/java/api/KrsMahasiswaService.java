package api;

import bean.KrsMahasiswaBean;
import com.fasterxml.jackson.annotation.JsonInclude;
import ejb.KrsMahasiswaManager;
import ejb.UserManager;
import model.KrsMahasiswa;
import model.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

class KrsMahasiswaDTO{
    Long idKrs, idSkemaKrs;
    String kodeMatakuliah, namaMatakuliah, namaDosen;
    public Long getIdKrs() {
        return idKrs;
    }

    public void setIdKrs(Long idKrs) {
        this.idKrs = idKrs;
    }

    public Long getIdSkemaKrs() {
        return idSkemaKrs;
    }

    public void setIdSkemaKrs(Long idSkemaKrs) {
        this.idSkemaKrs = idSkemaKrs;
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

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

}

@Path("/krs")
@Produces("application/json")
public class KrsMahasiswaService {

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    @EJB
    UserManager userManager;

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Response getKrs(@PathParam("username") String username){
        if(username.equals("2244068")) {
            User user = userManager.findUser(username);
            List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(user.getId());
            List<KrsMahasiswaDTO> krsMahasiswaDTOs = new ArrayList<>();
            krsMahasiswas.forEach(krs->{
                KrsMahasiswaDTO krsMahasiswaDTO = new KrsMahasiswaDTO();
                krsMahasiswaDTO.setIdKrs(krs.getId());
                krsMahasiswaDTO.setIdSkemaKrs(krs.getSkedul().getIdSkemakrs().getId());
                krsMahasiswaDTO.setNamaDosen(krs.getSkedul().getIdDosen().getNamaDosen());
                krsMahasiswaDTO.setKodeMatakuliah(krs.getSkedul().getIdMatakuliah().getKodeMatakuliah());
                krsMahasiswaDTO.setNamaMatakuliah(krs.getSkedul().getIdMatakuliah().getNamaMatakuliah());
                krsMahasiswaDTOs.add(krsMahasiswaDTO);
            });
            return Response.ok(krsMahasiswaDTOs,MediaType.APPLICATION_JSON).build();
        }
        return Response.noContent().build();
    }
}
