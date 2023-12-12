
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author Elitsoft83
 */
@Entity
@Table(name = "TBL_USR")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "usr_id")
public class Usuario  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usr_id;
    private String usr_rut;
    private String usr_nom;
    private String usr_ap_pat;
    private String usr_ap_mat;
    @Column(name="usr_direcc")
    private String usr_direcc;
    @Column(unique = true)
    private String usr_email;
    private String usr_pass;
    private String usr_tel;
    @Column(name = "usr_gen")
    private String usr_gen;
    private String usr_url_link;
    private String usr_rol;
    private String usr_ver_code;
    // Relación muchos a uno con la entidad Pais
    private Boolean usr_is_ver;
    private String usr_rec_tkn;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "id")
    private Long cityId;



    @OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Resultados> resultados;

   // @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    //@JsonManagedReference
    //private List<ObservacionReclutador> observaciones; // unión con tbl_obs_rec


    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Herramienta> herramientas;


    @Column(name = "usr_cv_pth")
    private String cvPath;


    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Laboral> laborales;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Academica> academicas;

    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CargoUsuario> cargoUsuario;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Observacion> observaciones;



}
