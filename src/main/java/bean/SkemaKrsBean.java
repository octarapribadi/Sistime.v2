package bean;

import repo.KrsMahasiswaManager;
import repo.SkemaKrsManager;
import repo.TahunAjaranManajer;
import model.KrsMahasiswa;
import model.SkemaKrs;
import model.TahunAjaran;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SkemaKrsBean implements Serializable {
    String status;
    @EJB
    SkemaKrsManager skemaKrsManager;

    @EJB
    TahunAjaranManajer tahunAjaranManajer;

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;
    List<SkemaKrs> skemaKrsList;

    SkemaKrs selectedSkemaKrs;
    List<KrsMahasiswa> krsMahasiswaList;

    @Inject
    LihatKrsMahasiswaBean lihatKrsMahasiswaBean;

    @PostConstruct
    public void init() {
        selectedSkemaKrs = new SkemaKrs();
        selectedSkemaKrs.setTahunAjaran(new TahunAjaran());
    }

    public void tambah() {
        status = "new";
        selectedSkemaKrs = new SkemaKrs();
        selectedSkemaKrs.setTahunAjaran(new TahunAjaran());
    }

    public void findAllSkemaKrs() {
        skemaKrsList = skemaKrsManager.findAllSkemaKrs();
    }

    public SkemaKrs findSkemaKrsByAktif() {
        return skemaKrsManager.findSkemaKrsByAktif();
    }

    public void simpan() {
        TahunAjaran t = tahunAjaranManajer.findTahunAjaranById(selectedSkemaKrs.getTahunAjaran().getId());
        selectedSkemaKrs.getTahunAjaran().setTahun(t.getTahun());
        selectedSkemaKrs.getTahunAjaran().setSemester(t.getSemester());
        if (!status.isEmpty() && status.equals("new")) {
            status = "";
            skemaKrsList.add(selectedSkemaKrs);
            skemaKrsManager.persist(selectedSkemaKrs);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"skema berhasil ditambahkan",""));
        }
    }

    public void hapus() {
        if (selectedSkemaKrs != null) {
            skemaKrsList.remove(selectedSkemaKrs);
            skemaKrsManager.hapus(selectedSkemaKrs);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil dihapus!", ""));
        }
    }

    public String aktif(SkemaKrs skemaKrs) {
        if (skemaKrs.getAktif()) {
            skemaKrsManager.aktifkan(skemaKrs);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Skema diaktifkan", ""));
            return "pi pi-check-circle";
        } else {
            skemaKrsManager.nonAktifkan(skemaKrs);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Skema dinon-aktifkan", ""));
            return "pi pi-times-circle";
        }
    }

    public String isAktif(SkemaKrs skemaKrs) {
        if (skemaKrs.getAktif())
            return "isi krs";
        else
            return "";
    }

    public SkemaKrs getSelectedSkemaKrs() {
        return selectedSkemaKrs;
    }

    public void setSelectedSkemaKrs(SkemaKrs selectedSkemaKrs) {
        this.selectedSkemaKrs = selectedSkemaKrs;
    }

    public List<SkemaKrs> getSkemaKrsList() {
        return skemaKrsList;
    }

    public void setSkemaKrsList(List<SkemaKrs> skemaKrsList) {
        this.skemaKrsList = skemaKrsList;
    }

    public List<KrsMahasiswa> getKrsMahasiswaList() {
        return krsMahasiswaList;
    }

    public void setKrsMahasiswaList(List<KrsMahasiswa> krsMahasiswaList) {
        this.krsMahasiswaList = krsMahasiswaList;
    }

}
