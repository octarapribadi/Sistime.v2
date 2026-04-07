package bean;

import dto.SkedulDto;
import entity.Skedul;
import repo.SkedulManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SkedulBean implements Serializable {
    @Inject
    SkedulManager skedulManager;

    public List<SkedulDto>findSkedulByIdSkemaKrs(Long idSkemaKrs){
        List<Skedul> skeduls = skedulManager.findSkedulByIdSkemaKrs(idSkemaKrs);
        List<SkedulDto> skedulDtos = new ArrayList<>();
        skeduls.forEach(s->{
            SkedulDto dto = new SkedulDto();
            dto.setId(s.getId());
            dto.setIdSkemaKrs(s.getIdSkemakrs().getId());
            dto.setIdDosen(s.getIdDosen().getId());
            dto.setIdMataKuliah(s.getIdMatakuliah().getId());
            dto.setKeterangan(s.getKeterangan());
            dto.setKodeKelas(s.getKodeKelas().getKodeKelas());
            skedulDtos.add(dto);
        });
        return skedulDtos;
    }
    @Transactional
    public void removeSkedul(Long idSkedul){
        Skedul skedul = skedulManager.findSkedulById(idSkedul);
        if(skedul!=null)
            skedulManager.remove(skedul);
        else
            throw new WebApplicationException("Skedul tidak ditemukan", Response.Status.NOT_FOUND);
    }
}
