package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Maeva Martínez 
 */
@Entity
@Table(name = "TBL_HERR_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Herramienta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long herr_usr_id;
    
    private String herr_usr_anos_exp;
    private String herr_usr_vrs;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prd_id", referencedColumnName = "prd_id") // Nombre de la columna que será clave foránea para la tabla de Producto
    private Producto producto;

    private Long cert_id;
    
    private Long nvl_id;
    

    public Herramienta() {
    }

    public Long getHerr_usr_id() {
        return herr_usr_id;
    }

    public void setHerr_usr_id(Long herr_usr_id) {
        this.herr_usr_id = herr_usr_id;
    }

    public String getHerr_usr_anos_exp() {
        return herr_usr_anos_exp;
    }

    public void setHerr_usr_anos_exp(String herr_usr_anos_exp) {
        this.herr_usr_anos_exp = herr_usr_anos_exp;
    }

    public String getHerr_usr_vrs() {
        return herr_usr_vrs;
    }

    public void setHerr_usr_vrs(String herr_usr_vrs) {
        this.herr_usr_vrs = herr_usr_vrs;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public Long getCert_id() {
        return cert_id;
    }

    public void setCert_id(Long cert_id) {
        this.cert_id = cert_id;
    }

    public Long getNvl_id() {
        return nvl_id;
    }

    public void setNvl_id(Long nvl_id) {
        this.nvl_id = nvl_id;
    }

}
