package entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 45, unique = true)
    private String username;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "keterangan")
    private String keterangan;

    @OneToOne(mappedBy = "user")
    private Mahasiswa mahasiswa;

    @OneToOne(mappedBy = "user")
    private StatusMahasiswa statusMahasiswa;

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public StatusMahasiswa getStatusMahasiswa() {
        return statusMahasiswa;
    }

    public void setStatusMahasiswa(StatusMahasiswa statusMahasiswa) {
        this.statusMahasiswa = statusMahasiswa;
    }
}