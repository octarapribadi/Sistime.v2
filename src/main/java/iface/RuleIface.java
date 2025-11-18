package iface;

import java.util.Map;

public interface RuleIface {
    public String generateNilaiHuruf();
    public void setNilaiMap(Map<String, Object> nilaiMap);
    public void setBobotMap(Map<String,Object> bobotMap);
    public Double generateNilaiAngka();
}
