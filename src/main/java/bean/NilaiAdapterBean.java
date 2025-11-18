package bean;

import ejb.SkemaKrsNilaiManager;
import iface.RuleIface;
import model.SkemaKrsNilai;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class NilaiAdapterBean implements Serializable {
    RuleIface nilaiRule;
    @EJB
    SkemaKrsNilaiManager skemaKrsNilaiManager;
    List<SkemaKrsNilai> skemaKrsNilais;
    Map<String, Object> bobotMap;
    @PostConstruct
    private void init(){
        skemaKrsNilais = skemaKrsNilaiManager.findAllSkemaKrsNilai();
        bobotMap = new HashMap<>();
    }

    public void setRule(RuleIface nilaiRule){
        this.nilaiRule = nilaiRule;
    }

    public String hitung(Map<String, Object> nilaiMap, Long skemaKrsId) throws Exception {
        if(nilaiRule!=null) {
            nilaiRule.setNilaiMap(nilaiMap);

            skemaKrsNilais.forEach(skn->{
                if(skn.getSkemaKrs().getId().equals(skemaKrsId))
                    skn.getSkemaNilai().getSkemaNilaiDetail().forEach(snd->{
                        bobotMap.put(snd.getJenisNilai().getJenis(),snd.getBobot().doubleValue());
                    });
            });

            nilaiRule.setBobotMap(bobotMap);
            return nilaiRule.generateNilaiHuruf();
        }
        else {
            throw new Exception("Rule tidak ditemukan");
        }
    }


}
