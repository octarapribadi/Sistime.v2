package entity;

import java.io.Serializable;

public class MahasiswaNilai implements Serializable {
    private KrsMahasiswa krsMahasiswa;
    private String nim;
    private String namaMahasiswa;
    private Double absensi;
    private Double tugas;
    private Double uts;
    private Double uas;
    private String keterangan;

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public Double getAbsensi() {
        return absensi;
    }

    public void setAbsensi(Double absensi) {
        this.absensi = absensi;
    }

    public Double getTugas() {
        return tugas;
    }

    public void setTugas(Double tugas) {
        this.tugas = tugas;
    }

    public Double getUts() {
        return uts;
    }

    public void setUts(Double uts) {
        this.uts = uts;
    }

    public Double getUas() {
        return uas;
    }

    public void setUas(Double uas) {
        this.uas = uas;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public KrsMahasiswa getKrsMahasiswa() {
        return krsMahasiswa;
    }

    public void setKrsMahasiswa(KrsMahasiswa krsMahasiswa) {
        this.krsMahasiswa = krsMahasiswa;
    }
}
