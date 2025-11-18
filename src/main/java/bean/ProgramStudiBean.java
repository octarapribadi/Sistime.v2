package bean;


import ejb.ProgramStudiManager;
import model.ProgramStudi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ProgramStudiBean implements Serializable {
    @EJB
    ProgramStudiManager programStudiManager;
    ProgramStudi programStudi;
    List<ProgramStudi> programStudis;

    @PostConstruct
    public void init(){
        programStudis = new ArrayList<>();
    }

    public ProgramStudi getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(ProgramStudi programStudi) {
        this.programStudi = programStudi;
    }

    public List<ProgramStudi> getProgramStudis() {
        return programStudis;
    }

    public void setProgramStudis(List<ProgramStudi> programStudis) {
        this.programStudis = programStudis;
    }

    public List<ProgramStudi> listProgramStudi(){
        programStudis = programStudiManager.findAllProgramStudi();
        return programStudis;
    }


}
