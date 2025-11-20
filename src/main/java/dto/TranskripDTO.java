package dto;

import java.util.HashMap;
import java.util.Map;

public class TranskripDTO {
    long krsId;
    String kodeMatkul, matkul;
    int sks;
    Map<String, Double> nilais = new HashMap<>();

    public long getKrsId() {
        return krsId;
    }

    public void setKrsId(long krsId) {
        this.krsId = krsId;
    }

    public String getKodeMatkul() {
        return kodeMatkul;
    }

    public void setKodeMatkul(String kodeMatkul) {
        this.kodeMatkul = kodeMatkul;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public Map<String, Double> getNilais() {
        return nilais;
    }

    public void setNilais(Map<String, Double> nilais) {
        this.nilais = nilais;
    }
}
