
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
@Table(name = "TBL_CRG_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CargoUsuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crg_usr_id;
    
    private String crg_usr_pret;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;
    
    private Long crg_elit_id;

    public CargoUsuario() {
    }

    public Long getCrg_usr_id() {
        return crg_usr_id;
    }

    public void setCrg_usr_id(Long crg_usr_id) {
        this.crg_usr_id = crg_usr_id;
    }

    public String getCrg_usr_pret() {
        return crg_usr_pret;
    }

    public void setCrg_usr_pret(String crg_usr_pret) {
        this.crg_usr_pret = crg_usr_pret;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getCrg_elit_id() {
        return crg_elit_id;
    }

    public void setCrg_elit_id(Long crg_elit_id) {
        this.crg_elit_id = crg_elit_id;
    }
    
    
    
    
    
}
