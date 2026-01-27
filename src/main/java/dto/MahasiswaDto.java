package dto;

import entity.Agama;
import entity.Kampus;
import entity.Mahasiswa;

import java.util.Date;

public class MahasiswaDto {
    private long idPendaftaran;
    private long idUser;
    private String idAgama;
    private String kodeKampus;
    private String kodeProgramStudi;
    private Integer idSekolah;
    private Integer idStatus;
    private Integer idWaktuKuliah;
    private String email;
    private Date tanggalPendaftaran;
    private String namaMahasiswa;
    private String tempatLahir;
    private Date tanggalLahir;
    private String alamatMahasiswa;
    private String jenisKelamin;
    private String alamatOrangtua;
    private Integer anakKe;
    private String golonganDarah;
    private String hobi;
    private Integer jumlahSaudara;
    private String jurusan;
    private String keterangan;
    private String kewarganegaraan;
    private String namaAyah;
    private String namaIbu;
    private String noIjazah;
    private String noTeleponMahasiswa;
    private String noTeleponOrangtua;
    private String pekerjaanOrangtua;

    private Integer tahunAngkatan;
    private Integer tahunLulus;
    private Date tanggalIjazah;
    private String pendidikanOrangtua;

    public static Mahasiswa toEntity(Mahasiswa mhs, MahasiswaDto dto) {
        if (dto.idAgama != null) {
            mhs.setAgama(new Agama());
            mhs.getAgama().setIdAgama(dto.getIdAgama());
        }
        if(dto.kodeKampus!=null){
            mhs.setKampus(new Kampus());
            mhs.getKampus().setKodeKampus(dto.getKodeKampus());
        }
        mhs.setAlamatMahasiswa(dto.getAlamatMahasiswa());
        return mhs;
    }

    public static MahasiswaDto fromEntity(Mahasiswa mahasiswa) {
        MahasiswaDto mhs = new MahasiswaDto();
        mhs.idPendaftaran = mahasiswa.getIdPendaftaran();
        mhs.idUser = mahasiswa.getUser().getId();
        mhs.idAgama = mahasiswa.getAgama() == null ? null : mahasiswa.getAgama().getIdAgama();
        mhs.kodeKampus = mahasiswa.getKampus() == null ? null : mahasiswa.getKampus().getKodeKampus();
        mhs.kodeProgramStudi = mahasiswa.getProgramStudi() == null ? null : mahasiswa.getProgramStudi().getKodeProgramstudi();
        mhs.idSekolah = mahasiswa.getSekolah() == null ? null : mahasiswa.getSekolah().getIdSekolah();
        mhs.idStatus = mahasiswa.getStatus() == null ? null : mahasiswa.getStatus().getIdStatus();
        mhs.idWaktuKuliah = mahasiswa.getWaktuKuliah() == null ? null : mahasiswa.getWaktuKuliah().getIdWaktukuliah();
        mhs.email = mahasiswa.getEmail();
        mhs.tanggalPendaftaran = mahasiswa.getTanggalPendaftaran();
        mhs.namaMahasiswa = mahasiswa.getNamaMahasiswa();
        mhs.tempatLahir = mahasiswa.getTempatLahir();
        mhs.tanggalLahir = mahasiswa.getTanggalLahir();
        mhs.alamatMahasiswa = mahasiswa.getAlamatMahasiswa();
        mhs.jenisKelamin = mahasiswa.getJenisKelamin();
        mhs.alamatOrangtua = mahasiswa.getAlamatOrangtua();
        mhs.anakKe = mahasiswa.getAnakKe();
        mhs.golonganDarah = mahasiswa.getGolonganDarah();
        mhs.hobi = mahasiswa.getHobi();
        mhs.jumlahSaudara = mahasiswa.getJumlahSaudara();
        mhs.jurusan = mahasiswa.getJurusan();
        mhs.keterangan = mahasiswa.getKeterangan();
        mhs.kewarganegaraan = mahasiswa.getKewarganegaraan();
        mhs.namaAyah = mahasiswa.getNamaAyah();
        mhs.namaIbu = mahasiswa.getNamaIbu();
        mhs.noIjazah = mahasiswa.getNoIjazah();
        mhs.noTeleponMahasiswa = mahasiswa.getNoTeleponMahasiswa();
        mhs.noTeleponOrangtua = mahasiswa.getNoTeleponOrangtua();
        mhs.pekerjaanOrangtua = mahasiswa.getPekerjaanOrangtua();
        mhs.tahunAngkatan = mahasiswa.getTahunAngkatan();
        mhs.tahunLulus = mahasiswa.getTahunLulus();
        mhs.tanggalIjazah = mahasiswa.getTanggalIjazah();
        mhs.pendidikanOrangtua = mahasiswa.getPendidikanOrangtua();
        return mhs;
    }

