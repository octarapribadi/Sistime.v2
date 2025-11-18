package bean;

import ejb.DosenManager;
import model.Dosen;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.ColumnToggleEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class DosenBean implements Serializable {
    Dosen selectedDosen;
    List<Dosen> dosens;
    String status = "";
    DataTable dtDosen;
    Map<String,Boolean> visible;
    @EJB
    DosenManager dosenManager;

    @PostConstruct
    public void init() {
        dosens = new ArrayList<>();
        visible = new HashMap<>();
    }

    public void initColumnVisibleList(){
        dtDosen = (DataTable) FacesContext.getCurrentInstance()
                .getViewRoot().findComponent("form_dosen:dt_dosen");
        for(UIColumn c: dtDosen.getColumns()){
            visible.put(c.getColumnKey(),c.isVisible());
        }
    }

    public void onToggle(ColumnToggleEvent e) {
        try{
            System.out.println(e.getColumn().getColumnKey());
            visible.replace(e.getColumn().getColumnKey(), e.getVisibility()== Visibility.VISIBLE);
            //System.out.println(visible.get(e.getColumn().getColumnKey()));
            //visible.replace(e.getColumn().getColumnKey(),e.getColumn().isVisible());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Dosen> findAllDosen() {
        dosens = dosenManager.findAllDosen();
        return dosens;
    }

    public List<Dosen> completeDosen(String query) {
        String lowerQuery = query.toLowerCase();
        return dosens.stream().filter(t -> t.getNamaDosen().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }


    public Dosen getSelectedDosen() {
        return selectedDosen;
    }

    public void setSelectedDosen(Dosen selectedDosen) {
        this.selectedDosen = selectedDosen;
    }

    public List<Dosen> getDosens() {
        return dosens;
    }

    public void setDosens(List<Dosen> dosens) {
        this.dosens = dosens;
    }

    public void openNew() {
        status = "new";
        selectedDosen = new Dosen();
    }

    public void delete() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if (selectedDosen != null) {
                dosenManager.remove(selectedDosen);
                dosens.remove(selectedDosen);
                selectedDosen = null;
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil dihapus!", ""));
            }
        } catch (PersistenceException ex) {
            System.err.println(ex.getMessage());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal dihapus", ex.getMessage()));
        }
    }

    public void edit() {
        status = "edit";
    }

    public void save() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            if (status.equals("new")) {
                dosenManager.persist(selectedDosen);
                dosens.add(selectedDosen);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil ditambahkan", ""));
            } else if (status.equals("edit")) {
                dosenManager.merge(selectedDosen);
                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data berhasil diubah", ""));
            }
        } catch (PersistenceException ex) {
            System.err.println(ex.getMessage());
            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data gagal ditambahkan", ex.getMessage()));
        } finally {
            status = "";
        }
    }

    public Map<String, Boolean> getVisible() {
        return visible;
    }

    public void setVisible(Map<String, Boolean> visible) {
        this.visible = visible;
    }
}
