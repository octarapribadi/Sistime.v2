package bean;

import dto.SkemaKrsDto;
import entity.SkemaKrs;
import entity.TahunAjaran;
import repo.SkemaKrsManager;
import repo.TahunAjaranManajer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.TransactionScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SkemaKrsBean implements Serializable {
    @Inject
    SkemaKrsManager skemaKrsManager;

    @Inject
    TahunAjaranManajer tahunAjaranManajer;

    public List<SkemaKrsDto> findAll(){
        List<SkemaKrs> skemaKrss = skemaKrsManager.findAllSkemaKrs();
        List<SkemaKrsDto> skemaKrsDtos = new ArrayList<>();
        for (SkemaKrs sk : skemaKrss){
            SkemaKrsDto dto = new SkemaKrsDto();
            dto.setId(sk.getId());
            dto.setIdTahunAjaran(sk.getTahunAjaran().getId());
            dto.setAktif(sk.getAktif());
            dto.setKeterangan(sk.getKeterangan());
            skemaKrsDtos.add(dto);
        }
        return skemaKrsDtos;
    }

    @Transactional
    public void persist(SkemaKrsDto dto){
        if(tahunAjaranManajer.findTahunAjaranById(dto.getIdTahunAjaran())==null)
            throw new WebApplicationException("tahun ajaran tidak ditemukan", Response.Status.NOT_FOUND);
        SkemaKrs skemaKrs = new SkemaKrs();
        TahunAjaran tahunAjaran = new TahunAjaran();
        tahunAjaran.setId(dto.getIdTahunAjaran());
        skemaKrs.setTahunAjaran(tahunAjaran);
        skemaKrs.setAktif(dto.getAktif()!=null?dto.getAktif():true);
        skemaKrs.setKeterangan(dto.getKeterangan()!=null?dto.getKeterangan():null);
        skemaKrsManager.persist(skemaKrs);
        dto.setId(skemaKrs.getId());
    }
}
