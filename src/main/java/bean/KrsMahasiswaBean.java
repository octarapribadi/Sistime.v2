package bean;

import repo.KrsMahasiswaManager;
import repo.UserManager;
import entity.KrsMahasiswa;
import entity.User;
import dto.KrsMahasiswaDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class KrsMahasiswaBean implements Serializable {
    @Inject
    KrsMahasiswaManager krsMahasiswaManager;

    @Inject
    UserManager userManager;

    public List<KrsMahasiswaDto> getKrs(long idUser){
        User user = userManager.findUserByUserId(idUser);
        if (user != null) {
            List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(user.getId());
            List<KrsMahasiswaDto> krsMahasiswaDtos = new ArrayList<>();
            krsMahasiswas.forEach(krs -> {
                KrsMahasiswaDto krsMahasiswaDTO = new KrsMahasiswaDto();
                krsMahasiswaDTO.setIdKrs(krs.getId());
                krsMahasiswaDTO.setIdSkemaKrs(krs.getSkedul().getIdSkemakrs().getId());
                krsMahasiswaDTO.setNamaDosen(krs.getSkedul().getIdDosen().getNamaDosen());
                //cek lagi
                krsMahasiswaDTO.setSemester(krs.getSkedul().getIdMatakuliah().getSemester());
                krsMahasiswaDTO.setKodeMatakuliah(krs.getSkedul().getIdMatakuliah().getKodeMatakuliah());
                krsMahasiswaDTO.setNamaMatakuliah(krs.getSkedul().getIdMatakuliah().getNamaMatakuliah());
                krsMahasiswaDtos.add(krsMahasiswaDTO);
            });
            return krsMahasiswaDtos;
        }
        else
            return Collections.emptyList();
    }
}
