package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_kampus")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "kodeKampus")
public class Kampus implements java.io.Serializable {


    private String kodeKampus;
    private String keterangan;
    private Set<RegistrasiMahasiswa> registrasiMahasiswas = new HashSet<RegistrasiMahasiswa>(0);
    private Set<Mahasiswa> mahasiswas = new HashSet<Mahasiswa>(0);
    private Set<WaktuKuliah> waktuKuliahs = new HashSet<WaktuKuliah>(0);

    public Kampus() {
    }


    public Kampus(String kodeKampus) {
        this.kodeKampus = kodeKampus;
    }

    public Kampus(String kodeKampus, String keterangan, Set<RegistrasiMahasiswa> registrasiMahasiswas, Set<Mahasiswa> mahasiswas, Set<WaktuKuliah> waktuKuliahs) {
        this.kodeKampus = kodeKampus;
        this.keterangan = keterangan;
        this.registrasiMahasiswas = registrasiMahasiswas;
        this.mahasiswas = mahasiswas;
        this.waktuKuliahs = waktuKuliahs;
    }

    @Id


    @Column(name = "kode_kampus", unique = true, nullable = false, length = 45)
    public String getKodeKampus() {
        return this.kodeKampus;
    }

    public void setKodeKampus(String kodeKampus) {
        this.kodeKampus = kodeKampus;
    }


    @Column(name = "keterangan")
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kampus")
    @JsonIgnore
    public Set<RegistrasiMahasiswa> getRegistrasiMahasiswas() {
        return this.registrasiMahasiswas;
    }

    public void setRegistrasiMahasiswas(Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.registrasiMahasiswas = registrasiMahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kampus")
    @JsonBackReference
    public Set<Mahasiswa> getMahasiswas() {
        return this.mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kampus")
    @JsonBackReference
    public Set<WaktuKuliah> getWaktuKuliahs() {
        return this.waktuKuliahs;
    }

    public void setWaktuKuliahs(Set<WaktuKuliah> waktuKuliahs) {
        this.waktuKuliahs = waktuKuliahs;
    }


}


