
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Date;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_CRG_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class CargoUsuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crg_usr_id;
    
    private String crg_usr_pret;
    private String crg_prf;
    private String disponibilidad;
    private String tiempo_incorporacion;
    private String otro_tiempo_incorporacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crg_elit_id")
    private CargoElitsoft cargoElitsoft;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_Postulacion")
    private Date fechaPostulacion;

    public CargoUsuario() {
    }

    public Long getCrg_usr_id() {
        return crg_usr_id;
    }

    public void setCrg_usr_id(Long crg_usr_id) {
        this.crg_usr_id = crg_usr_id;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }
}
