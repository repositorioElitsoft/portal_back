package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REF_LAB")
@Data
public class ReferenciaLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ref_lab_id;

    private String ref_lab_nom;
    private String ref_lab_emp;
    private String ref_lab_email;
    private String ref_lab_tel;


}
