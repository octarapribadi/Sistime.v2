package bean;

import ejb.WaktuKuliahManager;
import model.WaktuKuliah;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
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
