package model;

import java.io.Serializable;

public class MahasiswaKrsNilai implements Serializable {
    Mahasiswa mahasiswa;
    KrsNilai krsNilai;

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public KrsNilai getKrsNilai() {
        return krsNilai;
    }

    public void setKrsNilai(KrsNilai krsNilai) {
        this.krsNilai = krsNilai;
    }
}
