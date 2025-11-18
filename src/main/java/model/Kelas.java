package model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_kelas")
public class Kelas {
    @Id
    @Column(name = "kode_kelas", nullable = false, length = 45)
    private String kodeKelas;

    @Column(name = "nama_kelas", length = 45)
    private String namaKelas;

    @Column(name = "angkatan")
    private Integer angkatan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dosenwali")
    private Dosen idDosenwali;

    @Column(name = "keterangan")
    private String keterangan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_prodi")
    private ProgramStudi programStudi;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "kelas")
    @JsonBackReference
    private List<StatusMahasiswa> statusMahasiswa;

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String id) {
        this.kodeKelas = id;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public Integer getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(Integer angkatan) {
        this.angkatan = angkatan;
    }

    public Dosen getIdDosenwali() {
        return idDosenwali;
    }

    public void setIdDosenwali(Dosen idDosenwali) {
        this.idDosenwali = idDosenwali;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public ProgramStudi getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    public List<StatusMahasiswa> getStatusMahasiswa() {
        return statusMahasiswa;
    }

    public void setStatusMahasiswa(List<StatusMahasiswa> statusMahasiswa) {
        this.statusMahasiswa = statusMahasiswa;
    }
}