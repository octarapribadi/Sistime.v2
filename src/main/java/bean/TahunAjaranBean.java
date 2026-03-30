package bean;

import entity.TahunAjaran;
import repo.TahunAjaranManajer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TahunAjaranBean {
    @Inject
    TahunAjaranManajer tahunAjaranManajer;

    public void persist(TahunAjaran tahunAjaran){

    }
}
