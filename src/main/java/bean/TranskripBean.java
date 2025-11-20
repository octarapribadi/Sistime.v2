package bean;

import repo.KrsMahasiswaManager;
import repo.MahasiswaManager;
import repo.Nilai2Manager;
import repo.StatusMahasiswaManager;
import model.KrsMahasiswa;
import model.Mahasiswa;
import model.KrsNilai;
import model.Nilai2;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@ViewScoped
public class TranskripBean implements Serializable {

    @Inject
    SessionBean sessionBean;

    @Inject
    NilaiAdapterBean nilaiAdapterBean;

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    @EJB
    Nilai2Manager nilai2Manager;

    @EJB
    MahasiswaManager mahasiswaManager;

    @EJB
    StatusMahasiswaManager statusMahasiswaManager;

    int countMatkulGagal;
    int countMatkulLulus;
    double calculateIPK;
    int sumIpk;

    String nim;

    Mahasiswa selectedMahasiswa;

    List<KrsNilai> mhsNilais;

    Set<String> jenisNilais;

    @PostConstruct
    private void init() {
        selectedMahasiswa = new Mahasiswa();
        mhsNilais = new ArrayList<>();
        jenisNilais = new HashSet<>();
        nilaiAdapterBean.setRule(new NilaiRule());
    }

    public void fillNilai() throws Exception {
        if (nim != null) {
            selectedMahasiswa = mahasiswaManager.findMahasiswaByNim(nim);
            if (selectedMahasiswa != null) {
                mhsNilais.clear();
                jenisNilais.clear();

                List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(selectedMahasiswa.getUser().getId());
                List<Nilai2> nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
                nilai2s.forEach((n) -> {
                    jenisNilais.add(n.getJenisNilai().getJenis());
                });

                krsMahasiswas.forEach((krs) -> {
                    KrsNilai mhsNilai = new KrsNilai();
                    mhsNilai.setKrsMahasiswa(krs);
                    mhsNilai.setNilais(generateNilaiMapByKrsMahasiswa(nilai2s, krs.getId()));

                    mhsNilais.add(mhsNilai);
                });
                //hitung jumlahmatkulgagal, matkullulus, jumlahsks, ipksementara
                fillSummary();
            }
        }

    }

    public void fillNilaiBySession() throws Exception {
            selectedMahasiswa = mahasiswaManager.findMahasiswaByIdUser(sessionBean.getId());

            if (selectedMahasiswa != null) {
                mhsNilais.clear();
                jenisNilais.clear();

                List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(selectedMahasiswa.getUser().getId());
                List<Nilai2> nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
                nilai2s.forEach((n) -> {
                    jenisNilais.add(n.getJenisNilai().getJenis());
                });

                krsMahasiswas.forEach((krs) -> {
                    KrsNilai mhsNilai = new KrsNilai();
                    mhsNilai.setKrsMahasiswa(krs);
                    mhsNilai.setNilais(generateNilaiMapByKrsMahasiswa(nilai2s, krs.getId()));

                    mhsNilais.add(mhsNilai);
                });
                //hitung jumlahmatkulgagal, matkullulus, jumlahsks, ipksementara
                fillSummary();
            }


    }

    private void fillSummary() throws Exception {
        countMatkulGagal = 0;
        countMatkulLulus = 0;
        calculateIPK = .0;
        sumIpk = 0;
        double sumNilaixSks = 0.0;
        for (KrsNilai mhsNilai : mhsNilais) {
            int sks = mhsNilai.getKrsMahasiswa().getSkedul().getIdMatakuliah().getSks();
            sumIpk += sks;
            String nilaiHuruf = nilaiAdapterBean.hitung(mhsNilai.getNilais(),mhsNilai.getKrsMahasiswa().getSkedul().getIdSkemakrs().getId());

            switch (nilaiHuruf) {
                case "A":
                    sumNilaixSks += 4 * sks;
                    break;
                case "B":
                    sumNilaixSks += 3 * sks;
                    break;
                case "C":
                    sumNilaixSks += 2 * sks;
                    break;
                case "D":
                    sumNilaixSks += sks;
                    break;
                case "E":
                case "":
                    sumNilaixSks += 0;
                    break;
            }


            if (nilaiHuruf.equals("D") || nilaiHuruf.equals("E") || nilaiHuruf.isEmpty())
                countMatkulGagal++;
            else
                countMatkulLulus++;
        }
        calculateIPK = sumNilaixSks / sumIpk;
    }

    private Map<String, Object> generateNilaiMapByKrsMahasiswa(List<Nilai2> nilai2s, Long krsMahasiswaId) {
        Map<String, Object> myMap = new HashMap<>();
        for (Nilai2 n : nilai2s) {
            if (Objects.equals(krsMahasiswaId, n.getKrsMahasiswa().getId())) {
                myMap.put(n.getJenisNilai().getJenis(), n.getNilai());
            }
        }
        return myMap;
    }

    public List<KrsNilai> getMhsNilais() {
        return mhsNilais;
    }

    public void setMhsNilais(List<KrsNilai> mhsNilais) {
        this.mhsNilais = mhsNilais;
    }

    public Set<String> getJenisNilais() {
        return jenisNilais;
    }

    public void setJenisNilais(Set<String> jenisNilais) {
        this.jenisNilais = jenisNilais;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }

    public int getCountMatkulGagal() {
        return countMatkulGagal;
    }

    public void setCountMatkulGagal(int countMatkulGagal) {
        this.countMatkulGagal = countMatkulGagal;
    }

    public int getCountMatkulLulus() {
        return countMatkulLulus;
    }

    public void setCountMatkulLulus(int countMatkulLulus) {
        this.countMatkulLulus = countMatkulLulus;
    }

    public double getCalculateIPK() {
        return calculateIPK;
    }

    public void setCalculateIPK(double calculateIPK) {
        this.calculateIPK = calculateIPK;
    }

    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }

    public int getSumIpk() {
        return sumIpk;
    }

    public void setSumIpk(int sumIpk) {
        this.sumIpk = sumIpk;
    }
}
