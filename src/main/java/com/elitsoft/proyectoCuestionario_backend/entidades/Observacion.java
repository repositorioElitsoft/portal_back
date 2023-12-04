package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_OBS")
@Data
public class Observacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long obs_id;

    @Column(name = "obs_desc")
    private String obs_desc;

    @Column(name = "obs_fec_cre")
    @CreationTimestamp
    private Date obs_fec_cre;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "obs_fec_mod")
    private Date obs_fec_mod;

    @Column(name = "apr_oper")
    private String apr_oper;

    @Column(name = "apr_tec")
    private String apr_tec;

    @Column(name = "apr_ger")
    private String apr_ger;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @JsonBackReference
    @Getter
    private Usuario usuario;

    @Column(name = "usr_id_obs")
    private Long usr_id_obs;

    @Column(name = "usr_id_obs_mod")
    private Long usr_id_obs_mod;

    @ManyToOne
    @JoinColumn(name = "cat_obs_id")
    private CategoriaObservacion categoriaObservacion;





}
