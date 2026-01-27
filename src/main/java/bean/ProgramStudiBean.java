package bean;

import dto.ProgramStudiDto;
import entity.ProgramStudi;
import repo.ProgramStudiManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProgramStudiBean implements Serializable {
    @Inject
    ProgramStudiManager programStudiManager;

    public List<ProgramStudiDto> getProgramStudi(){
        List<ProgramStudi> programStudis = programStudiManager.findAllProgramStudi();
        List<ProgramStudiDto> dtos = new ArrayList<>();
        programStudis.forEach(p->{
            ProgramStudiDto dto = new ProgramStudiDto();
            dto.setKodeProgramstudi(p.getKodeProgramstudi());
            dto.setNamaProgramstudi(p.getNamaProgramstudi());
            dto.setKeterangan(p.getKeterangan());
            dtos.add(dto);
        });
        return dtos;
    }
}
