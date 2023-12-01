package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 *
 * @author Maeva Martínez 
 */
@Entity
@Table(name = "TBL_HERR_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Herramienta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "herr_usr_id")
    private Long herr_usr_id;
    
    private Integer herr_usr_anos_exp;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    @JsonBackReference
    private Usuario usuario;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vrs_prd_id", referencedColumnName = "vrs_id")
    private VersionProducto versionProducto;

    private Boolean herr_is_cert;

    @Pattern(regexp = "\\b(?:alto|medio|bajo)\\b",
            message = "Error valores solamente pueden ser alto, medio, or bajo.")
    private String herr_nvl;


    @ManyToMany
    @JsonIgnore
    private List<Laboral> laborals;





}
