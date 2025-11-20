package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_jenisnilai")
public class JenisNilai {
    @Id
    @Column(name = "jenis", nullable = false, length = 20)
    private String jenis;

    @Column(name = "aktif")
    private Byte aktif;

    @Column(name = "keterangan")
    private String keterangan;

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
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

}