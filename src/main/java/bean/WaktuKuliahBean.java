package bean;

import dto.WaktuKuliahDto;
import entity.WaktuKuliah;
import repo.WaktuKuliahManager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class WaktuKuliahBean {
    @Inject
    WaktuKuliahManager waktuKuliahManager;

    public List<WaktuKuliahDto> getWaktuKuliah(){
        List<WaktuKuliahDto> dtos = new ArrayList<>();
        List<WaktuKuliah> waktuKuliahs = waktuKuliahManager.findAllWaktuKuliah();
        waktuKuliahs.forEach(wk->{
            WaktuKuliahDto dto = new WaktuKuliahDto();
            dto.setIdWaktukuliah(wk.getIdWaktukuliah());
            dto.setKodeKampus(wk.getKampus().getKodeKampus());
            dto.setWaktuKuliah(wk.getWaktuKuliah());
            dto.setKeterangan(wk.getKeterangan());
            dtos.add(dto);
        });
        return dtos;
    }
}
