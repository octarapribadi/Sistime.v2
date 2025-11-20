package entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_registrasimahasiswa")
public class RegistrasiMahasiswa implements java.io.Serializable {

    private Long idPendaftaran;
    private Agama agama;
    private Kampus kampus;
    private ProgramStudi programStudi;
    private Sekolah sekolah;
    private Status status;
    private WaktuKuliah waktuKuliah;
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
    private String kodeVerifikasi;
    private String pendidikanOrangtua;
    private String fileFoto;

    public RegistrasiMahasiswa() {
    }

    public RegistrasiMahasiswa(Agama agama, Kampus kampus, ProgramStudi programStudi, Sekolah sekolah, Status status, WaktuKuliah waktuKuliah, String email, Date tanggalPendaftaran, String namaMahasiswa, String tempatLahir, Date tanggalLahir, String alamatMahasiswa, String jenisKelamin, String alamatOrangtua, Integer anakKe, String golonganDarah, String hobi, Integer jumlahSaudara, String jurusan, String keterangan, String kewarganegaraan, String namaAyah, String namaIbu, String noIjazah, String noTeleponMahasiswa, String noTeleponOrangtua, String pekerjaanOrangtua, Integer tahunAngkatan, Integer tahunLulus, Date tanggalIjazah, String kodeVerifikasi, String pendidikanOrangtua, String fileFoto) {
        this.agama = agama;
        this.kampus = kampus;
        this.programStudi = programStudi;
        this.sekolah = sekolah;
        this.status = status;
        this.waktuKuliah = waktuKuliah;
        this.email = email;
        this.tanggalPendaftaran = tanggalPendaftaran;
        this.namaMahasiswa = namaMahasiswa;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamatMahasiswa = alamatMahasiswa;
        this.jenisKelamin = jenisKelamin;
        this.alamatOrangtua = alamatOrangtua;
        this.anakKe = anakKe;
        this.golonganDarah = golonganDarah;
        this.hobi = hobi;
        this.jumlahSaudara = jumlahSaudara;
        this.jurusan = jurusan;
        this.keterangan = keterangan;
        this.kewarganegaraan = kewarganegaraan;
        this.namaAyah = namaAyah;
        this.namaIbu = namaIbu;
        this.noIjazah = noIjazah;
        this.noTeleponMahasiswa = noTeleponMahasiswa;
        this.noTeleponOrangtua = noTeleponOrangtua;
        this.pekerjaanOrangtua = pekerjaanOrangtua;
        this.tahunAngkatan = tahunAngkatan;
        this.tahunLulus = tahunLulus;
        this.tanggalIjazah = tanggalIjazah;
        this.kodeVerifikasi = kodeVerifikasi;
        this.pendidikanOrangtua = pendidikanOrangtua;
        this.fileFoto = fileFoto;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)


    @Column(name = "id_pendaftaran", unique = true, nullable = false)
    public Long getIdPendaftaran() {
        return this.idPendaftaran;
    }

