package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_REF_ACAD")

@Data
public class ReferenciaAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ref_acad_id;

    private String ref_acad_nom;
    private String ref_acad_ins;
    private String ref_acad_email;
    private String ref_acad_tel;


}
