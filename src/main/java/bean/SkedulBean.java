package bean;

import dto.SkedulDto;
import entity.*;
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
    public void remove(Long idSkedul){
        Skedul skedul = skedulManager.findSkedulById(idSkedul);
        if(skedul!=null)
            skedulManager.remove(skedul);
        else
            throw new WebApplicationException("Skedul tidak ditemukan", Response.Status.NOT_FOUND);
    }

    @Transactional
    public void persist(List<SkedulDto> dtos){
        if(dtos==null)
            throw new WebApplicationException("Skedul tidak ada", Response.Status.BAD_REQUEST);
        List<Skedul>skeduls = new ArrayList<>();
        dtos.forEach(dto->{
            Skedul skedul = new Skedul();

            Dosen dosen = new Dosen();
            dosen.setId(dto.getIdDosen());

            Kelas kelas = new Kelas();
            kelas.setKodeKelas(dto.getKodeKelas());

            MataKuliah matkul = new MataKuliah();
            matkul.setId(dto.getIdMataKuliah());

            SkemaKrs skemaKrs = new SkemaKrs();
            skemaKrs.setId(dto.getIdSkemaKrs());

            skedul.setIdDosen(dosen);
            skedul.setKodeKelas(kelas);
            skedul.setIdMatakuliah(matkul);
            skedul.setIdSkemakrs(skemaKrs);
            skedul.setKeterangan(dto.getKeterangan());

            skeduls.add(skedul);
        });

        skeduls.forEach(skedul->{
            skedulManager.persist(skedul);
        });
    }
}
