package bean;

import repo.MataKuliahManager;
import repo.SkedulManager;
import model.MataKuliah;
import model.ProgramStudi;
import model.Skedul;
import model.TipeMataKuliah;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class MataKuliahBean implements Serializable {
    MataKuliah selectedMataKuliah;
    List<MataKuliah> mataKuliahs;
    String status="";
    @EJB
    MataKuliahManager mataKuliahManager;

    @EJB
    SkedulManager skedulManager;

    @PostConstruct
    public void init() {
        selectedMataKuliah = new MataKuliah();
        //mataKuliahs = mataKuliahManager.findAllMataKuliah();
        mataKuliahs = new ArrayList<>();
    }

    public List<MataKuliah> findAllMataKuliah(){
        mataKuliahs = mataKuliahManager.findAllMataKuliah();
        return mataKuliahs;
    }

    public void fillMatakuliahByKodeKelas(String kodeKelas){
        if(kodeKelas!=null && !kodeKelas.isEmpty()){
            mataKuliahs = new ArrayList<>();
            List<Skedul> skeduls = skedulManager.findAllSkedulByKodeKelas(kodeKelas);
            skeduls.forEach(s->{
                mataKuliahs.add(s.getIdMatakuliah());
            });
        }
    }

    public void openNew(){
        status="new";
        selectedMataKuliah = new MataKuliah();
        selectedMataKuliah.setIdTipematakuliah(new TipeMataKuliah());
        selectedMataKuliah.setKodeProdi(new ProgramStudi());
    }

    public void edit(){
        status="edit";
    }

    public void delete(){
        try{
            if(selectedMataKuliah!=null){
                mataKuliahManager.remove(selectedMataKuliah);
                mataKuliahs.remove(selectedMataKuliah);
                selectedMataKuliah = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data berhasil dihapus!",""));
            }
        }
        catch(PersistenceException ex){
            System.err.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal dihapus", ex.getMessage()));
        }
    }

    public void save(){
        try{
            if(status.equals("new")){
                mataKuliahManager.persist(selectedMataKuliah);
                mataKuliahs.add(selectedMataKuliah);
                status="";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil ditambahkan",""));
            }
            else if(status.equals("edit")){
                mataKuliahManager.merge(selectedMataKuliah);
                //sisipkan namaTipeMatakuliah agar ketika update dt_matakuliah muncul namaTipeMataKuliah
                //tambahkanNamaTipeMatakuliah();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil diubah",""));
            }
        }
        catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal ditambahkan", ex.getMessage()));
        }
    }

    public MataKuliah getSelectedMataKuliah() {
        return selectedMataKuliah;
    }

    public void setSelectedMataKuliah(MataKuliah selectedMataKuliah) {
        this.selectedMataKuliah = selectedMataKuliah;
    }

    public List<MataKuliah> getMataKuliahs() {
        return mataKuliahs;
    }

    public void setMataKuliahs(List<MataKuliah> mataKuliahs) {
        this.mataKuliahs = mataKuliahs;
    }

//    private void tambahkanNamaTipeMatakuliah(){
//        for(TipeMataKuliah t:tipeMataKuliahs){
//            if(t.getId().equals(selectedMataKuliah.getIdTipematakuliah().getId())){
//                selectedMataKuliah.getIdTipematakuliah().setTipeMatakuliah(t.getTipeMatakuliah());
//                break;
//            }
//        }
//    }

}