package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TBL_OBS")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obs_id")
    private Long id;
    @Column(name = "obs_desc")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_usr_id")
    @JsonBackReference
    private UserJob userJob;

    @OneToMany(mappedBy = "observation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ObservationUpdate> updates;





}
