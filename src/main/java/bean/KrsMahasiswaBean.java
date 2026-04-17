package bean;

import dto.ErrorResponse;
import entity.Skedul;
import repo.KrsMahasiswaManager;
import repo.SkedulManager;
import repo.UserManager;
import entity.KrsMahasiswa;
import entity.User;
import dto.KrsMahasiswaDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
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

    @Inject
    SkedulManager skedulManager;

    public List<KrsMahasiswaDto> getKrs(long idUser){
        User user = userManager.findUserByUserId(idUser);
        if (user != null) {
            List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(user.getId());
            List<KrsMahasiswaDto> krsMahasiswaDtos = new ArrayList<>();
            krsMahasiswas.forEach(krs -> {
                KrsMahasiswaDto krsMahasiswaDTO = new KrsMahasiswaDto();
                krsMahasiswaDTO.setId(krs.getId());
                krsMahasiswaDTO.setIdUser(krs.getUser().getId());
                krsMahasiswaDTO.setIdSkedul(krs.getSkedul().getId());
                krsMahasiswaDTO.setKeterangan(krs.getKeterangan());
                krsMahasiswaDtos.add(krsMahasiswaDTO);
            });
            return krsMahasiswaDtos;
        }
        else
            return Collections.emptyList();
    }

    @Transactional
    public void persist(List<KrsMahasiswa> krsMahasiswas){
        List<Long>skedulIds = new ArrayList<>();
        krsMahasiswas.forEach(krsMahasiswa -> {
            skedulIds.add(krsMahasiswa.getSkedul().getId());
        });

        for(KrsMahasiswa krsMahasiswa : krsMahasiswas){
            boolean ketemu = false;
            for(Long skedulId : skedulIds){
                if(krsMahasiswa.getSkedul().getId().equals(skedulId)){
                    ketemu = true;
                    break;
                }
            }
            if(!ketemu)
                throw new NotFoundException("idSkedul tidak ketemu");
        }
        krsMahasiswaManager.persist(krsMahasiswas);
    }

}
