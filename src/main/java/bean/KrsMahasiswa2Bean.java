package bean;

import repo.KrsMahasiswaManager;
import model.KrsMahasiswa;
import model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class KrsMahasiswa2Bean implements Serializable {
    List<KrsMahasiswa>krsMahasiswas;

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    @PostConstruct
    private void init(){
        krsMahasiswas = new ArrayList<>();
    }

    public List<KrsMahasiswa> findAllKrsMahasiswaByUsersAndIdMataKuliah(List<User> user, Long idMatkul) {
        krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUsersAndIdMatakuliah(user,idMatkul);
        return krsMahasiswas;
    }

}
