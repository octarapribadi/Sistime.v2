package model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_nilai2")
public class Nilai2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_krsmahasiswa")
    private KrsMahasiswa krsMahasiswa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jenis_nilai")
    private JenisNilai jenisNilai;

    @Column(name = "nilai")
    private Double nilai;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JenisNilai getJenisNilai() {
        return jenisNilai;
    }

    public void setJenisNilai(JenisNilai jenisNilai) {
        this.jenisNilai = jenisNilai;
    }

    public Double getNilai() {
        return nilai;
    }

    public void setNilai(Double nilai) {
        this.nilai = nilai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public KrsMahasiswa getKrsMahasiswa() {
        return krsMahasiswa;
    }

    public void setKrsMahasiswa(KrsMahasiswa krsMahasiswa) {
        this.krsMahasiswa = krsMahasiswa;
    }
}