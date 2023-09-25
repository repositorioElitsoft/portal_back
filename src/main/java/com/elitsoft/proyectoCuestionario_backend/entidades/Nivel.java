
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Maeva Martínez
 */

@Entity
@Table(name = "TBL_NVL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Nivel {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long nvl_id;
   
   private String nvl;

    public Nivel() {
    }

    public Long getNvl_id() {
        return nvl_id;
    }

    public void setNvl_id(Long nvl_id) {
        this.nvl_id = nvl_id;
    }

    public String getNvl() {
        return nvl;
    }

    public void setNvl(String nvl) {
        this.nvl = nvl;
    }
   
   
}
