/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sistema.PortalElitsoft.Entidades;

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
 * @author Elitsoft83
 */


@Entity
@Table(name = "tbl_prg")
public class Pregunta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prg_id")
    private Long prg_id;
    @Column(name = "prg",length = 5000)
    private String prg; //contenido de la pregunta
    @Column(name = "prg_opc1")
    private String prg_opc1;
    @Column(name = "prg_opc2")
    private String prg_opc2;
    @Column(name = "prg_opc3")
    private String prg_opc3;
    @Column(name = "prg_opc4")
    private String prg_opc4;
    @Transient
    @Column(name = "prg_respDada")
    private String prg_respDada; //respuesta dada 
    @Column(name = "prg_resp")
    private String prg_resp; //respuesta d la pregunta
    @Column(name = "prg_ptje_prg")
    private String prg_ptje_prg; //puntaje pregunta
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Examen examen;
    
    public Pregunta(){

    }

    public Long getPrg_id() {
        return prg_id;
    }

    public void setPrg_id(Long prg_id) {
        this.prg_id = prg_id;
    }

    public String getPrg() {
        return prg;
    }

    public void setPrg(String prg) {
        this.prg = prg;
    }

    public String getPrg_opc1() {
        return prg_opc1;
    }

    public void setPrg_opc1(String prg_opc1) {
        this.prg_opc1 = prg_opc1;
    }

    public String getPrg_opc2() {
        return prg_opc2;
    }

    public void setPrg_opc2(String prg_opc2) {
        this.prg_opc2 = prg_opc2;
    }

    public String getPrg_opc3() {
        return prg_opc3;
    }

    public void setPrg_opc3(String prg_opc3) {
        this.prg_opc3 = prg_opc3;
    }

    public String getPrg_opc4() {
        return prg_opc4;
    }

    public void setPrg_opc4(String prg_opc4) {
        this.prg_opc4 = prg_opc4;
    }

    public String getPrg_respDada() {
        return prg_respDada;
    }

    public void setPrg_respDada(String prg_respDada) {
        this.prg_respDada = prg_respDada;
    }

    public String getPrg_resp() {
        return prg_resp;
    }

    public void setPrg_resp(String prg_resp) {
        this.prg_resp = prg_resp;
    }

    public String getPrg_ptje_prg() {
        return prg_ptje_prg;
    }

    public void setPrg_ptje_prg(String prg_ptje_prg) {
        this.prg_ptje_prg = prg_ptje_prg;
    }
    
    
    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
    
    
    
}
