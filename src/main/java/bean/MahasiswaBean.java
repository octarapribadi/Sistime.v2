package bean;

import entity.Mahasiswa;
import repo.MahasiswaManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@RequestScoped
public class MahasiswaBean implements Serializable {
    @Inject
    MahasiswaManager mahasiswaManager;

    public Mahasiswa getMahasiswaByNim(String nim){
        return mahasiswaManager.findMahasiswaByNim(nim);
    }
}
