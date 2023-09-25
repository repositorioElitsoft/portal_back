/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.PortalElitsoft.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Elitsoft83
 */
@Entity
@Table ( name = "tbl_exam")
public class Examen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long exam_id;
    @Column(name = "exam_titl") 
    private String exam_titl; //titulo
    @Column(name = "exam_act") 
    private boolean activo = false;   //examen activo o no
    @Column(name = "exam_desc")
    private String exam_desc; //descripción
    @Column(name = "exam_n_preg")
    private String exam_n_preg; //numero de preguntas
    @Column(name = "exam_ptos_max")
    private String exam_ptos_max; //puntos maximos del examen
     
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    
    @OneToMany(mappedBy="examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();
    
    public Examen() {
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_titl() {
        return exam_titl;
    }

    public void setExam_titl(String exam_titl) {
        this.exam_titl = exam_titl;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getExam_desc() {
        return exam_desc;
    }

    public void setExam_desc(String exam_desc) {
        this.exam_desc = exam_desc;
    }

    public String getExam_n_preg() {
        return exam_n_preg;
    }

    public void setExam_n_preg(String exam_n_preg) {
        this.exam_n_preg = exam_n_preg;
    }

    public String getExam_ptos_max() {
        return exam_ptos_max;
    }

    public void setExam_ptos_max(String exam_ptos_max) {
        this.exam_ptos_max = exam_ptos_max;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
    
}
