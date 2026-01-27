package bean;

import dto.MahasiswaDto;
import entity.Mahasiswa;
import org.eclipse.microprofile.jwt.JsonWebToken;
import repo.MahasiswaManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Set;

@RequestScoped
public class MahasiswaBean implements Serializable {
    @Inject
    MahasiswaManager mahasiswaManager;

    @Inject
    JsonWebToken token;

    public MahasiswaDto getMahasiswaByNim(String nim) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByNim(nim);
        if (mhs != null)
            return MahasiswaDto.fromEntity(mhs);
        else return null;
    }

    public MahasiswaDto getMahasiswaByIdUser(long idUser) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByIdUser(idUser);
        if (mhs != null)
            return MahasiswaDto.fromEntity(mhs);
        else return null;
    }
}
