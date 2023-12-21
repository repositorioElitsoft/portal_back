package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_cat_obs")
@Data
public class ObservationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_obs_id")
    private Long id;

    @Column(name = "cat_obs_desc")
    private String description;

    @OneToMany(mappedBy = "observationCategory", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Observation> observation;

}
