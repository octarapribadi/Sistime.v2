package bean;

import dto.KampusDto;
import entity.Kampus;
import repo.KampusManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class KampusBean implements Serializable {
    @Inject
    KampusManager kampusManager;
    public List<KampusDto> getKampus(){
        List<Kampus>kampuss = kampusManager.findAllKampus();
        List<KampusDto>dto = new ArrayList<>();
        kampuss.forEach(k->{
            KampusDto kampus = new KampusDto();
            kampus.setKodeKampus(k.getKodeKampus());
            kampus.setKeterangan(k.getKeterangan());
            dto.add(kampus);
        });
        return dto;
    }
}
