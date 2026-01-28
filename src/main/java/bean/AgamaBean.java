package bean;

import dto.AgamaDto;
import entity.Agama;
import repo.AgamaManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgamaBean implements Serializable {
    @Inject
    AgamaManager agamaManager;

    public List<AgamaDto> getAgama() {
        List<Agama> agamas = agamaManager.findAllAgama();
        List<AgamaDto> dtos = new ArrayList<>();
        agamas.forEach(a -> {
            AgamaDto dto = new AgamaDto();
            dto.setAgama(a.getAgama());
            dto.setIdAgama(a.getIdAgama());
            dtos.add(dto);
        });
        return dtos;
    }
    public  boolean isAgamaExist(String idAgama){
        return agamaManager.findAgamaById(idAgama)!=null;
    }
}
