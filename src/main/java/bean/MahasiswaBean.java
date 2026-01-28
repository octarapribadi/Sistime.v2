package bean;

import dto.MahasiswaDto;
import entity.*;
import repo.MahasiswaManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;

@RequestScoped
public class MahasiswaBean implements Serializable {
    @Inject
    MahasiswaManager mahasiswaManager;

    @Inject
    AgamaBean agamaBean;

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
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByIdUser(idUser);
        if (dto.getIdAgama() != null && agamaBean.isAgamaExist(dto.getIdAgama())) {
            mhs.setAgama(new Agama());
            mhs.getAgama().setIdAgama(dto.getIdAgama());
        }
        if (dto.getKodeKampus() != null) {
            mhs.setKampus(new Kampus());
            mhs.getKampus().setKodeKampus(dto.getKodeKampus());
        }
        if (dto.getKodeProgramStudi() != null) {
            mhs.setProgramStudi(new ProgramStudi());
            mhs.getProgramStudi().setKodeProgramstudi(dto.getKodeProgramStudi());
        }
        if (dto.getIdSekolah() != null) {
            mhs.setSekolah(new Sekolah());
            mhs.getSekolah().setIdSekolah(dto.getIdSekolah());
        }
        if (dto.getIdStatus() != null) {
            mhs.setStatus(new Status());
            mhs.getStatus().setIdStatus(dto.getIdStatus());
        }
        if (dto.getIdWaktuKuliah() != null) {
            mhs.setWaktuKuliah(new WaktuKuliah());
            mhs.getWaktuKuliah().setIdWaktukuliah(dto.getIdWaktuKuliah());
        }
        if (dto.getEmail() != null)
            mhs.setEmail(dto.getEmail());
        if(dto.getTanggalPendaftaran()!=null)
            mhs.setTanggalPendaftaran(dto.getTanggalPendaftaran());


        if (dto.getAlamatMahasiswa() != null)
            mhs.setAlamatMahasiswa(dto.getAlamatMahasiswa());

        mahasiswaManager.merge(mhs);
    }
}
