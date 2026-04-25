package bean;

import entity.StatusMahasiswa;
import repo.StatusMahasiswaManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StatusMahasiswaBean {

    @Inject
    StatusMahasiswaManager statusMahasiswaManager;

    public StatusMahasiswa getStatusMahasiswaByIdUser(Long idUser){
        return statusMahasiswaManager.findStatusMahasiswaByIdUser(idUser);
    }
}
