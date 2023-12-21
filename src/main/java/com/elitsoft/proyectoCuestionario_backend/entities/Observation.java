package com.elitsoft.proyectoCuestionario_backend.entities;

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
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obs_id")
    private Long id;

    @Column(name = "obs_desc")
    private String description;

    @Column(name = "obs_fec_cre")
    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "obs_fec_mod")
    private Date modificationDate;

    @Column(name = "obs_apr_oper")
    private String operationalApproval;

    @Column(name = "obs_apr_tec")
    private String technicalApproval;

    @Column(name = "obs_apr_man")
    private String managementApproval;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @JsonBackReference
    @Getter
    private User user;
    @Column(name = "usr_id_obs")
    private Long usr_id_obs;
    @Column(name = "usr_id_obs_mod")
    private Long usr_id_obs_mod;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cat_obs_id")  // Nombre de la columna que almacena la clave foránea
    private ObservationCategory observationCategory;


}
