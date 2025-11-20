package bean;

import repo.AgamaManager;
import model.Agama;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AgamaBean implements Serializable {

    @EJB
    AgamaManager agamaManager;

    List<Agama> agamas;

    @PostConstruct
    public void init(){
        agamas = new ArrayList<>();
    }

    public void findAllAgama(){
        agamas = agamaManager.findAllAgama();
    }

    public List<Agama> listAgama(){
        return agamaManager.findAllAgama();
    }

    public List<Agama> getAgamas() {
        return agamas;
    }

    public void setAgamas(List<Agama> agamas) {
        this.agamas = agamas;
    }
}
