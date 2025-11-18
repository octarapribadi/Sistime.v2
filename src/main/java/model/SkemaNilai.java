package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_skemanilai")
public class SkemaNilai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nama", length = 45)
    private String nama;

    @Column(name = "aktif")
    private Byte aktif;

    @Column(name = "keterangan")
    private String keterangan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "skemaNilai")
    private List<SkemaNilaiDetail> skemaNilaiDetail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Byte getAktif() {
        return aktif;
    }

    public void setAktif(Byte aktif) {
        this.aktif = aktif;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<SkemaNilaiDetail> getSkemaNilaiDetail() {
        return skemaNilaiDetail;
    }

    public void setSkemaNilaiDetail(List<SkemaNilaiDetail> skemaNilaiDetail) {
        this.skemaNilaiDetail = skemaNilaiDetail;
    }
}