package bean;

import repo.WaktuKuliahManager;
import model.WaktuKuliah;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class WaktuKuliahBean implements Serializable {

    @EJB
    WaktuKuliahManager waktuKuliahManager;

    List<WaktuKuliah> waktuKuliahs;

    public List<WaktuKuliah> getWaktuKuliahs() {
        return waktuKuliahs;
    }

    public void setWaktuKuliahs(List<WaktuKuliah> waktuKuliahs) {
        this.waktuKuliahs = waktuKuliahs;
    }

    public void getWaktuKuliahByKampus(String kodeKampus) {
        if (kodeKampus != null && !kodeKampus.isEmpty()) {
            waktuKuliahs = waktuKuliahManager.findWaktuKuliahByKodeKampus(kodeKampus);
        }
    }
    public void findAllWaktuKuliah(){
        waktuKuliahs = waktuKuliahManager.findAllWaktuKuliah();
    }


}
