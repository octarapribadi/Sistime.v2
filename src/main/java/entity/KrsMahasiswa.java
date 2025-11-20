package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_krsmahasiswa",uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user","id_skedul"})})
public class KrsMahasiswa {
    public static final Integer WAJIB = 0;
    public static final Integer BAWAH = 1;
    public static final Integer PILIHAN = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skedul")
    private Skedul skedul;

    @Column(name = "keterangan")
    private String keterangan;

    //0: matkul wajib , 1: matkul kelas bawah , 2: matkul pilihan
    @Column(name = "tipe_skedul")
    private Integer tipeSkedul;

    @OneToMany(mappedBy = "krsMahasiswa", fetch = FetchType.LAZY)
    private List<Nilai2> nilai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skedul getSkedul() {
        return skedul;
    }

    public void setSkedul(Skedul skedul) {
        this.skedul = skedul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getTipeSkedul() {
        return tipeSkedul;
    }

    public void setTipeSkedul(Integer tipeSkedul) {
        this.tipeSkedul = tipeSkedul;
    }

    public List<Nilai2> getNilai() {
        return nilai;
    }

    public void setNilai(List<Nilai2> nilai) {
        this.nilai = nilai;
    }
}