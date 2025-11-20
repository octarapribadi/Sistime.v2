package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_agama")
public class Agama implements java.io.Serializable {

    private String idAgama;
    private String agama;
    private Set<RegistrasiMahasiswa> registrasiMahasiswas = new HashSet<RegistrasiMahasiswa>(0);
    @JsonBackReference
    private Set<Mahasiswa> mahasiswas = new HashSet<Mahasiswa>(0);

    public Agama() {
    }


    public Agama(String idAgama) {
        this.idAgama = idAgama;
    }

    public Agama(String idAgama, String agama, Set<RegistrasiMahasiswa> registrasiMahasiswas, Set<Mahasiswa> mahasiswas) {
        this.idAgama = idAgama;
        this.agama = agama;
        this.registrasiMahasiswas = registrasiMahasiswas;
        this.mahasiswas = mahasiswas;
    }

    @Id

    @Column(name = "id_agama", unique = true, nullable = false, length = 45)
    public String getIdAgama() {
        return this.idAgama;
    }

    public void setIdAgama(String idAgama) {
        this.idAgama = idAgama;
    }


    @Column(name = "agama", length = 45)
    public String getAgama() {
        return this.agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agama")
    @JsonIgnore
    public Set<RegistrasiMahasiswa> getRegistrasiMahasiswas() {
        return this.registrasiMahasiswas;
    }

    public void setRegistrasiMahasiswas(Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.registrasiMahasiswas = registrasiMahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agama")
    public Set<Mahasiswa> getMahasiswas() {
        return this.mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }


}


