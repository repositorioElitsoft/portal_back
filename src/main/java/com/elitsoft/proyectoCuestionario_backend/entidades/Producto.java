
package com.elitsoft.proyectoCuestionario_backend.entidades;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */

@Entity
@Table(name = "TBL_PRD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prd_id;
    
    private String prd_nom;
    
    // Clave foránea hacia la tabla de Categorias
    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_prod_id") // Nombre de la columna que será clave foránea hacia la tabla de cat_prod
    private ProductCategory cat_prod_id;

    public Producto() {
    }

    public Long getPrd_id() {
        return prd_id;
    }

    public void setPrd_id(Long prd_id) {
        this.prd_id = prd_id;
    }

    public String getPrd_nom() {
        return prd_nom;
    }

    public void setPrd_nom(String prd_nom) {
        this.prd_nom = prd_nom;
    }

    public ProductCategory getCat_prod_id() {
        return cat_prod_id;
    }

    public void setCat_prod_id(ProductCategory cat_prod_id) {
        this.cat_prod_id = cat_prod_id;
    }

    
    
    
}
