package com.elitsoft.proyectoCuestionario_backend.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_REF_LAB")
@Data
public class EmploymentReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_lab_id")
    private Long id;
    @Column(name = "ref_lab_nom")
    private String name;
    @Column(name = "ref_lab_emp")
    private String company;
    @Column(name = "ref_lab_email")
    private String email;
    @Column(name = "ref_lab_phn")
    private String phone;


}
