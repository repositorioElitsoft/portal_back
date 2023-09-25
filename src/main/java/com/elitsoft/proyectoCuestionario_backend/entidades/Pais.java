
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ELITSOFT86
 */
@Entity
@Table(name = "TBL_PAIS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pais_id;
    
    @Column(name = "pais_nom")
    private String pais_nom;

    public Pais() {
    }

    public Long getPais_id() {
        return pais_id;
    }

    public void setPais_id(Long pais_id) {
        this.pais_id = pais_id;
    }



    public String getPais_nom() {
        return pais_nom;
    }

    public void setPais_nom(String pais_nom) {
        this.pais_nom = pais_nom;
    }
    
    
    
}
