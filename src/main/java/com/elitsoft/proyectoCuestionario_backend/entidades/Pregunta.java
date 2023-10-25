package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Maeva Martínez 
 */
@Entity
@Table(name = "tbl_prg")
@Data
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prg_id")
    private Long preguntaId;

    @Column(length = 5000, name = "prg")
    private String contenido;
    @Column(name = "prg_opc1")
    private String opcion1;
    @Column(name = "prg_opc2")
    private String opcion2;
    @Column(name = "prg_opc3")
    private String opcion3;
    @Column(name = "prg_opc4")
    private String opcion4;
    @Column(name = "prg_resp")
    private String respuesta;

    private int prg_ptje_prg ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Examen examen;
}