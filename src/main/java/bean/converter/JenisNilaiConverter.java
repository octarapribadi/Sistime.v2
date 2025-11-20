package bean.converter;

import repo.JenisNilaiManager;
import model.JenisNilai;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@Named
@FacesConverter("jenisNilaiConverter")
public class JenisNilaiConverter implements Converter<JenisNilai> {
    @EJB
    JenisNilaiManager jenisNilaiManager;
    @Override
    public JenisNilai getAsObject(FacesContext context, UIComponent component, String value) {
        if(value!=null && !value.trim().isEmpty()){
            return jenisNilaiManager.findJenisNilaiByJenis(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, JenisNilai value) {
        if(value!=null){
            return value.getJenis();
        }
        return null;
    }
}
