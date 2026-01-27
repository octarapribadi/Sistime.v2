package bean;

import repo.KrsMahasiswaManager;
import repo.Nilai2Manager;
import entity.KrsMahasiswa;
import entity.Nilai2;
import dto.KhsDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class KhsMahasiswaBean {
    @Inject
    Nilai2Manager nilai2Manager;

    @Inject
    KrsMahasiswaManager krsMahasiswaManager;

    public List<KhsDto> getKhs(long userId) {
        List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(userId);
        List<Nilai2> nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
        List<KhsDto> khs = new ArrayList<>();
        krsMahasiswas.forEach(krs -> {
            KhsDto khsDTO = new KhsDto();
            khsDTO.setIdKrs(krs.getId());
            khsDTO.setNamaMatakuliah(krs.getSkedul().getIdMatakuliah().getNamaMatakuliah());
            khsDTO.setSemester(krs.getSkedul().getIdMatakuliah().getSemester());
            khsDTO.setSks(krs.getSkedul().getIdMatakuliah().getSks());
            khsDTO.setKodeMatakuliah(krs.getSkedul().getIdMatakuliah().getKodeMatakuliah());
            nilai2s.forEach(n -> {
                if (n.getKrsMahasiswa().getId().equals(krs.getId()))
                    khsDTO.getNilais().put(n.getJenisNilai().getJenis(), n.getNilai());
            });
            khs.add(khsDTO);
        });
        return khs;
    }

}
