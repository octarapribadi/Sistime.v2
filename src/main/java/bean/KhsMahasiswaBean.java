package bean;

import ejb.NilaiManager;
import model.Nilai;
import model.NilaiKeterangan;
import model.TabelSemester;
import model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class KhsMahasiswaBean implements Serializable {

    List<NilaiKeterangan> nilaiKeterangans;
    List<Nilai> nilais;
    List<Nilai> nilaisPerSemester;
    @EJB
    NilaiManager nilaiManager;

    List<TabelSemester> tabelSemesters;

    @PostConstruct
    public void init() {
        nilaiKeterangans = new ArrayList<>();
        tabelSemesters = new ArrayList<>();
        nilais = new ArrayList<>();
    }

    private Double hitungAvgNilai(Nilai n) {
        if (n != null) {
            double rata2 = 0.0;
            rata2 += n.getAbsensi() != null ? n.getAbsensi() * 0.1 : 0;
            rata2 += n.getTugas() != null ? n.getTugas() * 0.2 : 0;
            rata2 += n.getUts() != null ? n.getUts() * 0.3 : 0;
            rata2 += n.getUas() != null ? n.getUas() * 0.4 : 0;
            return rata2;
        }
        return null;
    }

    public List<Nilai> findNilaiByIdUser(Long idUser) {
        nilais = nilaiManager.findNilaiByIdUser(idUser);
        return nilais;
    }

    public List<NilaiKeterangan> generateNilaiKeteranganBySemester(Integer semester) {
        nilaiKeterangans = new ArrayList<>();
        for (Nilai n : nilais) {
            if (n.getKrsMahasiswa().getSkedul().getIdMatakuliah().getSemester().equals(semester)) {
                NilaiKeterangan nilaiKeterangan = new NilaiKeterangan();
                nilaiKeterangan.setNilaiPerSemester(n);

                //hitung rata-rata
                Double rata2 = hitungAvgNilai(n);
                nilaiKeterangan.setNilaiAngka(rata2);

                //hitung nilai huruf
                if (rata2 >= 90) {
                    nilaiKeterangan.setNilaiHuruf("A");
                    nilaiKeterangan.setNilaiIPK(4.0);
                } else if (rata2 >= 75) {
                    nilaiKeterangan.setNilaiHuruf("B");
                    nilaiKeterangan.setNilaiIPK(3.0);
                } else if (rata2 >= 60) {
                    nilaiKeterangan.setNilaiHuruf("C");
                    nilaiKeterangan.setNilaiIPK(2.0);
                } else if (rata2 >= 50) {
                    nilaiKeterangan.setNilaiHuruf("D");
                    nilaiKeterangan.setNilaiIPK(1.0);
                } else {
                    nilaiKeterangan.setNilaiHuruf("E");
                    nilaiKeterangan.setNilaiIPK(0.0);
                }
                nilaiKeterangans.add(nilaiKeterangan);
            }
        }
        if (!nilaiKeterangans.isEmpty())
            return nilaiKeterangans;
        else
            return null;
    }

    public Double generateIPKBySemester(Integer semester) {
        if (!nilais.isEmpty()) {
            int sumNilai = 0;
            int sumSks = 0;
            for (Nilai n : nilais) {
                if (n.getKrsMahasiswa().getSkedul().getIdMatakuliah().getSemester().equals(semester)) {
                    //cari sks matkul
                    int sks =n.getKrsMahasiswa().getSkedul().getIdMatakuliah().getSks();

                    //hitung total sks
                    sumSks+=sks;

                    //hitung total nilai absen, tugas, uts, uas
                    Double rata2 = hitungAvgNilai(n);
                    if (rata2 >= 90) sumNilai += 4*sks;
                    else if (rata2 >= 75) sumNilai += 3*sks;
                    else if (rata2 >= 60) sumNilai += 2*sks;
                    else if (rata2 >= 50) sumNilai += 1*sks;
                    else sumNilai += 0;
                }
            }
            return (double)sumNilai / (double)sumSks;
        }
        return null;
}

    public List<Nilai> generateNilaiBySemester(Integer semester) {
        nilaisPerSemester = new ArrayList<Nilai>();
        for (Nilai n : nilais) {
            if (n.getKrsMahasiswa().getSkedul().getIdMatakuliah().getSemester().equals(semester)) {
                nilaisPerSemester.add(n);
            }
        }
        return nilaisPerSemester;
    }

    public Double generateIPKByNilaiKeterangans() {
        if (!nilaiKeterangans.isEmpty()) {
            Double totalNilai = 0.0;
            int totalSks = 0;
            for (NilaiKeterangan n : nilaiKeterangans) {
                int sks = n.getNilaiPerSemester().getKrsMahasiswa().getSkedul().getIdMatakuliah().getSks();
                totalNilai += n.getNilaiIPK() * sks;
                totalSks += sks;

            }
            return totalNilai / totalSks;
        }
        return 0.0;

    }

    //membuat tabelsemester yang berisi list semester siswa yang sedang login
    public List<TabelSemester> findDistinctSemesterFromListNilai() {
        if (nilais != null) {
            tabelSemesters = new ArrayList<>();
            TabelSemester tabelSemester;
            for (Nilai nilai : nilais) {
                boolean ketemu = false;
                for (TabelSemester ts : tabelSemesters) {
                    if (nilai.getKrsMahasiswa().getSkedul().getIdMatakuliah().getSemester()
                            .equals(ts.getSemester())) {
                        ketemu = true;
                        break;
                    }
                }
                if (!ketemu) {
                    tabelSemester = new TabelSemester();
                    tabelSemester.setSemester(nilai.getKrsMahasiswa().getSkedul().getIdMatakuliah()
                            .getSemester());
                    tabelSemester.setTahunAjaran(nilai.getKrsMahasiswa().getSkedul().getIdSkemakrs().getTahunAjaran().getTahun());
                    tabelSemesters.add(tabelSemester);
                }
            }
            return tabelSemesters;
        }
        return null;
    }

    public List<Nilai> getNilais() {
        return nilais;
    }

    public void setNilais(List<Nilai> nilais) {
        this.nilais = nilais;
    }

    public void findNilaiByUserId(Long idUser) {
        nilais = nilaiManager.findNilaiByIdUser(idUser);
    }

    public List<TabelSemester> getTabelSemesters() {
        return tabelSemesters;
    }

    public void setTabelSemesters(List<TabelSemester> tabelSemesters) {
        this.tabelSemesters = tabelSemesters;
    }

    public List<Nilai> getNilaisPerSemester() {
        return nilaisPerSemester;
    }

    public void setNilaisPerSemester(List<Nilai> nilaisPerSemester) {
        this.nilaisPerSemester = nilaisPerSemester;
    }

    public List<NilaiKeterangan> getNilaiKeterangans() {
        return nilaiKeterangans;
    }

    public void setNilaiKeterangans(List<NilaiKeterangan> nilaiKeterangans) {
        this.nilaiKeterangans = nilaiKeterangans;
    }

}
