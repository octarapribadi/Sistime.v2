package entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_programstudi")
public class ProgramStudi implements java.io.Serializable {

    private String kodeProgramstudi;
    private String keterangan;
    private String namaProgramstudi;
    private Set<Mahasiswa> mahasiswas = new HashSet<Mahasiswa>(0);
    private Set<RegistrasiMahasiswa> registrasiMahasiswas = new HashSet<RegistrasiMahasiswa>(0);

    public ProgramStudi() {
    }


    public ProgramStudi(String kodeProgramstudi) {
        this.kodeProgramstudi = kodeProgramstudi;
    }

    public ProgramStudi(String kodeProgramstudi, String keterangan, String namaProgramstudi, Set<Mahasiswa> mahasiswas, Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.kodeProgramstudi = kodeProgramstudi;
        this.keterangan = keterangan;
        this.namaProgramstudi = namaProgramstudi;
        this.mahasiswas = mahasiswas;
        this.registrasiMahasiswas = registrasiMahasiswas;
    }

    @Id
    @Column(name = "kode_programstudi", unique = true, nullable = false, length = 45)
    public String getKodeProgramstudi() {
        return this.kodeProgramstudi;
    }

    public void setKodeProgramstudi(String kodeProgramstudi) {
        this.kodeProgramstudi = kodeProgramstudi;
    }


    @Column(name = "keterangan")
    public String getKeterangan() {
        return this.keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    @Column(name = "nama_programstudi", length = 45)
    public String getNamaProgramstudi() {
        return this.namaProgramstudi;
    }

    public void setNamaProgramstudi(String namaProgramstudi) {
        this.namaProgramstudi = namaProgramstudi;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programStudi")
    public Set<Mahasiswa> getMahasiswas() {
        return this.mahasiswas;
    }

    public void setMahasiswas(Set<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programStudi")
    public Set<RegistrasiMahasiswa> getRegistrasiMahasiswas() {
        return this.registrasiMahasiswas;
    }

    public void setRegistrasiMahasiswas(Set<RegistrasiMahasiswa> registrasiMahasiswas) {
        this.registrasiMahasiswas = registrasiMahasiswas;
    }


}


