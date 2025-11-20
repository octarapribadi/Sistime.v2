package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_tipematakuliah")
public class TipeMataKuliah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tipe_matakuliah", length = 45)
    private String tipeMatakuliah;

    @Column(name = "keterangan")
    private String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipeMatakuliah() {
        return tipeMatakuliah;
    }

    public void setTipeMatakuliah(String tipeMatakuliah) {
        this.tipeMatakuliah = tipeMatakuliah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idTipematakuliah")
    private Set<MataKuliah> mataKuliahs = new HashSet<>();

    public Set<MataKuliah> getMataKuliahs() {
        return mataKuliahs;
    }

    public void setMataKuliahs(Set<MataKuliah> mataKuliahs) {
        this.mataKuliahs = mataKuliahs;
    }
}