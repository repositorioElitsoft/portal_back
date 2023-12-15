package com.elitsoft.proyectoCuestionario_backend.entidades;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_exm_usr_count")
public class ExamenUserCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Examen examen;

    private Integer count;

}
