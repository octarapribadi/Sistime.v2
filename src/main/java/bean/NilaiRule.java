package bean;

import iface.RuleIface;

import java.util.Map;

public class NilaiRule implements RuleIface {
    Map<String, Object>bobotMap;
    Map<String, Object>nilaiMap;
    public void setBobotMap(Map<String,Object>bobotMap){
        this.bobotMap = bobotMap;
    }

    @Override
    public String generateNilaiHuruf() {
        Double avg = generateNilaiAngka();
        String huruf="";
        if(avg==0)
            huruf="";
        else if(avg>=90)
            huruf = "A";
        else if(avg>=75)
            huruf = "B";
        else if(avg>=60)
            huruf = "C";
        else if(avg>=50)
            huruf = "D";
        else huruf = "E";

        return huruf;
    }

    @Override
    public void setNilaiMap(Map<String, Object> nilaiMap) {
        this.nilaiMap = nilaiMap;
    }

    @Override
    public Double generateNilaiAngka() {
        if (!nilaiMap.isEmpty()) {
            Double avg = 0.0;
            for(Map.Entry<String, Object>n: bobotMap.entrySet()){
                if(!n.getKey().equals("perbaikan"))
                    avg += (Double)n.getValue() * (Double)nilaiMap.getOrDefault(n.getKey(),.0);
            }
            double nilaiPerbaikan = (Double)nilaiMap.getOrDefault("perbaikan",.0) * (Double)bobotMap.get("perbaikan");
            avg = nilaiPerbaikan>avg? nilaiPerbaikan :avg;
            return avg;
        }
        else
            return 0.0;
    }


}
