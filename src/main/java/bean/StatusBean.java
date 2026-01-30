package bean;

import dto.StatusDto;
import entity.Status;
import repo.StatusManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StatusBean implements Serializable {
    @Inject
    StatusManager statusManager;

    public List<StatusDto> getStatus(){
        List<Status> status = statusManager.findAllStatus();
        List<StatusDto> dtos = new ArrayList<>();
        status.forEach(s->{
            StatusDto dto = new StatusDto();
            dto.setStatus(s.getStatus());
            dto.setIdStatus(s.getIdStatus());
            dtos.add(dto);
        });
        return dtos;
    }
}
