package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_statusmahasiswa")
public class StatusMahasiswa implements Serializable {

    public static final String AKTIF = "AKTIF";
    public static final String NONAKTIF = "NONAKTIF";
    public static final String CUTI = "CUTI";
    public static final String DROPOUT = "DROPOUT";
    public static final String ALUMNI = "ALUMNI";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonManagedReference
    private User user;

    @Column(name = "nim", length = 45)
    private String nim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_kelas")
    @JsonManagedReference
    private Kelas kelas;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "keterangan")
    private String keterangan;

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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Kelas getKodeKelas() {
        return kelas;
    }

    public void setKodeKelas(Kelas kodeKelas) {
        this.kelas = kodeKelas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}