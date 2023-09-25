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
@Table(name = "TBL_CRG_ELIT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CargoElitsoft {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crg_elit_id;
    
    private String crg_elit_nom;

    public CargoElitsoft() {
    }

    public Long getCrg_elit_id() {
        return crg_elit_id;
    }

    public void setCrg_elit_id(Long crg_elit_id) {
        this.crg_elit_id = crg_elit_id;
    }

    public String getCrg_elit_nom() {
        return crg_elit_nom;
    }

    public void setCrg_elit_nom(String crg_elit_nom) {
        this.crg_elit_nom = crg_elit_nom;
    }
    
    
    
    
    
    
}
