package business;

import repo.MataKuliahManager;
import entity.MataKuliah;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@RequestScoped
public class MataKuliahCDI implements Serializable {
    @Inject
    MataKuliahManager mataKuliahManager;

    public List<MataKuliah> getMataKuliah(){
        return mataKuliahManager.findAllMataKuliah();
    }
}
