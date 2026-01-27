package dto;

import java.util.HashMap;
import java.util.Map;

public class KhsDto {
    long idKrs;
    String namaMatakuliah;
    String kodeMatakuliah;
    int sks;
    int semester;
    Map<String, Double> nilais = new HashMap<>();

    public long getIdKrs() {
        return idKrs;
    }

    public void setIdKrs(long idKrs) {
        this.idKrs = idKrs;
    }

    public String getNamaMatakuliah() {
        return namaMatakuliah;
    }

    public void setNamaMatakuliah(String namaMatakuliah) {
        this.namaMatakuliah = namaMatakuliah;
    }

    public Map<String, Double> getNilais() {
        return nilais;
    }

    public void setNilais(Map<String, Double> nilais) {
        this.nilais = nilais;
    }

    public String getKodeMatakuliah() {
        return kodeMatakuliah;
    }

    public void setKodeMatakuliah(String kodeMatakuliah) {
        this.kodeMatakuliah = kodeMatakuliah;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
