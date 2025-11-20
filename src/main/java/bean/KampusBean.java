package bean;

import repo.KampusManager;
import model.Kampus;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class KampusBean implements Serializable {

    @EJB
    KampusManager kampusManager;

    public List<Kampus> findAllKampus(){
        return kampusManager.findAllKampus();
    }

}
