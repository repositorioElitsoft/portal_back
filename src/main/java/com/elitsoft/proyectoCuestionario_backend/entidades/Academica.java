
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
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
@Table(name = "TBL_INF_ACAD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Academica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inf_acad_id;


    private String titl;
    private String inf_acad_nom_esc;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inf_acad_fec_ini;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inf_acad_fec_fin; 
    private String inf_acad_est;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;

    public Academica() {
    }

    public Long getInf_acad_id() {
        return inf_acad_id;
    }

    public void setInf_acad_id(Long inf_acad_id) {
        this.inf_acad_id = inf_acad_id;
    }

    public String getTitl() {
        return titl;
    }

    public void setTitl(String titl) {
        this.titl = titl;
    }

    public String getInf_acad_nom_esc() {
        return inf_acad_nom_esc;
    }

    public void setInf_acad_nom_esc(String inf_acad_nom_esc) {
        this.inf_acad_nom_esc = inf_acad_nom_esc;
    }

    public Date getInf_acad_fec_ini() {
        return inf_acad_fec_ini;
    }

    public void setInf_acad_fec_ini(Date inf_acad_fec_ini) {
        this.inf_acad_fec_ini = inf_acad_fec_ini;
    }

    public Date getInf_acad_fec_fin() {
        return inf_acad_fec_fin;
    }

    public void setInf_acad_fec_fin(Date inf_acad_fec_fin) {
        this.inf_acad_fec_fin = inf_acad_fec_fin;
    }

    public String getInf_acad_est() {
        return inf_acad_est;
    }

    public void setInf_acad_est(String inf_acad_est) {
        this.inf_acad_est = inf_acad_est;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    
}
