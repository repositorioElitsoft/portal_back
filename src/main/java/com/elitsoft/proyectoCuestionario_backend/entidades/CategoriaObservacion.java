package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "categoriaObservacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Observacion> observaciones = new ArrayList<>();

}
