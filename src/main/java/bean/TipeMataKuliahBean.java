package bean;

import ejb.TipeMataKuliahManager;
import model.TipeMataKuliah;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class TipeMataKuliahBean implements Serializable {
    List<TipeMataKuliah> tipeMataKuliahs;

    @EJB
    TipeMataKuliahManager tipeMataKuliahManager;

    @PostConstruct
    public void init(){
        tipeMataKuliahs = new ArrayList<>();
    }

    public List<TipeMataKuliah> findAllTipeMatkulh(){
        tipeMataKuliahs = tipeMataKuliahManager.findAllTipeMataKuliah();
        return tipeMataKuliahs;
    }

    public List<TipeMataKuliah> getTipeMataKuliahs() {
        return tipeMataKuliahs;
    }

    public void setTipeMataKuliahs(List<TipeMataKuliah> tipeMataKuliahs) {
        this.tipeMataKuliahs = tipeMataKuliahs;
    }

}