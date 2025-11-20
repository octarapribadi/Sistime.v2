package bean;

import repo.*;
import model.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SkedulBean implements Serializable {
    Skedul selectedSkedul;
    List<Skedul> skedulList;
    List<String> rowStatus;
    Long krsId;
    Kelas selectedKelas;

    @PostConstruct
    public void init(){
        selectedKelas = new Kelas();
    }

    @EJB
    SkedulManager skedulManager;

    @EJB
    MataKuliahManager mataKuliahManager;

    @EJB
    KelasManager kelasManager;

    private void initSkedul() {
        selectedSkedul = new Skedul();
        selectedSkedul.setIdDosen(new Dosen());
        selectedSkedul.setIdMatakuliah(new MataKuliah());
        selectedSkedul.setIdSkemakrs(new SkemaKrs());
        selectedSkedul.setKodeKelas(new Kelas());
    }

    public void lihatSkedul() {

        skedulList = skedulManager.findAllSkedulAktifByKodeKelasAndKrsId(selectedKelas.getKodeKelas(), krsId);
        if (skedulList == null) {
            skedulList = new ArrayList<>();
            rowStatus = new ArrayList<>();
        } else {
            rowStatus = new ArrayList<>();
            skedulList.forEach(s -> {
                rowStatus.add("none");
            });
        }

    }

    public void tambah() {
        initSkedul();
        selectedKelas = kelasManager.findKelasByKodeKelas(selectedKelas.getKodeKelas());
        selectedSkedul.setKodeKelas(selectedKelas);
        selectedSkedul.getIdSkemakrs().setId(krsId);
        skedulList.add(0, selectedSkedul);
        rowStatus.add(0, "new");
        //initSkedul();
    }

    public void hapusSkedul(Skedul skedul) {
        int pos = skedulList.indexOf(skedul);
        skedulList.remove(skedul);
        rowStatus.remove(pos);
        skedulManager.remove(skedul);
    }

    public void simpan() {
        skedulManager.update(skedulList, rowStatus);
        rowStatus.replaceAll(s->"none");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "data berhasil dimasukan", ""));
    }

    public void statusRowEdit(Skedul skedul) {
        String kodeMatkul = mataKuliahManager.findMataKuliahById(skedul.getIdMatakuliah().getId()).getKodeMatakuliah();
        skedul.getIdMatakuliah().setKodeMatakuliah(kodeMatkul);

        int pos = skedulList.indexOf(skedul);
        if (rowStatus.get(pos).equals("none")) {
            rowStatus.set(pos, "edit");
        }
    }

    public Skedul getSelectedSkedul() {
        return selectedSkedul;
    }

    public void setSelectedSkedul(Skedul selectedSkedul) {
        this.selectedSkedul = selectedSkedul;
    }

    public List<Skedul> getSkedulList() {
        return skedulList;
    }

    public void setSkedulList(List<Skedul> skedulList) {
        this.skedulList = skedulList;
    }

    public Kelas getSelectedKelas() {
        return selectedKelas;
    }

    public void setSelectedKelas(Kelas selectedKelas) {
        this.selectedKelas = selectedKelas;
    }

    public Long getKrsId() {
        return krsId;
    }

    public void setKrsId(Long krsId) {
        this.krsId = krsId;
    }
}
