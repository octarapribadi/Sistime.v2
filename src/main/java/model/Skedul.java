package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_skedul")
public class Skedul {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skemakrs")
    private SkemaKrs idSkemakrs;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matakuliah")
    private MataKuliah idMatakuliah;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dosen")
    private Dosen idDosen;

    @Column(name = "keterangan")
    private String keterangan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_kelas")
    private Kelas kelas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkemaKrs getIdSkemakrs() {
        return idSkemakrs;
    }

    public void setIdSkemakrs(SkemaKrs idSkemakrs) {
        this.idSkemakrs = idSkemakrs;
    }

    public MataKuliah getIdMatakuliah() {
        return idMatakuliah;
    }

    public void setIdMatakuliah(MataKuliah idMatakuliah) {
        this.idMatakuliah = idMatakuliah;
    }

    public Dosen getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(Dosen idDosen) {
        this.idDosen = idDosen;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Kelas getKodeKelas() {
        return kelas;
    }

    public void setKodeKelas(Kelas kodeKelas) {
        this.kelas = kodeKelas;
    }

}