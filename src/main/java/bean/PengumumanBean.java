package bean;

import repo.PengumumanManager;
import model.Pengumuman;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class PengumumanBean implements Serializable {
    @EJB
    PengumumanManager pengumumanManager;

    List<Pengumuman> pengumumans;
    Pengumuman selectedPengumuman;

    @PostConstruct
    public void init(){
        pengumumans = new ArrayList<>();
        selectedPengumuman = new Pengumuman();
    }

    public List<Pengumuman> findAllPengumuman(){
        pengumumans = pengumumanManager.findAllPengumuman();
        return pengumumans;
    }

    public List<Pengumuman> getPengumumanAktif(){
        return pengumumanManager.findAllPengumumanAktif();
    }

    public List<Pengumuman> getPengumumans() {
        return pengumumans;
    }

    public void setPengumumans(List<Pengumuman> pengumumans) {
        this.pengumumans = pengumumans;
    }

    public Pengumuman getSelectedPengumuman() {
        return selectedPengumuman;
    }

    public void setSelectedPengumuman(Pengumuman selectedPengumuman) {
        this.selectedPengumuman = selectedPengumuman;
    }


}
