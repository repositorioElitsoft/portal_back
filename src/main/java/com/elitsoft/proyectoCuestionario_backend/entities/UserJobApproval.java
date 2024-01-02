package com.elitsoft.proyectoCuestionario_backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tlb_apr_job")
@Data
public class UserJobApproval {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tp_apr_id") // Nombre de la columna que será clave foránea para la tabla user
    private Approval approval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_usr_id") // Nombre de la columna que será clave foránea para la tabla user
    @JsonIgnore
    private UserJob userJob;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apr_job_date")
    private Date date;
}