    public void setIdPendaftaran(Long idPendaftaran) {
        this.idPendaftaran = idPendaftaran;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agama")
    public Agama getAgama() {
        return this.agama;
    }

    public void setAgama(Agama agama) {
        this.agama = agama;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_kampus")
    public Kampus getKampus() {
        return this.kampus;
    }

    public void setKampus(Kampus kampus) {
        this.kampus = kampus;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_programstudi")
    @JsonManagedReference
    public ProgramStudi getProgramStudi() {
        return this.programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sekolah")
    @JsonManagedReference
    public Sekolah getSekolah() {
        return this.sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    @JsonManagedReference
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_waktukuliah")
    @JsonManagedReference
    public WaktuKuliah getWaktuKuliah() {
        return this.waktuKuliah;
    }

    public void setWaktuKuliah(WaktuKuliah waktuKuliah) {
        this.waktuKuliah = waktuKuliah;
    }


    @Column(name = "email", length = 45)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_pendaftaran", length = 10)
    public Date getTanggalPendaftaran() {
        return this.tanggalPendaftaran;
    }

    public void setTanggalPendaftaran(Date tanggalPendaftaran) {
        this.tanggalPendaftaran = tanggalPendaftaran;
    }


    @Column(name = "nama_mahasiswa", length = 45)
    public String getNamaMahasiswa() {
        return this.namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }


    @Column(name = "tempat_lahir", length = 45)
    public String getTempatLahir() {
        return this.tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_lahir", length = 10)
    public Date getTanggalLahir() {
        return this.tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }


    @Column(name = "alamat_mahasiswa")
    public String getAlamatMahasiswa() {
        return this.alamatMahasiswa;
    }

    public void setAlamatMahasiswa(String alamatMahasiswa) {
        this.alamatMahasiswa = alamatMahasiswa;
    }


    @Column(name = "jenis_kelamin", length = 45)
    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }


    @Column(name = "alamat_orangtua")
    public String getAlamatOrangtua() {
        return this.alamatOrangtua;
    }

    public void setAlamatOrangtua(String alamatOrangtua) {
        this.alamatOrangtua = alamatOrangtua;
    }


    @Column(name = "anak_ke")
    public Integer getAnakKe() {
        return this.anakKe;
    }

    public void setAnakKe(Integer anakKe) {
        this.anakKe = anakKe;
    }


    @Column(name = "golongan_darah", length = 45)
    public String getGolonganDarah() {
        return this.golonganDarah;
    }

    public void setGolonganDarah(String golonganDarah) {
        this.golonganDarah = golonganDarah;
    }


    @Column(name = "hobi", length = 45)
    public String getHobi() {
        return this.hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }


    @Column(name = "jumlah_saudara")
    public Integer getJumlahSaudara() {
        return this.jumlahSaudara;
    }

    public void setJumlahSaudara(Integer jumlahSaudara) {
        this.jumlahSaudara = jumlahSaudara;
    }


    @Column(name = "jurusan", length = 45)
    public String getJurusan() {
        return this.jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }


    @Column(name = "keterangan")
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    @Column(name = "kewarganegaraan", length = 45)
    public String getKewarganegaraan() {
        return this.kewarganegaraan;
    }

    public void setKewarganegaraan(String kewarganegaraan) {
        this.kewarganegaraan = kewarganegaraan;
    }


    @Column(name = "nama_ayah", length = 45)
    public String getNamaAyah() {
        return this.namaAyah;
    }

    public void setNamaAyah(String namaAyah) {
        this.namaAyah = namaAyah;
    }


    @Column(name = "nama_ibu", length = 45)
    public String getNamaIbu() {
        return this.namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }


    @Column(name = "no_ijazah", length = 45)
    public String getNoIjazah() {
        return this.noIjazah;
    }

    public void setNoIjazah(String noIjazah) {
        this.noIjazah = noIjazah;
    }


    @Column(name = "no_telepon_mahasiswa", length = 45)
    public String getNoTeleponMahasiswa() {
        return this.noTeleponMahasiswa;
    }

    public void setNoTeleponMahasiswa(String noTeleponMahasiswa) {
        this.noTeleponMahasiswa = noTeleponMahasiswa;
    }


    @Column(name = "no_telepon_orangtua", length = 45)
    public String getNoTeleponOrangtua() {
        return this.noTeleponOrangtua;
    }

    public void setNoTeleponOrangtua(String noTeleponOrangtua) {
        this.noTeleponOrangtua = noTeleponOrangtua;
    }


    @Column(name = "pekerjaan_orangtua", length = 45)
    public String getPekerjaanOrangtua() {
        return this.pekerjaanOrangtua;
    }

    public void setPekerjaanOrangtua(String pekerjaanOrangtua) {
        this.pekerjaanOrangtua = pekerjaanOrangtua;
    }


    @Column(name = "tahun_angkatan")
    public Integer getTahunAngkatan() {
        return this.tahunAngkatan;
    }

    public void setTahunAngkatan(Integer tahunAngkatan) {
        this.tahunAngkatan = tahunAngkatan;
    }


    @Column(name = "tahun_lulus")
    public Integer getTahunLulus() {
        return this.tahunLulus;
    }

    public void setTahunLulus(Integer tahunLulus) {
        this.tahunLulus = tahunLulus;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_ijazah", length = 10)
    public Date getTanggalIjazah() {
        return this.tanggalIjazah;
    }

    public void setTanggalIjazah(Date tanggalIjazah) {
        this.tanggalIjazah = tanggalIjazah;
    }


    @Column(name = "kode_verifikasi")
    public String getKodeVerifikasi() {
        return this.kodeVerifikasi;
    }

    public void setKodeVerifikasi(String kodeVerifikasi) {
        this.kodeVerifikasi = kodeVerifikasi;
    }


    @Column(name = "pendidikan_orangtua", length = 45)
    public String getPendidikanOrangtua() {
        return this.pendidikanOrangtua;
    }

    public void setPendidikanOrangtua(String pendidikanOrangtua) {
        this.pendidikanOrangtua = pendidikanOrangtua;
    }


    @Column(name = "file_foto")
    public String getFileFoto() {
        return this.fileFoto;
    }

    public void setFileFoto(String fileFoto) {
        this.fileFoto = fileFoto;
    }


}


