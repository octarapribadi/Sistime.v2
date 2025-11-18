package bean;

import ejb.DosenManager;
import ejb.KelasManager;
import model.Dosen;
import model.Kelas;
import model.ProgramStudi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class KelasBean implements Serializable {
    Kelas selectedKelas;
    List<Kelas> kelass;
    List<Dosen> dosens;

    List<Kelas> filteredKelas;
    String status = "";
    @EJB
    KelasManager kelasManager;

    @EJB
    DosenManager dosenManager;

    @PostConstruct
    public void init() {
        selectedKelas = new Kelas();
        selectedKelas.setIdDosenwali(new Dosen());
        selectedKelas.setProgramStudi(new ProgramStudi());

        kelass = new ArrayList<>();
        filteredKelas = new ArrayList<>();
        dosens = dosenManager.findAllDosen();
    }

    public List<Kelas> findAllKelas() {
        kelass = kelasManager.findAllKelas();
        return kelass;
    }

    public void openNew() {
        status = "new";
        selectedKelas = new Kelas();
        selectedKelas.setIdDosenwali(new Dosen());
        selectedKelas.setProgramStudi(new ProgramStudi());
    }

    public void edit() {
        status = "edit";
        if (selectedKelas.getIdDosenwali() == null) {
            selectedKelas.setIdDosenwali(new Dosen());
        }
    }

    public void delete() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if (selectedKelas != null) {
                //if (selectedKelas.getIdDosenwali().getId().equals("")) selectedKelas.setIdDosenwali(null);
                kelasManager.remove(selectedKelas);
                kelass.remove(selectedKelas);
                selectedKelas = null;
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "data berhasil dihapus!", ""));
            }
        } catch (PersistenceException ex) {
            //System.err.println(ex.getMessage());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal dihapus", ex.getMessage()));
        }
    }

    public Kelas getSelectedKelas() {
        return selectedKelas;
    }

    public void setSelectedKelas(Kelas selectedKelas) {
        this.selectedKelas = selectedKelas;
    }

    public List<Kelas> getKelass() {
        return kelass;
    }

    public void setKelass(List<Kelas> kelass) {
        this.kelass = kelass;
    }

    public List<Dosen> getDosens() {
        return dosens;
    }

    public void setDosens(List<Dosen> dosens) {
        this.dosens = dosens;
    }

    public void save() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if (status.equals("new")) {
                if (selectedKelas.getIdDosenwali().getId() == null)
                    selectedKelas.setIdDosenwali(null);
                kelasManager.persist(selectedKelas);
                kelass.add(selectedKelas);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil ditambahkan", ""));
            } else if (status.equals("edit")) {
                if (selectedKelas.getIdDosenwali().getId() == null) {
                    selectedKelas.setIdDosenwali(null);
                }
                kelasManager.merge(selectedKelas);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil diubah", ""));
            }
        } catch (Exception ex) {
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal ditambahkan", ex.getMessage()));
        } finally {
            status = "";
        }

    }

    public List<Dosen> completeDosen(String query) {
        String lowerQuery = query.toLowerCase();
        //List<Dosen> dosens = dosenManager.findAllDosen();
        return dosens.stream().filter(t -> t.getNamaDosen().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }


    public void tambahkanNamaDosenPadaSelectedKelas() {
        for (Dosen d : dosens) {
            if (d.getId().equals(selectedKelas.getIdDosenwali().getId())) {
                //selectedKelas.getIdDosenwali().setNamaDosen(d.getNamaDosen());
                selectedKelas.setIdDosenwali(d);
                break;
            }
        }
    }

    public List<Kelas> completeKelas(String query) {
        String lowerQuery = query.toLowerCase();
        return kelass.stream().filter(t -> t.getNamaKelas().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public List<Kelas> getFilteredKelas() {
        return filteredKelas;
    }

    public void setFilteredKelas(List<Kelas> filteredKelas) {
        this.filteredKelas = filteredKelas;
    }
}
