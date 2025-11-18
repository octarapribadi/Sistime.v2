package model;

public class NilaiKeterangan {
    Nilai nilaiPerSemester;
    Double nilaiAngka;
    //nilaiIPK per satu matakuliah
    Double nilaiIPK;
    String nilaiHuruf;

    public Nilai getNilaiPerSemester() {
        return nilaiPerSemester;
    }

    public void setNilaiPerSemester(Nilai nilaiPerSemester) {
        this.nilaiPerSemester = nilaiPerSemester;
    }

    public Double getNilaiAngka() {
        return nilaiAngka;
    }

    public void setNilaiAngka(Double nilaiAngka) {
        this.nilaiAngka = nilaiAngka;
    }

    public String getNilaiHuruf() {
        return nilaiHuruf;
    }

    public void setNilaiHuruf(String nilaiHuruf) {
        this.nilaiHuruf = nilaiHuruf;
    }

    public Double getNilaiIPK() {
        return nilaiIPK;
    }

    public void setNilaiIPK(Double nilaiIPK) {
        this.nilaiIPK = nilaiIPK;
    }
}
