
package com.sistema.PortalElitsoft.Entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Elitsoft83
 */
@Entity
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long USR_ROL_ID;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario tbl_usr;
    
    @ManyToOne
    private Rol rol;

    public Long getUSR_ROL_ID() {
        return USR_ROL_ID;
    }

    public void setUSR_ROL_ID(Long USR_ROL_ID) {
        this.USR_ROL_ID = USR_ROL_ID;
    }

    public Usuario getTbl_usr() {
        return tbl_usr;
    }

    public void setTbl_usr(Usuario tbl_usr) {
        this.tbl_usr = tbl_usr;
    }


    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public UsuarioRol(){
        
    }
    
}
