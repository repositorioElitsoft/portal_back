package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
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
    @Column(name = "exam_id")
    private Long examenId;
    @Column(name = "exam_titl")
    private String titulo;
    @Column (name = "exam_desc")
    private String descripcion;
    @Column (name = "exam_ptos_max")
    private String puntosMaximos;
    @Column (name = "exam_n_preg")
    private String numeroDePreguntas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "exm_prd",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "prd_id"))
    private List<Producto> productos;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @OneToMany(mappedBy="examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pregunta> preguntas;

    @OneToMany(mappedBy="examen", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Resultados> resultados;

}