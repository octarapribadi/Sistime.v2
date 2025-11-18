package bean;

import ejb.SekolahManager;
import model.Sekolah;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class AutocompleteBean implements Serializable {

    String _idSekolah;

    public String get_idSekolah() {
        return _idSekolah;
    }

    public void set_idSekolah(String _idSekolah) {
        this._idSekolah = _idSekolah;
    }

    Sekolah sekolah;

    public Sekolah getSekolah() {
        return sekolah;
    }

    public void setSekolah(Sekolah sekolah) {
        this.sekolah = sekolah;
    }

    @EJB
    SekolahManager sekolahManager;

    public List<Sekolah> getAllSekolah(String query) {
        String queryLowerCase = query.toLowerCase();
        List<Sekolah> sekolahs = sekolahManager.findAll();
        return sekolahs.stream().filter(t->t.getNamaSekolah().toLowerCase()
                .contains(queryLowerCase)).collect(Collectors.toList());
    }

    public void action(){
        if(_idSekolah!=null){
            sekolah = sekolahManager.findById(Integer.parseInt(_idSekolah));
            System.out.println(sekolah.getNamaSekolah());
            System.out.println(sekolah.getIdSekolah());
        }
        else{
            System.out.println("id sekolah tidak ditemukan!!");
        }



    }

}
