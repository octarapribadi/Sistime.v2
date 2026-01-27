package bean;

import dto.MahasiswaDto;
import entity.Mahasiswa;
import repo.MahasiswaManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@RequestScoped
public class MahasiswaBean implements Serializable {
    @Inject
    MahasiswaManager mahasiswaManager;

    public MahasiswaDto getMahasiswaByNim(String nim) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByNim(nim);
        if (mhs != null)
            return MahasiswaDto.fromEntity(mhs);
        else
            return null;
    }

    public MahasiswaDto getMahasiswaByIdUser(long idUser) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByIdUser(idUser);
        if (mhs != null) {
            return MahasiswaDto.fromEntity(mhs);
        } else return null;
    }

    @Transactional
    public void patchMahasiswaByIdUser(long idUser, MahasiswaDto dto) {
//        Long idPendaftaran = mahasiswaManager.findIdPendaftaranByIdUser(idUser);
        Mahasiswa m = mahasiswaManager.findMahasiswaByIdUser(idUser);
        Mahasiswa mhs = MahasiswaDto.toEntity(m, dto);
        mahasiswaManager.merge(mhs);
    }
}
