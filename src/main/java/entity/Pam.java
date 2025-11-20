package entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pam")
public class Pam {

    public static final int DIPROSES = 0;
    public static final int DITOLAK = 1;
    public static final int DITERIMA = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "nama_aktifitas")
    private String namaAktifitas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_kategori_pam")
    private KategoriPam kategoriPam;

    @Column(name = "poin")
    private Integer poin;

    @Column(name = "keterangan")
    private String keterangan;

    @Column(name = "berkas")
    private String berkas;

    @Column(name="status")
    private Integer status;
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

    public String getNamaAktifitas() {
        return namaAktifitas;
    }

    public void setNamaAktifitas(String namaAktifitas) {
        this.namaAktifitas = namaAktifitas;
    }

    public KategoriPam getKategoriPam() {
        return kategoriPam;
    }

    public void setKategoriPam(KategoriPam kategoriPam) {
        this.kategoriPam = kategoriPam;
    }

    public Integer getPoin() {
        return poin;
    }

    public void setPoin(Integer poin) {
        this.poin = poin;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBerkas() {
        return berkas;
    }

    public void setBerkas(String berkas) {
        this.berkas = berkas;
    }
}