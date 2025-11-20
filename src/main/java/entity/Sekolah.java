package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "tbl_sekolah")
public class Sekolah implements java.io.Serializable {


    private Integer idSekolah;
    private String alamatSekolah;
    private String keterangan;
    private String namaSekolah;
    private Set<Mahasiswa> mahasiswas = new HashSet<Mahasiswa>(0);
    private Set<RegistrasiMahasiswa> registrasiMahasiswas = new HashSet<RegistrasiMahasiswa>(0);

    public Sekolah() {
    }

    public Sekolah(String alamatSekolah, String keterangan, String namaSekolah, Set<Mahasiswa> mahasiswas, Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.alamatSekolah = alamatSekolah;
        this.keterangan = keterangan;
        this.namaSekolah = namaSekolah;
        this.mahasiswas = mahasiswas;
        this.registrasiMahasiswas = registrasiMahasiswas;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)


    @Column(name = "id_sekolah", unique = true, nullable = false)
    public Integer getIdSekolah() {
        return this.idSekolah;
    }

    public void setIdSekolah(Integer idSekolah) {
        this.idSekolah = idSekolah;
    }


    @Column(name = "alamat_sekolah")
    public String getAlamatSekolah() {
        return this.alamatSekolah;
    }

    public void setAlamatSekolah(String alamatSekolah) {
        this.alamatSekolah = alamatSekolah;
    }


    @Column(name = "keterangan")
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    @Column(name = "nama_sekolah")
    public String getNamaSekolah() {
        return this.namaSekolah;
    }

    public void setNamaSekolah(String namaSekolah) {
        this.namaSekolah = namaSekolah;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sekolah")
    @JsonBackReference
    public Set<Mahasiswa> getMahasiswas() {
        return this.mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sekolah")
    @JsonBackReference
    public Set<RegistrasiMahasiswa> getRegistrasiMahasiswas() {
        return this.registrasiMahasiswas;
    }

    public void setRegistrasiMahasiswas(Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.registrasiMahasiswas = registrasiMahasiswas;
    }


}


