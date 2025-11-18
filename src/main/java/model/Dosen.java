package model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_dosen")
public class Dosen {
    Long id;
    String namaDosen;
    String statusDosen;
    String email;
    String noTelepon;
    String alamat;
    String keterangan;

    public Dosen(String namaDosen, String statusDosen, String email, String noTelepon, String alamat, String keterangan) {
        this.namaDosen = namaDosen;
        this.statusDosen = statusDosen;
        this.email = email;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
        this.keterangan = keterangan;
    }

    public Dosen() {

    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="nama_dosen",length = 45)
    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    @Column(name="status_dosen",length = 45)
    public String getStatusDosen() {
        return statusDosen;
    }

    public void setStatusDosen(String statusDosen) {
        this.statusDosen = statusDosen;
    }
    @Column(name="email",length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="no_telepon",length = 45)
    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    @Column(name="alamat",length = 255)
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Column(name="keterangan",length = 255)
    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
