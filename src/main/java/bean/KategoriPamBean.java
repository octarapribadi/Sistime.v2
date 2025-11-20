package bean;

import repo.KategoriPamManager;
import model.KategoriPam;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class KategoriPamBean implements Serializable {
    List<KategoriPam> kategoriPams;
    List<SelectItemGroup> kategoris;

    @PostConstruct
    private void init() {
        kategoris = new ArrayList<>();
        kategoriPams = new ArrayList<>();
    }

    @EJB
    KategoriPamManager kategoriPamManager;

    public List<KategoriPam> findAllKategoriPam() {
        kategoriPams = kategoriPamManager.findAllKategoriPam();
        return kategoriPams;
    }

    public List<SelectItemGroup> findAllKategoriPamAndGroupByKelompok() {
        List<KategoriPam> kategoriPamList = kategoriPamManager.findAllKategoriPam();
        List<String> kelompok = kategoriPamManager.findAllKelompokKategoriPam();
        SelectItemGroup sig;
        List<SelectItem> selectItemList;
        for (String s : kelompok) {
            sig = new SelectItemGroup(s);
            selectItemList = new ArrayList<>();
            for (KategoriPam kategoriPam : kategoriPamList) {
                if (kategoriPam.getKelompok().equals(s)) {
                    selectItemList.add(new SelectItem(kategoriPam.getId(),
                            kategoriPam.getKategori(),
                            kategoriPam.getPoin().toString()));
                }
            }
            SelectItem[] selectItemArrs = selectItemList.toArray(new SelectItem[0]);
            sig.setSelectItems(selectItemArrs);
            kategoris.add(sig);
        }
        return kategoris;
    }

    public int findPoinByIdKategori(Long id) {
        for (KategoriPam pam : kategoriPams) {
            if (pam.getId() == id) {
                return pam.getPoin();
            }
        }
        return -1;
    }

    public List<KategoriPam> getKategoriPams() {
        return kategoriPams;
    }

    public void setKategoriPams(List<KategoriPam> kategoriPams) {
        this.kategoriPams = kategoriPams;
    }

    public List<SelectItemGroup> getKategoris() {
        return kategoris;
    }

    public void setKategoris(List<SelectItemGroup> kategoris) {
        this.kategoris = kategoris;
    }
}
