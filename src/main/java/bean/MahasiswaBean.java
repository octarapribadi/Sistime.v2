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
            return entityToDto(mhs);
        else
            return null;
    }

    public MahasiswaDto getMahasiswaByIdUser(long idUser) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByIdUser(idUser);
        if (mhs != null) {
            return entityToDto(mhs);
        } else return null;
    }

    @Transactional
    public void patchMahasiswaByIdUser(long idUser, MahasiswaDto dto) {
        Mahasiswa mhs = mahasiswaManager.findMahasiswaByIdUser(idUser);
        dtoToEntity(mhs, dto);
        mahasiswaManager.merge(mhs);
    }

    private MahasiswaDto entityToDto(Mahasiswa mhs) {
        MahasiswaDto dto = new MahasiswaDto();
        if (mhs.getAgama() != null)
            dto.setIdAgama(mhs.getAgama().getIdAgama());
        if (mhs.getKampus() != null)
            dto.setKodeKampus(mhs.getKampus().getKodeKampus());
        if (mhs.getProgramStudi() != null)
            dto.setKodeProgramStudi(mhs.getProgramStudi().getKodeProgramstudi());
        if (mhs.getSekolah() != null)
            dto.setIdSekolah(mhs.getSekolah().getIdSekolah());
        if (mhs.getStatus() != null)
            dto.setIdStatus(mhs.getStatus().getIdStatus());
        if (mhs.getWaktuKuliah() != null)
            dto.setIdWaktuKuliah(mhs.getWaktuKuliah().getIdWaktukuliah());
        dto.setIdPendaftaran(mhs.getIdPendaftaran());
        dto.setIdUser(mhs.getUser().getId());
        dto.setEmail(mhs.getEmail());
        dto.setTanggalPendaftaran(mhs.getTanggalPendaftaran());
        dto.setNamaMahasiswa(mhs.getNamaMahasiswa());
        dto.setTempatLahir(mhs.getTempatLahir());
        dto.setTanggalLahir(mhs.getTanggalLahir());
        dto.setAlamatMahasiswa(mhs.getAlamatMahasiswa());
        dto.setJenisKelamin(mhs.getJenisKelamin());
        dto.setAlamatOrangtua(mhs.getAlamatOrangtua());
        dto.setAnakKe(mhs.getAnakKe());
        dto.setGolonganDarah(mhs.getGolonganDarah());
        dto.setHobi(mhs.getHobi());
        dto.setJumlahSaudara(mhs.getJumlahSaudara());
        dto.setJurusan(mhs.getJurusan());
        dto.setKeterangan(mhs.getKeterangan());
        dto.setKewarganegaraan(mhs.getKewarganegaraan());
        dto.setNamaAyah(mhs.getNamaAyah());
        dto.setNamaIbu(mhs.getNamaIbu());
        dto.setNoIjazah(mhs.getNoIjazah());
        dto.setNoTeleponMahasiswa(mhs.getNoTeleponMahasiswa());
        dto.setNoTeleponOrangtua(mhs.getNoTeleponOrangtua());
        dto.setPekerjaanOrangtua(mhs.getPekerjaanOrangtua());
        dto.setTahunAngkatan(mhs.getTahunAngkatan());
        dto.setTahunLulus(mhs.getTahunLulus());
        dto.setTanggalIjazah(mhs.getTanggalIjazah());
        dto.setPendidikanOrangtua(mhs.getPendidikanOrangtua());
        return dto;
    }

    private Mahasiswa dtoToEntity(Mahasiswa mhs, MahasiswaDto dto) {

        if (dto.getIdAgama() != null) {
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

        if (dto.getTanggalPendaftaran() != null)
            mhs.setTanggalPendaftaran(dto.getTanggalPendaftaran());

        if (dto.getNamaMahasiswa() != null)
            mhs.setNamaMahasiswa(dto.getNamaMahasiswa());

        if (dto.getTempatLahir() != null)
            mhs.setTempatLahir(dto.getTempatLahir());

        if (dto.getTanggalLahir() != null)
            mhs.setTanggalLahir(dto.getTanggalLahir());

        if (dto.getAlamatMahasiswa() != null)
            mhs.setAlamatMahasiswa(dto.getAlamatMahasiswa());

        if (dto.getJenisKelamin() != null)
            mhs.setJenisKelamin(dto.getJenisKelamin());

        if (dto.getAlamatOrangtua() != null)
            mhs.setAlamatOrangtua(dto.getAlamatOrangtua());

        if (dto.getAnakKe() != null)
            mhs.setAnakKe(dto.getAnakKe());

        if (dto.getGolonganDarah() != null)
            mhs.setGolonganDarah(dto.getGolonganDarah());

        if (dto.getHobi() != null)
            mhs.setHobi(dto.getHobi());

        if (dto.getJumlahSaudara() != null)
            mhs.setJumlahSaudara(dto.getJumlahSaudara());

        if (dto.getJurusan() != null)
            mhs.setJurusan(dto.getJurusan());

        if (dto.getKeterangan() != null)
            mhs.setKeterangan(dto.getKeterangan());

        if (dto.getKeterangan() != null)
            mhs.setKewarganegaraan(dto.getKewarganegaraan());

        if (dto.getNamaAyah() != null)
            mhs.setNamaAyah(dto.getNamaAyah());

        if (dto.getNamaIbu() != null)
            mhs.setNamaIbu(dto.getNamaIbu());

        if (dto.getNoIjazah() != null)
            mhs.setNoIjazah(dto.getNoIjazah());

        if (dto.getNoTeleponMahasiswa() != null)
            mhs.setNoTeleponMahasiswa(dto.getNoTeleponMahasiswa());

        if (dto.getNoTeleponOrangtua() != null)
            mhs.setNoTeleponOrangtua(dto.getNoTeleponOrangtua());

        if (dto.getPekerjaanOrangtua() != null)
            mhs.setPekerjaanOrangtua(dto.getPekerjaanOrangtua());

        if (dto.getTahunAngkatan() != null)
            mhs.setTahunAngkatan(dto.getTahunAngkatan());

        if (dto.getTanggalIjazah() != null)
            mhs.setTanggalIjazah(dto.getTanggalIjazah());

        if (dto.getPendidikanOrangtua() != null)
            mhs.setPendidikanOrangtua(dto.getPendidikanOrangtua());

        return mhs;
    }

}
