
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_INF_ACAD")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Academica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inf_acad_id;


    private String titl;
    private String inf_acad_nom_esc;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate inf_acad_fec_ini;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate inf_acad_fec_fin;
    private String inf_acad_est;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "inf_acad_id")
    private List<ReferenciaAcademica> referenciaAcademicas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Usuario usuario;


    
}
