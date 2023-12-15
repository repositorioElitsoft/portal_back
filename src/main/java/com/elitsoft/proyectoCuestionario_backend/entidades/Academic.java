
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
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
    @Column(name = "inf_acad_id")
    private Long id;
    @Column(name = "inf_acad_titl")
    private String degree;
    @Column(name = "inf_acad_name_uni")
    private String university;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "inf_acad_fec_ini")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "inf_acad_fec_fin")
    private LocalDate endDate;
    @Column(name = "inf_acad_fec_fin")
    private String status;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "inf_acad_id")
    private List<ReferenciaAcademica> academicReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "usr_id") // Nombre de la columna que será clave foránea para la tabla user
    private User user;


    
}
