package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_matakuliah")
public class MataKuliah implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "kode_matakuliah", length = 45)
    private String kodeMatakuliah;

    @Column(name = "nama_matakuliah")
    private String namaMatakuliah;

    @Column(name = "sks")
    private Integer sks;

    @Column(name = "semester")
    private Integer semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kode_prodi")
    private ProgramStudi kodeProdi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipematakuliah")
    private TipeMataKuliah idTipematakuliah;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeMatakuliah() {
        return kodeMatakuliah;
    }

    public void setKodeMatakuliah(String kodeMatakuliah) {
        this.kodeMatakuliah = kodeMatakuliah;
    }

    public String getNamaMatakuliah() {
        return namaMatakuliah;
    }

    public void setNamaMatakuliah(String namaMatakuliah) {
        this.namaMatakuliah = namaMatakuliah;
    }

    public Integer getSks() {
        return sks;
    }

    public void setSks(Integer sks) {
        this.sks = sks;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public ProgramStudi getKodeProdi() {
        return kodeProdi;
    }

    public void setKodeProdi(ProgramStudi kodeProdi) {
        this.kodeProdi = kodeProdi;
    }

    public TipeMataKuliah getIdTipematakuliah() {
        return idTipematakuliah;
    }

    public void setIdTipematakuliah(TipeMataKuliah idTipematakuliah) {
        this.idTipematakuliah = idTipematakuliah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    /*
    public Skedul getSkedul() {
        return skedul;
    }

    public void setSkedul(Skedul skedul) {
        this.skedul = skedul;
    }

     */
}