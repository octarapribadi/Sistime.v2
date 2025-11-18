package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_skemakrs")
public class SkemaKrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tahunajaran")
    private TahunAjaran tahunAjaran;

    @Column(name = "aktif",columnDefinition = "TINYINT(1)")
    private Boolean aktif;

    @Column(name = "keterangan")
    private String keterangan;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idSkemakrs")
    private List<Skedul> skedul;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "skemaKrs")
    private List<SkemaKrsNilai> skemaKrsNilai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TahunAjaran getTahunAjaran() {
        return tahunAjaran;
    }

    public void setTahunAjaran(TahunAjaran tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public List<Skedul> getSkedul() {
        return skedul;
    }

    public void setSkedul(List<Skedul> skedul) {
        this.skedul = skedul;
    }

    public List<SkemaKrsNilai> getSkemaKrsNilai() {
        return skemaKrsNilai;
    }

    public void setSkemaKrsNilai(List<SkemaKrsNilai> skemaKrsNilai) {
        this.skemaKrsNilai = skemaKrsNilai;
    }
}