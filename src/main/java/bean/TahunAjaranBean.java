package bean;

import repo.TahunAjaranManajer;
import model.TahunAjaran;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class TahunAjaranBean implements Serializable {
    @EJB
    TahunAjaranManajer tahunAjaranManajer;
    List<TahunAjaran> tahunAjarans;

    TahunAjaran selectedTahunAjaran;

    @PostConstruct
    public void init() {
        tahunAjarans = new ArrayList<>();
    }

    public List<TahunAjaran> findAllTahunAjaran() {
        tahunAjarans = tahunAjaranManajer.findAllTahunAjaran();
        return tahunAjaranManajer.findAllTahunAjaran();
    }

    public List<TahunAjaran> getTahunAjarans() {
        return tahunAjarans;
    }

    public void setTahunAjarans(List<TahunAjaran> tahunAjarans) {
        this.tahunAjarans = tahunAjarans;
    }

    public TahunAjaran getSelectedTahunAjaran() {
        return selectedTahunAjaran;
    }

    public void setSelectedTahunAjaran(TahunAjaran selectedTahunAjaran) {
        this.selectedTahunAjaran = selectedTahunAjaran;
    }
}
