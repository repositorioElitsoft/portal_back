package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_JOB_POS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JobPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_pos_id")
    private Long id;

    @Column(name = "job_pos_nom")
    private String name;

    
}
