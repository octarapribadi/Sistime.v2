package rest.bean;

import ejb.KrsMahasiswaManager;
import ejb.Nilai2Manager;
import model.KrsMahasiswa;
import model.Nilai2;
import rest.model.KhsDTO;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Named
public class KhsMahasiswaCDI {
    @EJB
    Nilai2Manager nilai2Manager;

    @EJB
    KrsMahasiswaManager krsMahasiswaManager;

    public List<KhsDTO> getKhs(long userId){
        List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(userId);
        List<Nilai2>nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);
        List<KhsDTO> khs = new ArrayList<>();
        krsMahasiswas.forEach(krs->{
            KhsDTO khsDTO = new KhsDTO();
            khsDTO.setId(krs.getId());
            khsDTO.setKrsMahasiswa(krs);
            khsDTO.setMataKuliah(krs.getSkedul().getIdMatakuliah());
            nilai2s.forEach(n->{
                if(n.getKrsMahasiswa().getId().equals(krs.getId())){
                    khsDTO.setNilai(n);

                }
            });
            khs.add(khsDTO);
        });
        return khs;
    }

}
