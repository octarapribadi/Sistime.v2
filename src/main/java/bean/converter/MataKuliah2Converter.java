package bean.converter;

import repo.MataKuliahManager;
import model.MataKuliah;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter(value="mataKuliah2Converter")
public class MataKuliah2Converter implements Converter<MataKuliah> {

    @EJB
    MataKuliahManager mataKuliahManager;

    @Override
    public MataKuliah getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s!=null && !s.isEmpty()){
            return mataKuliahManager.findMataKuliahByName(s);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, MataKuliah mataKuliah) {
        return mataKuliah.getNamaMatakuliah();
    }
}
