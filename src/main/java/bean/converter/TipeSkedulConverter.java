package bean.converter;

import model.KrsMahasiswa;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TipeSkedulConverter implements Converter<Integer> {

    @Override
    public Integer getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s.equals("WAJIB"))
            return 0;
        else if(s.equals("BAWAH"))
            return 1;
        else
            return 2;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Integer integer) {
        if(integer==0)
            return "WAJIB";
        else if(integer==1)
            return "BAWAH";
        else
            return "PILIHAN";
    }
}
