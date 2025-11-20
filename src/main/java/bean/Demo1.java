package bean;

import repo.SekolahManager;
import model.Sekolah;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class Demo1 implements Serializable {

    Sekolah selectedSekolah;
    @EJB
    SekolahManager sekolahManager;

    List<Sekolah> sekolahs;

    @PostConstruct
    public void init(){
        sekolahs = sekolahManager.findAll();
        selectedSekolah=new Sekolah();
    }
    public List<Sekolah> complete(String query) {
        String queryLowerCase = query.toLowerCase();
        return sekolahs.stream().filter(t->t.getNamaSekolah().toLowerCase().contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void toeng(){
        //System.out.println("toeng");
        System.out.println(selectedSekolah.getIdSekolah());
    }

    public Sekolah getSelectedSekolah() {
        return selectedSekolah;
    }

    public void setSelectedSekolah(Sekolah selectedSekolah) {
        this.selectedSekolah = selectedSekolah;
    }
}
