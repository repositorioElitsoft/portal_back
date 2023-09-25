
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
@Table(name = "TBL_CAT_PROD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Categoria_Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_prod_id;
    
    private String cat_prod_nom;

    public Categoria_Producto() {
    }

    public Long getCat_prod_id() {
        return cat_prod_id;
    }

    public void setCat_prod_id(Long cat_prod_id) {
        this.cat_prod_id = cat_prod_id;
    }

    public String getCat_prod_nom() {
        return cat_prod_nom;
    }

    public void setCat_prod_nom(String cat_prod_nom) {
        this.cat_prod_nom = cat_prod_nom;
    }
    
    
    
}
