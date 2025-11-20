package rest.model;

public class KrsMahasiswaDTO {
    Long idKrs, idSkemaKrs;
    int semester;
    String kodeMatakuliah, namaMatakuliah, namaDosen;

    public Long getIdKrs() {
        return idKrs;
    }

    public void setIdKrs(Long idKrs) {
        this.idKrs = idKrs;
    }

    public Long getIdSkemaKrs() {
        return idSkemaKrs;
    }

    public void setIdSkemaKrs(Long idSkemaKrs) {
        this.idSkemaKrs = idSkemaKrs;
    }

    public String getKodeMatakuliah() {
        return kodeMatakuliah;
    }

    public void setKodeMatakuliah(String kodeMatakuliah) {
        this.kodeMatakuliah = kodeMatakuliah;
    }

    public String getNamaMatakuliah() {
        return namaMatakuliah;
    }

    public void setNamaMatakuliah(String namaMatakuliah) {
        this.namaMatakuliah = namaMatakuliah;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
