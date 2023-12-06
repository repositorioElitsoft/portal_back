package com.elitsoft.proyectoCuestionario_backend.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_cat_obs")
@Data
public class CategoriaObservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_obs_id")
    private Long cat_obs_id;

    @Column(name = "cat_obs_desc")
    private String cat_obs_desc;


    //private List<Observacion> observaciones;
}
