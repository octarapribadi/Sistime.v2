package dto;

public class WaktuKuliahDto {
    private Integer idWaktukuliah;
    private String kodeKampus;
    private String waktuKuliah;
    private String keterangan;

    public Integer getIdWaktukuliah() {
        return idWaktukuliah;
    }

    public void setIdWaktukuliah(Integer idWaktukuliah) {
        this.idWaktukuliah = idWaktukuliah;
    }

    public String getKodeKampus() {
        return kodeKampus;
    }

    public void setKodeKampus(String kodeKampus) {
        this.kodeKampus = kodeKampus;
    }

    public String getWaktuKuliah() {
        return waktuKuliah;
    }

    public void setWaktuKuliah(String waktuKuliah) {
        this.waktuKuliah = waktuKuliah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
