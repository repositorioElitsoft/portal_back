package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Maeva Martínez
 */

@Entity
@Table(name = "TBL_INF_LAB")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Laboral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inf_lab_id;
    
    private String inf_lab_crg_emp;
    private String inf_lab_emp;
    private String inf_lab_act;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inf_lab_fec_ini;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inf_lab_fec_fin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;
    
    @ManyToMany
    @JoinTable(
        name = "Laboral_Herramienta",
        joinColumns = @JoinColumn(name = "inf_lab_id"),
        inverseJoinColumns = @JoinColumn(name = "herr_usr_id")
    )
    private Set<Herramienta> herramientas = new HashSet<>();
    

    public Laboral() {
    }

    public Long getInf_lab_id() {
        return inf_lab_id;
    }

    public void setInf_lab_id(Long inf_lab_id) {
        this.inf_lab_id = inf_lab_id;
    }

    public String getInf_lab_crg_emp() {
        return inf_lab_crg_emp;
    }

    public void setInf_lab_crg_emp(String inf_lab_crg_emp) {
        this.inf_lab_crg_emp = inf_lab_crg_emp;
    }

    public String getInf_lab_emp() {
        return inf_lab_emp;
    }

    public void setInf_lab_emp(String inf_lab_emp) {
        this.inf_lab_emp = inf_lab_emp;
    }

    public String getInf_lab_act() {
        return inf_lab_act;
    }

    public void setInf_lab_act(String inf_lab_act) {
        this.inf_lab_act = inf_lab_act;
    }

    public Date getInf_lab_fec_ini() {
        return inf_lab_fec_ini;
    }

    public void setInf_lab_fec_ini(Date inf_lab_fec_ini) {
        this.inf_lab_fec_ini = inf_lab_fec_ini;
    }

    public Date getInf_lab_fec_fin() {
        return inf_lab_fec_fin;
    }

    public void setInf_lab_fec_fin(Date inf_lab_fec_fin) {
        this.inf_lab_fec_fin = inf_lab_fec_fin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Herramienta> getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(Set<Herramienta> herramientas) {
        this.herramientas = herramientas;
    }

    
    
}
