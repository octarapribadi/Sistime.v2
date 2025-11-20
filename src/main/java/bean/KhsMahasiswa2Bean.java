package bean;

import repo.*;
import model.*;

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
public class KhsMahasiswa2Bean implements Serializable {
    List<KrsMahasiswa> krsMahasiswas;
    List<SkemaKrs> skemaKrss;
    SkemaKrs selectedSkemaKrs;
    List<JenisNilai> jenisNilais;
    List<SkemaNilaiDetail> skemaNilaiDetails;
    List<KrsNilai> krsNilais;
    Mahasiswa selectedMahasiswa;
    Double ipkSementara = .0;
    String nim;

    @Inject
    SessionBean sessionBean;
    @EJB
    KrsMahasiswaManager krsMahasiswaManager;
    @EJB
    SkemaKrsManager skemaKrsManager;
    @EJB
    Nilai2Manager nilaiManager;
    @EJB
    SkemaNilaiDetailManager skemaNilaiDetailManager;
    @EJB
    SkemaKrsNilaiManager skemaKrsNilaiManager;

    @EJB
    MahasiswaManager mahasiswaManager;

    @PostConstruct
    private void init() {
        krsMahasiswas = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void lihatKhs() {
        //System.out.println(selectedSkemaKrs.getId());
        krsMahasiswas = krsMahasiswaManager.findAllKrsMahasiswaByUserIdAndSkemaKrsId(selectedMahasiswa.getUser().getId(), selectedSkemaKrs.getId());
        SkemaKrsNilai skemaKrsNilai = skemaKrsNilaiManager.findSkemaKrsNilaiByIdSkemaKrs(selectedSkemaKrs.getId());
        skemaNilaiDetails = skemaNilaiDetailManager.findAllSkemaNilaiDetailByIdSkemaNilai(skemaKrsNilai.getSkemaNilai().getId());
        List<Nilai2> nilais = nilaiManager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
        //System.out.println("nilais " + nilais.size());
        krsNilais = new ArrayList<>();
        List<Double> _tempNilaixSks = new ArrayList<>();
        Integer totalSks = 0;
        for (KrsMahasiswa krs : krsMahasiswas) {
            KrsNilai krsNilai = new KrsNilai();
            krsNilai.setKrsMahasiswa(krs);
            List<Nilai2> _tempNilai = new ArrayList<>();

            for (Nilai2 n : nilais) {
                if (n.getKrsMahasiswa().getId().equals(krs.getId())) {
                    krsNilai.getNilais().put(n.getJenisNilai().getJenis(), n.getNilai());
                    _tempNilai.add(n);
                }
            }
            Double totalNilai = hitungTotal(_tempNilai);
            krsNilai.getNilais().put("total", totalNilai);

//            Double perbaikan = (Double) krsNilai.getNilais().get("perbaikan");
            Integer sks = krs.getSkedul().getIdMatakuliah().getSks();
//            if (perbaikan != null && perbaikan > totalNilai) {
//                krsNilai.getNilais().put("huruf", hitungNilaiHuruf(perbaikan));
//                _tempNilaixSks.add(hitungIPK(perbaikan, sks));
//
//            } else {
//                krsNilai.getNilais().put("huruf", hitungNilaiHuruf(totalNilai));
//                _tempNilaixSks.add(hitungIPK(totalNilai, sks));
//            }

            krsNilai.getNilais().put("huruf", hitungNilaiHuruf(totalNilai));
            _tempNilaixSks.add(hitungIPK(totalNilai, sks));

            totalSks += sks;

            krsNilais.add(krsNilai);
        }
        if (!_tempNilaixSks.isEmpty()) {
            Double temp = .0;
            for (Double n : _tempNilaixSks) {
                temp += n;
            }
            ipkSementara = temp / totalSks;
        }
    }

    public Integer tampilkanSemester() {
        if (selectedMahasiswa != null && selectedSkemaKrs != null) {
            Integer angkatan = selectedMahasiswa.getTahunAngkatan();
            SkemaKrs skemaKrs = selectedSkemaKrs;
            Integer tahun = skemaKrs.getTahunAjaran().getTahun();
            int myMod = tahun % angkatan;

            if (skemaKrs.getTahunAjaran().getSemester().equals("GENAP"))
                return tahun - angkatan + myMod + 2;
            else
                return tahun - angkatan + myMod + 1;
        }
        return null;
    }

    public Integer tampilkanSemester(SkemaKrs skemaKrs) {
        if (selectedMahasiswa != null && skemaKrs != null) {
            Integer angkatan = selectedMahasiswa.getTahunAngkatan();
            Integer tahun = skemaKrs.getTahunAjaran().getTahun();
            int myMod = tahun % angkatan;
            if (skemaKrs.getTahunAjaran().getSemester().equals("GENAP"))
                return tahun - angkatan + myMod + 2;
            else
                return tahun - angkatan + myMod + 1;
        }
        return null;
    }

    private Double hitungTotal(List<Nilai2> nilais) {
        double total = .0;
        for (Nilai2 n : nilais) {
            if (!n.getJenisNilai().getJenis().equals("perbaikan")) {
                for (SkemaNilaiDetail snd : skemaNilaiDetails) {
                    if (snd.getJenisNilai().getJenis().equals(n.getJenisNilai().getJenis())) {
                        total += n.getNilai() * snd.getBobot();
                        break;
                    }
                }
            }
        }
        return total;
    }

    private Double hitungIPK(Double totalNilai, Integer sks) {
        double score = .0;
        if (totalNilai >= 90)
            score = 4.0;
        else if (totalNilai >= 75)
            score = 3.0;
        else if (totalNilai >= 60)
            score = 2.0;
        else if (totalNilai >= 50)
            score = 1.0;
        else
            score = .0;
        score = score * Double.valueOf(sks);
        return score;
    }

    private String hitungNilaiHuruf(Double nilai) {
        if (nilai >= 90)
            return "A";
        else if (nilai >= 75)
            return "B";
        else if (nilai >= 60)
            return "C";
        else if (nilai >= 50)
            return "D";
        else
            return "E";
    }

    public Boolean render(SkemaNilaiDetail snd) {
        if (snd != null) {
            if (snd.getJenisNilai().getJenis().equals("perbaikan"))
                return false;
        }
        return true;
    }

    public void findAllKrsMahasiswaByNim(){
        if(nim!=null){
            selectedMahasiswa = mahasiswaManager.findMahasiswaByNim(nim);
            krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(selectedMahasiswa.getUser().getId());
            skemaKrss = skemaKrsManager.findAllSkemaKrs();
            List<SkemaKrs> _temp = new ArrayList<>();
            for (SkemaKrs sk : skemaKrss) {
                boolean ketemu = false;
                for (KrsMahasiswa km : krsMahasiswas) {
                    if (km.getSkedul().getIdSkemakrs().getId().equals(sk.getId())) {
                        ketemu = true;
                        break;
                    }
                }
                if (!ketemu) {
                    _temp.add(sk);
                }
            }
            _temp.forEach(k -> {
                skemaKrss.remove(k);
            });
        }
    }

    public void findAllKrsMahasiswaBySession() {
//        if (sessionBean != null) {
//            krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(sessionBean.getId());
//        }
        if(sessionBean!=null){
            //selectedUserId = sessionBean.getId();
            selectedMahasiswa = mahasiswaManager.findMahasiswaWithStatusMahasiswaByUserId(sessionBean.getId());
            krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(sessionBean.getId());
            skemaKrss = skemaKrsManager.findAllSkemaKrs();
            List<SkemaKrs> _temp = new ArrayList<>();
            for (SkemaKrs sk : skemaKrss) {
                boolean ketemu = false;
                for (KrsMahasiswa km : krsMahasiswas) {
                    if (km.getSkedul().getIdSkemakrs().getId().equals(sk.getId())) {
                        ketemu = true;
                        break;
                    }
                }
                if (!ketemu) {
                    _temp.add(sk);
                }
            }
            _temp.forEach(k -> {
                skemaKrss.remove(k);
            });
        }
    }

    public List<SkemaKrs> getSkemaKrss() {
        return skemaKrss;
    }

    public void setSkemaKrss(List<SkemaKrs> skemaKrss) {
        this.skemaKrss = skemaKrss;
    }

    public SkemaKrs getSelectedSkemaKrs() {
        return selectedSkemaKrs;
    }

    public void setSelectedSkemaKrs(SkemaKrs selectedSkemaKrs) {
        this.selectedSkemaKrs = selectedSkemaKrs;
    }

    public List<KrsMahasiswa> getKrsMahasiswas() {
        return krsMahasiswas;
    }

    public void setKrsMahasiswas(List<KrsMahasiswa> krsMahasiswas) {
        this.krsMahasiswas = krsMahasiswas;
    }

    public List<JenisNilai> getJenisNilais() {
        return jenisNilais;
    }

    public void setJenisNilais(List<JenisNilai> jenisNilais) {
        this.jenisNilais = jenisNilais;
    }

    public List<SkemaNilaiDetail> getSkemaNilaiDetails() {
        return skemaNilaiDetails;
    }

    public void setSkemaNilaiDetails(List<SkemaNilaiDetail> skemaNilaiDetails) {
        this.skemaNilaiDetails = skemaNilaiDetails;
    }

    public List<KrsNilai> getKrsNilais() {
        return krsNilais;
    }

    public void setKrsNilais(List<KrsNilai> krsNilais) {
        this.krsNilais = krsNilais;
    }

    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }

    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }

    public Double getIpkSementara() {
        return ipkSementara;
    }

    public void setIpkSementara(Double ipkSementara) {
        this.ipkSementara = ipkSementara;
    }
}
