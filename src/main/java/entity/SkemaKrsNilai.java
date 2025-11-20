package entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_skemakrs_skemanilai")
public class SkemaKrsNilai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skemakrs")
    private SkemaKrs skemaKrs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skemanilai")
    private SkemaNilai skemaNilai;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkemaKrs getSkemaKrs() {
        return skemaKrs;
    }

    public void setSkemaKrs(SkemaKrs skemaKrs) {
        this.skemaKrs = skemaKrs;
    }

    public SkemaNilai getSkemaNilai() {
        return skemaNilai;
    }

    public void setSkemaNilai(SkemaNilai skemaNilai) {
        this.skemaNilai = skemaNilai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}