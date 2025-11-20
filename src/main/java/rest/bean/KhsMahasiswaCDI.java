package rest.bean;

import repo.KrsMahasiswaManager;
import repo.Nilai2Manager;
import model.KrsMahasiswa;
import model.Nilai2;
import rest.model.KhsDTO;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class KhsMahasiswaCDI {
    @EJB
    Nilai2Manager nilai2Manager;

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    public List<KhsDTO> getKhs(long userId) {
        List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(userId);
        List<Nilai2> nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
        List<KhsDTO> khs = new ArrayList<>();
        krsMahasiswas.forEach(krs -> {
            KhsDTO khsDTO = new KhsDTO();
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
