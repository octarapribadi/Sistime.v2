package rest.bean;

import ejb.KrsMahasiswaManager;
import ejb.UserManager;
import model.KrsMahasiswa;
import model.User;
import rest.model.KrsMahasiswaDTO;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class KrsMahasiswaCDI implements Serializable {
    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    @EJB
    UserManager userManager;

    public List<KrsMahasiswaDTO> getKrs(long idUser){
        User user = userManager.findUserByUserId(idUser);
        if (user != null) {
            List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(user.getId());
            List<KrsMahasiswaDTO> krsMahasiswaDTOs = new ArrayList<>();
            krsMahasiswas.forEach(krs -> {
                KrsMahasiswaDTO krsMahasiswaDTO = new KrsMahasiswaDTO();
                krsMahasiswaDTO.setIdKrs(krs.getId());
                krsMahasiswaDTO.setIdSkemaKrs(krs.getSkedul().getIdSkemakrs().getId());
                krsMahasiswaDTO.setNamaDosen(krs.getSkedul().getIdDosen().getNamaDosen());
                //cek lagi
                krsMahasiswaDTO.setSemester(krs.getSkedul().getIdMatakuliah().getSemester());
                krsMahasiswaDTO.setKodeMatakuliah(krs.getSkedul().getIdMatakuliah().getKodeMatakuliah());
                krsMahasiswaDTO.setNamaMatakuliah(krs.getSkedul().getIdMatakuliah().getNamaMatakuliah());
                krsMahasiswaDTOs.add(krsMahasiswaDTO);
            });
            return krsMahasiswaDTOs;
        }
        else
            return Collections.emptyList();
    }
}
