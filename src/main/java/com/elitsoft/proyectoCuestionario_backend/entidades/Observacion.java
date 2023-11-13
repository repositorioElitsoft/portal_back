package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_OBS")
@Data
public class Observacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long obs_id;

    @Column(name = "obs_texto")
    private String obs_texto;

    @Column(name = "obs_categoria")
    private String obs_categoria;

    @Column(name = "obs_fecha_creacion")
    @CreationTimestamp
    private Date obs_fecha_creacion;

    @Column(name = "obs_final")
    private String obs_final;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    @JsonIgnore
    private Usuario usuario;
}
