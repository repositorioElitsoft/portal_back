
package com.sistema.PortalElitsoft.Entidades;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Noelid
 */
@Entity
@Table(name = "tbl_rol")
public class Rol {
    
    @Id
    @Column(name = "rol_id")
    private Long rol_id; //rol ID
    @Column(name = "rol_nom")
    private String rol_nom; //nombre del rol
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    
     public Rol(){
        
    }

    public Long getRol_id() {
        return rol_id;
    }

    public void setRol_id(Long rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_nom() {
        return rol_nom;
    }

    public void setRol_nom(String rol_nom) {
        this.rol_nom = rol_nom;
    }
     


    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
    
   
    
    
}
