package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_waktu_kuliah")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idWaktukuliah")
public class WaktuKuliah implements java.io.Serializable {


    private int idWaktukuliah;
    private Kampus kampus;
    private String waktuKuliah;
    private String keterangan;
    private Set<RegistrasiMahasiswa> registrasiMahasiswas = new HashSet<RegistrasiMahasiswa>(0);
    private Set<Mahasiswa> mahasiswas = new HashSet<Mahasiswa>(0);

    public WaktuKuliah() {
    }


    public WaktuKuliah(int idWaktukuliah) {
        this.idWaktukuliah = idWaktukuliah;
    }

    public WaktuKuliah(int idWaktukuliah, Kampus kampus, String waktuKuliah, String keterangan, Set<RegistrasiMahasiswa> registrasiMahasiswas, Set<Mahasiswa> mahasiswas) {
        this.idWaktukuliah = idWaktukuliah;
        this.kampus = kampus;
        this.waktuKuliah = waktuKuliah;
        this.keterangan = keterangan;
        this.registrasiMahasiswas = registrasiMahasiswas;
        this.mahasiswas = mahasiswas;
    }

    @Id
    @Column(name = "id_waktukuliah", unique = true, nullable = false)
    public int getIdWaktukuliah() {
        return this.idWaktukuliah;
    }

    public void setIdWaktukuliah(int idWaktukuliah) {
        this.idWaktukuliah = idWaktukuliah;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_kampus")
    @JsonBackReference
    public Kampus getKampus() {
        return this.kampus;
    }

    public void setKampus(Kampus kampus) {
        this.kampus = kampus;
    }


    @Column(name = "waktu_kuliah", length = 45)
    public String getWaktuKuliah() {
        return this.waktuKuliah;
    }

    public void setWaktuKuliah(String waktuKuliah) {
        this.waktuKuliah = waktuKuliah;
    }


    @Column(name = "keterangan")
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "waktuKuliah")
    @JsonBackReference
    public Set<RegistrasiMahasiswa> getRegistrasiMahasiswas() {
        return this.registrasiMahasiswas;
    }

    public void setRegistrasiMahasiswas(Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.registrasiMahasiswas = registrasiMahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "waktuKuliah")
    @JsonBackReference
    public Set<Mahasiswa> getMahasiswas() {
        return this.mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }


}


