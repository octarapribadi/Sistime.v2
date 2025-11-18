package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_jk"
        , catalog = "db_sistime"
)
public class Jk implements java.io.Serializable {


    private String idJk;
    private String jk;

    public Jk() {
    }


    public Jk(String idJk) {
        this.idJk = idJk;
    }

    public Jk(String idJk, String jk) {
        this.idJk = idJk;
        this.jk = jk;
    }

    @Id


    @Column(name = "id_jk", unique = true, nullable = false)
    public String getIdJk() {
        return this.idJk;
    }

    public void setIdJk(String idJk) {
        this.idJk = idJk;
    }


    @Column(name = "jk")
    public String getJk() {
        return this.jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }


}