    public Integer getIdSekolah() {
        return idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }

    public long getIdPendaftaran() {
        return idPendaftaran;
    }

    public void setIdPendaftaran(long idPendaftaran) {
        this.idPendaftaran = idPendaftaran;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getIdAgama() {
        return idAgama;
    }

    public void setIdAgama(String idAgama) {
        this.idAgama = idAgama;
    }

    public String getKodeKampus() {
        return kodeKampus;
    }

    public void setKodeKampus(String kodeKampus) {
        this.kodeKampus = kodeKampus;
    }

    public String getKodeProgramStudi() {
        return kodeProgramStudi;
    }

    public void setKodeProgramStudi(String kodeProgramStudi) {
        this.kodeProgramStudi = kodeProgramStudi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTanggalPendaftaran() {
        return tanggalPendaftaran;
    }

    public void setTanggalPendaftaran(Date tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamatMahasiswa() {
        return alamatMahasiswa;
    }

    public void setAlamatMahasiswa(String alamatMahasiswa) {
        this.alamatMahasiswa = alamatMahasiswa;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamatOrangtua() {
        return alamatOrangtua;
    }

    public void setAlamatOrangtua(String alamatOrangtua) {
        this.alamatOrangtua = alamatOrangtua;
    }

    public String getGolonganDarah() {
        return golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKewarganegaraan() {
        return kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }

    public String getNamaAyah() {
        return namaAyah;
    }

    public void setNamaAyah(String namaAyah) {
        this.namaAyah = namaAyah;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public String getNoIjazah() {
        return noIjazah;
    }

    public void setNoIjazah(String noIjazah) {
        this.noIjazah = noIjazah;
    }

    public String getNoTeleponMahasiswa() {
        return noTeleponMahasiswa;
    }

    public void setNoTeleponMahasiswa(String noTeleponMahasiswa) {
        this.noTeleponMahasiswa = noTeleponMahasiswa;
    }

    public String getNoTeleponOrangtua() {
        return noTeleponOrangtua;
    }

    public void setNoTeleponOrangtua(String noTeleponOrangtua) {
        this.noTeleponOrangtua = noTeleponOrangtua;
    }

    public String getPekerjaanOrangtua() {
        return pekerjaanOrangtua;
    }

    public void setPekerjaanOrangtua(String pekerjaanOrangtua) {
        this.pekerjaanOrangtua = pekerjaanOrangtua;
    }


    public Date getTanggalIjazah() {
        return tanggalIjazah;
    }

    public void setTanggalIjazah(Date tanggalIjazah) {
        this.tanggalIjazah = tanggalIjazah;
    }

    public String getPendidikanOrangtua() {
        return pendidikanOrangtua;
    }

    public void setPendidikanOrangtua(String pendidikanOrangtua) {
        this.pendidikanOrangtua = pendidikanOrangtua;
    }


    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public Integer getIdWaktuKuliah() {
        return idWaktuKuliah;
    }

    public void setIdWaktuKuliah(Integer idWaktuKuliah) {
        this.idWaktuKuliah = idWaktuKuliah;
    }

    public Integer getAnakKe() {
        return anakKe;
    }

    public void setAnakKe(Integer anakKe) {
        this.anakKe = anakKe;
    }

    public Integer getJumlahSaudara() {
        return jumlahSaudara;
    }

    public void setJumlahSaudara(Integer jumlahSaudara) {
        this.jumlahSaudara = jumlahSaudara;
    }

    public Integer getTahunAngkatan() {
        return tahunAngkatan;
    }

    public void setTahunAngkatan(Integer tahunAngkatan) {
        this.tahunAngkatan = tahunAngkatan;
    }

    public Integer getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(Integer tahunLulus) {
        this.tahunLulus = tahunLulus;
    }
}
