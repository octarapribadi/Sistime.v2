package entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "tbl_mahasiswa_file")
public class MahasiswaFile implements java.io.Serializable {


    private Long id;
    private Mahasiswa mahasiswa;
    private String file;
    private String tipeFile;

    public MahasiswaFile() {
    }

    public MahasiswaFile(Mahasiswa mahasiswa, String file, String tipeFile) {
        this.mahasiswa = mahasiswa;
        this.file = file;
        this.tipeFile = tipeFile;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)


    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pendaftaran")
    public Mahasiswa getMahasiswa() {
        return this.mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }


    @Column(name = "file")
    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    @Column(name = "tipe_file")
    public String getTipeFile() {
        return this.tipeFile;
    }

    public void setTipeFile(String tipeFile) {
        this.tipeFile = tipeFile;
    }

}


