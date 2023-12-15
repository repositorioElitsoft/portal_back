package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */

@Entity
@Table(name = "TBL_INF_LAB")
@Data
public class Laboral {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inf_lab_id;
    
    private String inf_lab_crg_emp;
    private String inf_lab_emp;
    private String inf_lab_act;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inf_lab_fec_ini;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inf_lab_fec_fin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "inf_lab_id")
    private List<ReferenciaLaboral> referenciasLaborales;

    @ManyToMany
    @JoinTable(name = "laboral_herramienta",
            joinColumns = @JoinColumn(name = "inf_lab_id"),
            inverseJoinColumns = @JoinColumn(name = "herr_usr_id"))
    private List<Herramienta> herramientas;
    
}
