package bean;

import repo.SekolahManager;
import model.Sekolah;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class SekolahBean implements Serializable {

    @EJB
    SekolahManager sekolahManager;

    List<Sekolah> sekolahs;
    List<String> namaSekolahs;
    Sekolah selectedSekolah;

    String status = "";

    @PostConstruct
    public void init() {
        sekolahs = new ArrayList<>();
        namaSekolahs = new ArrayList<>();
        selectedSekolah = new Sekolah();
    }

    public List<Sekolah> findAllSekolah() {
        sekolahs = sekolahManager.findAll();
        return sekolahs;
    }

    public void edit() {
        status = "edit";
    }

    public void openNew() {
        status = "tambah";
        selectedSekolah = new Sekolah();
    }

    public void simpan() {
        if(status.equals("tambah")) {
            sekolahManager.persist(selectedSekolah);
            sekolahs.add(selectedSekolah);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sekolah berhasil ditambahkan"));
        }
        else if(status.equals("edit")){
            sekolahManager.merge(selectedSekolah);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Sekolah berhasil diubah"));
        }
    }

    public List<String> listSekolahByNamaSekolah(String query) {
        String queryLowerCase = query.toLowerCase();
        //List<Sekolah> sekolahs = sekolahManager.findAll();
        if (namaSekolahs == null) {
            //List<String> namaSekolah = new ArrayList<>();
            namaSekolahs = new ArrayList<>();
            for (Sekolah s : sekolahs) {
                namaSekolahs.add(s.getNamaSekolah());
            }
        }
        return namaSekolahs.stream().filter(t -> t.toLowerCase()
                .contains(queryLowerCase)).collect(Collectors.toList());
    }

    public List<Sekolah> getSekolahs() {
        return sekolahs;
    }

    public void setSekolahs(List<Sekolah> sekolahs) {
        this.sekolahs = sekolahs;
    }

    public List<Sekolah> complete(String query) {
        String queryLowerCase = query.toLowerCase();
        return sekolahs.stream().filter(t -> t.getNamaSekolah().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public Sekolah getSelectedSekolah() {
        return selectedSekolah;
    }

    public void setSelectedSekolah(Sekolah selectedSekolah) {
        this.selectedSekolah = selectedSekolah;
    }
}
