package entity;

import java.util.HashMap;
import java.util.Map;


public class KrsNilai {
    private KrsMahasiswa krsMahasiswa;
    private Map<String, Object> nilais;

    public KrsNilai(){
        nilais = new HashMap<>();
    }

    public KrsMahasiswa getKrsMahasiswa() {
        return krsMahasiswa;
    }

    public void setKrsMahasiswa(KrsMahasiswa krsMahasiswa) {
        this.krsMahasiswa = krsMahasiswa;
    }

    public Map<String, Object> getNilais() {
        return nilais;
    }

    public void setNilais(Map<String, Object> nilais) {
        this.nilais = nilais;
    }
}
