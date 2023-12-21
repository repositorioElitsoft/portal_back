package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author ELITSOFT86
 */
@Entity
@Table ( name = "tbl_exam")
@Data
public class Exam {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @Column(name = "exam_titl")
    private String title;

    @Column (name = "exam_desc", nullable = true)
    private String description;

    @Column (name = "exam_pts_max")
    private String maxPoints;

    @Column (name = "exam_n_quest")
    private String questionNumber;

    @Column (name = "exam_lvl")
    private Long difficultyLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exm_prd",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "prd_id"))
    private List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    private ExamCategory category;

    @OneToMany(mappedBy="exam", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Question> questions;

    @OneToMany(mappedBy="exam", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ExamResult> results;





}