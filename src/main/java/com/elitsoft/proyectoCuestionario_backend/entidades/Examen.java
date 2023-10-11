
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author ELITSOFT86
 */
@Entity
@Table ( name = "tbl_exam")
@Data

public class Examen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exam_id;
    
    private String exam_titl;
    @Column (name = "exam_desc")
    private String exam_desc;
    private String exam_ptos_max;
    private String exam_n_preg;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    
    @OneToMany(mappedBy="examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();


    
    
    
    
    
    
}
