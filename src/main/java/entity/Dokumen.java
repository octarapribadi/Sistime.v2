package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_dokumen")
public class Dokumen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tanggal")
    private LocalDate tanggal;

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jenisdokumen")
    private JenisDokumen jenisDokumen;

    @Column(name = "url")
    private String url;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public JenisDokumen getJenisDokumen() {
        return jenisDokumen;
    }

    public void setJenisDokumen(JenisDokumen jenisDokumen) {
        this.jenisDokumen = jenisDokumen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}