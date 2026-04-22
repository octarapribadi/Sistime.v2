package bean;

import entity.Dosen;
import repo.DosenManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DosenBean {
    @Inject
    DosenManager dosenManager;

    public List<Dosen> getDosen(){
        return dosenManager.findAllDosen();
    }
}
