package entity;

public class TabelSemester {
    Integer tahunAjaran;
    Integer semester;
    Double ipk;

    public Integer getTahunAjaran() {
        return tahunAjaran;
    }

    public void setTahunAjaran(Integer tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Double getIpk() {
        return ipk;
    }

    public void setIpk(Double ipk) {
        this.ipk = ipk;
    }
}