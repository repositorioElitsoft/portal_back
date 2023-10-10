
package com.elitsoft.proyectoCuestionario_backend.entidades;

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
    private Long prg_id;

    @Column(length = 5000)
    private String prg; // antes era contenido

    private String prg_opc1;
    private String prg_opc2;
    private String prg_opc3;
    private String prg_opc4;
    private String prg_resp;

    private int prg_ptje_prg ;

    @ManyToOne(fetch = FetchType.EAGER)
    private Examen examen;



}