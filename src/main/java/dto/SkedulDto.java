package dto;

public class SkedulDto {
    Long id;
    Long idSkemaKrs;
    Long idMataKuliah;
    Long idDosen;
    String keterangan;
    String kodeKelas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSkemaKrs() {
        return idSkemaKrs;
    }

    public void setIdSkemaKrs(Long idSkemaKrs) {
        this.idSkemaKrs = idSkemaKrs;
    }

    public Long getIdMataKuliah() {
        return idMataKuliah;
    }

    public void setIdMataKuliah(Long idMataKuliah) {
        this.idMataKuliah = idMataKuliah;
    }

    public Long getIdDosen() {
        return idDosen;
    }

    public void setIdDosen(Long idDosen) {
        this.idDosen = idDosen;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getKodeKelas() {
        return kodeKelas;
    }

    public void setKodeKelas(String kodeKelas) {
        this.kodeKelas = kodeKelas;
    }
}
