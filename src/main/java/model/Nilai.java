package model;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_nilai")
public class Nilai implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_krsmahasiswa", referencedColumnName = "id", unique = true)
    private KrsMahasiswa krsMahasiswa;

    @Column(name = "absensi")
    private Double absensi;

    @Column(name = "tugas")
    private Double tugas;

    @Column(name = "uts")
    private Double uts;

    @Column(name = "uas")
    private Double uas;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KrsMahasiswa getKrsMahasiswa() {
        return krsMahasiswa;
    }

    public void setKrsMahasiswa(KrsMahasiswa krsMahasiswa) {
        this.krsMahasiswa = krsMahasiswa;
    }

    public Double getAbsensi() {
        return absensi;
    }

    public void setAbsensi(Double absensi) {
        this.absensi = absensi;
    }

    public Double getTugas() {
        return tugas;
    }

    public void setTugas(Double tugas) {
        this.tugas = tugas;
    }

    public Double getUts() {
        return uts;
    }

    public void setUts(Double uts) {
        this.uts = uts;
    }

    public Double getUas() {
        return uas;
    }

    public void setUas(Double uas) {
        this.uas = uas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}