package dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class KrsMahasiswaDto {
    Long id, idUser, idSkedul;

    @Min(0)
    @Max(2)
    int tipeSkedul;
    String keterangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdSkedul() {
        return idSkedul;
    }

    public void setIdSkedul(Long idSkedul) {
        this.idSkedul = idSkedul;
    }

    public int getTipeSkedul() {
        return tipeSkedul;
    }

    public void setTipeSkedul(int tipeSkedul) {
        this.tipeSkedul = tipeSkedul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
