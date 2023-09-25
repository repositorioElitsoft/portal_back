
package com.sistema.PortalElitsoft.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Noelid Chávez
 */

@Entity
@Table(name = "tbl_usr")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long usr_id; //usuario ID
    @Column(name = "usr_rut")
    private String usr_rut; //RUT del usuario
    @Column(name = "usr_nom")
    private String usr_nom; //Nombres del usuario
    @Column(name = "usr_ap_pat")
    private String usr_ap_pat; //apellido paterno del usuario
    @Column(name = "usr_ap_mat")
    private String usr_ap_mat; //apellido materno del usuario
    
    @Column(name = "usr_email")
    private String email; //correo de registro del usuario
    @Column(name = "usr_pass")
    private String usr_pass; //pass del usuario
    @Column(name = "usr_tel")
    private String usr_tel; //numero de telefono del usuario
    @Column(name = "usr_url_link")
    private String usr_url_link; //URL del perfil de linkedIn del usuario
    
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "tbl_usr")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();
    
    public Usuario(){
        
    }

    public Long getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(Long usr_id) {
        this.usr_id = usr_id;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsr_rut() {
        return usr_rut;
    }

    public void setUsr_rut(String usr_rut) {
        this.usr_rut = usr_rut;
    }

    public String getUsr_nom() {
        return usr_nom;
    }

    public void setUsr_nom(String usr_nom) {
        this.usr_nom = usr_nom;
    }

    public String getUsr_ap_pat() {
        return usr_ap_pat;
    }

    public void setUsr_ap_pat(String usr_ap_pat) {
        this.usr_ap_pat = usr_ap_pat;
    }

    public String getUsr_ap_mat() {
        return usr_ap_mat;
    }

    public void setUsr_ap_mat(String usr_ap_mat) {
        this.usr_ap_mat = usr_ap_mat;
    }

    public String getUsr_pass() {
        return usr_pass;
    }

    public void setUsr_pass(String usr_pass) {
        this.usr_pass = usr_pass;
    }

    public String getUsr_tel() {
        return usr_tel;
    }

    public void setUsr_tel(String usr_tel) {
        this.usr_tel = usr_tel;
    }

    public String getUsr_url_link() {
        return usr_url_link;
    }

    public void setUsr_url_link(String usr_url_link) {
        this.usr_url_link = usr_url_link;
    }

  
   
    public Set<UsuarioRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UsuarioRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
    
    @Transient // Esta anotación evita que JPA intente mapear este campo a una columna de base de datos
    private String rol; // Agrega el campo del rol aquí

    // ... otros métodos ...

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    
}
