package com.elitsoft.proyectoCuestionario_backend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_apr_job")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserJobApproval {

    @EmbeddedId
    private UserJobApprovalId id;
    @Column(name = "apr_job_is_apr")
    private Boolean isApproved;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apr_job_date")
    private Date date;

}
