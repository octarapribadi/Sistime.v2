package bean;

import dto.TranskripDto;
import entity.KrsMahasiswa;
import entity.Mahasiswa;
import entity.Nilai2;
import repo.KrsMahasiswaManager;
import repo.MahasiswaManager;
import repo.Nilai2Manager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.*;

@RequestScoped
public class TranskripBean {
    @Inject
    MahasiswaManager mahasiswaManager;

    @Inject
    KrsMahasiswaManager krsMahasiswaManager;

    @Inject
    Nilai2Manager nilai2Manager;

    public List<TranskripDto> getTranskripByIdUser(long idUser){
        Mahasiswa selectedMahasiswa = mahasiswaManager.findMahasiswaByIdUser(idUser);
        if (selectedMahasiswa != null) {
            List<TranskripDto> transkrips = new ArrayList<>();
            List<KrsMahasiswa> krsMahasiswas = krsMahasiswaManager.findKrsMahasiswaByUserId(selectedMahasiswa.getUser().getId());
            List<Nilai2> nilai2s = nilai2Manager.findAllNilaiByKrsMahasiswas(krsMahasiswas);

            Map<Long, List<Nilai2>> nilaiMap = new HashMap<>();
            for(Nilai2 n: nilai2s){
                nilaiMap.computeIfAbsent(n.getKrsMahasiswa().getId(), key->new ArrayList<>()).add(n);
            }

            for (KrsMahasiswa krs : krsMahasiswas) {
                TranskripDto dto = new TranskripDto();
                dto.setKrsId(krs.getId());
                dto.setKodeMatkul(krs.getSkedul().getIdMatakuliah().getKodeMatakuliah());
                dto.setMatkul(krs.getSkedul().getIdMatakuliah().getNamaMatakuliah());
                dto.setSks(krs.getSkedul().getIdMatakuliah().getSks());
//                for (Nilai2 n : nilai2s) {
//                    if (Objects.equals(n.getKrsMahasiswa().getId(), krs.getId())) {
//                        transkrip.getNilais().put(n.getJenisNilai().getJenis(), n.getNilai());
//                    }
//                }
                List<Nilai2> nilaiKrs = nilaiMap.getOrDefault(krs.getId(), Collections.emptyList());
                for(Nilai2 n : nilaiKrs){
                    dto.getNilais().put(n.getJenisNilai().getJenis(), n.getNilai());
                }

                transkrips.add(dto);
            }
            return transkrips;
        }
        else
            return Collections.emptyList();
    }

}
