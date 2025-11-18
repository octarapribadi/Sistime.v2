package bean;

import ejb.JenisNilaiManager;
import ejb.SkedulManager;
import ejb.SkemaKrsNilaiManager;
import model.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class JenisNilaiBean implements Serializable {
    JenisNilai selectedJenisNilai;
    List<JenisNilai> jenisNilais;
    List<JenisNilai> selectedJenisNilais;
    @EJB
    JenisNilaiManager jenisNilaiManager;
    @EJB
    SkedulManager skedulManager;
    @EJB
    SkemaKrsNilaiManager skemaKrsNilaiManager;

    @PostConstruct
    private void init(){
        selectedJenisNilai = new JenisNilai();
        selectedJenisNilais = new ArrayList<>();
        jenisNilais = new ArrayList<>();
    }

    public List<JenisNilai> fillJenisNilaiByKodeKelasAndIdMatkul(String kodeKelas, Long idMatkul){
        Skedul skedul = skedulManager.findSkedulByKodeKelasAndIdMataKuliah(kodeKelas, idMatkul);
        if(skedul!=null && skedul.getIdSkemakrs()!=null){
            jenisNilais = new ArrayList<>();
            Long idKrs = skedul.getIdSkemakrs().getId();
            SkemaKrsNilai skemaKrsNilai = skemaKrsNilaiManager.findSkemaKrsNilaiByIdSkemaKrs(idKrs);
            skemaKrsNilai.getSkemaNilai().getSkemaNilaiDetail().forEach(snd->{
                jenisNilais.add(snd.getJenisNilai());
            });
        }
        return jenisNilais;
    }

    public List<JenisNilai> findAllJenisNilai(){
        jenisNilais = jenisNilaiManager.findAllJenisNilai();
        return jenisNilais;
    }

    public List<JenisNilai> getJenisNilais() {
        return jenisNilais;
    }

    public void setJenisNilais(List<JenisNilai> jenisNilais) {
        this.jenisNilais = jenisNilais;
    }

    public JenisNilai getSelectedJenisNilai() {
        return selectedJenisNilai;
    }

    public void setSelectedJenisNilai(JenisNilai selectedJenisNilai) {
        this.selectedJenisNilai = selectedJenisNilai;
    }

    public List<JenisNilai> getSelectedJenisNilais() {
        return selectedJenisNilais;
    }

    public void setSelectedJenisNilais(List<JenisNilai> selectedJenisNilais) {
        this.selectedJenisNilais = selectedJenisNilais;
    }
}
