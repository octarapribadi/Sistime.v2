package dto;

import javax.validation.constraints.NotNull;

public class SkemaKrsDto {
    Long id;
    Long idTahunAjaran;
    Boolean aktif;

    String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTahunAjaran() {
        return idTahunAjaran;
    }

    public void setIdTahunAjaran(Long idTahunAjaran) {
        this.idTahunAjaran = idTahunAjaran;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
