package rest.model;

import model.KrsMahasiswa;
import model.MataKuliah;
import model.Nilai2;

public class KhsDTO {
    long id;
    KrsMahasiswa krsMahasiswa;
    MataKuliah mataKuliah;
    Nilai2 nilai;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public KrsMahasiswa getKrsMahasiswa() {
        return krsMahasiswa;
    }

    public void setKrsMahasiswa(KrsMahasiswa krsMahasiswa) {
        this.krsMahasiswa = krsMahasiswa;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public Nilai2 getNilai() {
        return nilai;
    }

    public void setNilai(Nilai2 nilai) {
        this.nilai = nilai;
    }
}
