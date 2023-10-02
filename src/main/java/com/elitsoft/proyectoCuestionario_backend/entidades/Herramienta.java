package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Maeva Martínez 
 */
@Entity
@Table(name = "TBL_HERR_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Herramienta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long herr_usr_id;
    
    private String herr_usr_anos_exp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;
   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vrs_prd_id", referencedColumnName = "vrs_id") // Nombre de la columna que será clave foránea para la tabla de Producto
    private VersionProducto versionProducto;

    private Boolean herr_is_cert;

    @Pattern(regexp = "\\b(?:alto|medio|bajo)\\b",
            message = "Error valores solamente pueden ser alto, medio, or bajo.")
    private String herr_nvl;


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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }





}
