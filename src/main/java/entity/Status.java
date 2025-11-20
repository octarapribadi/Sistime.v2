package entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_status")
public class Status implements java.io.Serializable {


    private int idStatus;
    private String status;
    private Set<RegistrasiMahasiswa> registrasiMahasiswas = new HashSet<RegistrasiMahasiswa>(0);
    private Set<Mahasiswa> mahasiswas = new HashSet<Mahasiswa>(0);

    public Status() {
    }


    public Status(int idStatus) {
        this.idStatus = idStatus;
    }

    public Status(int idStatus, String status, Set<RegistrasiMahasiswa> registrasiMahasiswas, Set<Mahasiswa> mahasiswas) {
        this.idStatus = idStatus;
        this.status = status;
        this.registrasiMahasiswas = registrasiMahasiswas;
        this.mahasiswas = mahasiswas;
    }

    @Id


    @Column(name = "id_status", unique = true, nullable = false)
    public int getIdStatus() {
        return this.idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }


    @Column(name = "status")
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    @JsonBackReference
    public Set<RegistrasiMahasiswa> getRegistrasiMahasiswas() {
        return this.registrasiMahasiswas;
    }

    public void setRegistrasiMahasiswas(Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.registrasiMahasiswas = registrasiMahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    @JsonBackReference
    public Set<Mahasiswa> getMahasiswas() {
        return this.mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }


}


