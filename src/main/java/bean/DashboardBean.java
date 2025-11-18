package bean;

import org.primefaces.model.dashboard.DashboardModel;
import org.primefaces.model.dashboard.DefaultDashboardModel;
import org.primefaces.model.dashboard.DefaultDashboardWidget;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class DashboardBean implements Serializable {
    @PostConstruct
    public void init(){
        responsiveModel = new DefaultDashboardModel();
    }

    public void create(){
        responsiveModel.addWidget(new DefaultDashboardWidget("panel_jumlahmahasiswaperprodi","col-6,col-6"));
        responsiveModel.addWidget(new DefaultDashboardWidget("panel_jumlahmahasiswaperangkatan","col-6,col-6"));

        responsiveModel.addWidget(new DefaultDashboardWidget("panel_jumlahmahasiswabystatuswithperangkatan","col-6,col-6"));
    }

    private DashboardModel responsiveModel;

    public DashboardModel getResponsiveModel() {
        return responsiveModel;
    }

    public void setResponsiveModel(DashboardModel responsiveModel) {
        this.responsiveModel = responsiveModel;
    }
}
