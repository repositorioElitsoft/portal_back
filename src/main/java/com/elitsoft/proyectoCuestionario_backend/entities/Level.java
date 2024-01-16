package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_level")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Long id;
    @Column(name = "level_desc")
    private String description;
}
