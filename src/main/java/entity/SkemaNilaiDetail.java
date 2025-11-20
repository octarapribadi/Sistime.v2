package entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_skemanilai_detail")
public class SkemaNilaiDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skemanilai")
    private SkemaNilai skemaNilai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jenis_nilai")
    private JenisNilai jenisNilai;

    @Column(name = "bobot")
    private Float bobot;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkemaNilai getSkemaNilai() {
        return skemaNilai;
    }

    public void setSkemaNilai(SkemaNilai skemaNilai) {
        this.skemaNilai = skemaNilai;
    }

    public JenisNilai getJenisNilai() {
        return jenisNilai;
    }

    public void setJenisNilai(JenisNilai jenisNilai) {
        this.jenisNilai = jenisNilai;
    }

    public Float getBobot() {
        return bobot;
    }

    public void setBobot(Float bobot) {
        this.bobot = bobot;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}