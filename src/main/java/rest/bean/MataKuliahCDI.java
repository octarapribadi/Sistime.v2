package rest.bean;

import repo.MataKuliahManager;
import model.MataKuliah;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class MataKuliahCDI implements Serializable {
    @EJB
    MataKuliahManager mataKuliahManager;

    public List<MataKuliah> getMataKuliah(){
        return mataKuliahManager.findAllMataKuliah();
    }
}
