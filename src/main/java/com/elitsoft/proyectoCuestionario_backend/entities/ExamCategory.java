
package com.elitsoft.proyectoCuestionario_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author ELITSOFT86
 */
@Entity
@Table ( name = "tbl_exam_cat")
@Data
public class ExamCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_cat_id")
    private Long id;
    @Column(name = "exam_cat_titl")
    private String title;
    @Column(name = "exam_cat_desc")
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Exam> exams;


}
