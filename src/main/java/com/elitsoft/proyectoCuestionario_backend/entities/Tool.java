package com.elitsoft.proyectoCuestionario_backend.entities;

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
@Table(name = "TBL_TOOL_USR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Tool {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_usr_id")
    private Long id;
    @Column(name = "tool_usr_yrs_exp")
    private Integer yearsOfExperience;
    @Column(name = "tool_is_cert")
    private Boolean isCertified;

    @Column(name = "tool_lvl")
    @Pattern(regexp = "\\b(?:alto|medio|bajo)\\b",
            message = "Error valores solamente pueden ser alto, medio, or bajo.")
    private String level;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vrs_prd_id", referencedColumnName = "vrs_id")
    private ProductVersion productVersion;

    @ManyToMany
    @JsonIgnore
    private List<Employment> employments;





}
