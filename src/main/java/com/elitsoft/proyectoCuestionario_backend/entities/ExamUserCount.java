package com.elitsoft.proyectoCuestionario_backend.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="tbl_exm_usr_count")
public class ExamUserCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exm_usr_count_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usr_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    private Integer count;

}
