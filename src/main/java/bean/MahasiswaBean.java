package bean;

import dto.MahasiswaDto;
import entity.Mahasiswa;
import org.eclipse.microprofile.jwt.JsonWebToken;
import repo.MahasiswaManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import java.io.Serializable;

@RequestScoped
public class MahasiswaBean implements Serializable {
    @Inject
    MahasiswaManager mahasiswaManager;

    @Inject
    JsonWebToken token;

    public MahasiswaDto getMahasiswaByNim(String nim) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByNim(nim);
        if (token.getClaim("sub").equals(mhs.getUser().getId().toString())
                || token.getClaim("roles").equals("administrator")) {
            MahasiswaDto dto = MahasiswaDto.fromEntity(mhs);
            return dto;
        } else
            return null;
    }

    public Mahasiswa getMahasiswaByIdUser(long idUser) {
        return mahasiswaManager.findMahasiswaByIdUser(idUser);
    }
}
