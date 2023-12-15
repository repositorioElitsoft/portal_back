
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author Elitsoft83
 */
@Entity
@Table(name = "TBL_USR")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "usr_id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long userId;
    @Column(name = "usr_rut")
    private String rut;
    @Column(name = "fst_lst_id")
    private String name;
    @Column(name = "scn_lst_id")
    private String firstlastname;
    @Column(name = "usr_id")
    private String secondLastname;
    @Column(name="usr_addr")
    private String address;
    @Column(unique = true, name = "usr_email")
    private String email;
    @Column(name = "usr_pass")
    private String password;
    @Column(name = "usr_phone")
    private String phone;
    @Column(name = "usr_gen")
    private String gender;
    @Column(name = "usr_url_link")
    private String verificationUrl;
    @Column(name = "usr_rol")
    private String rol;
    @Column(name = "usr_ver_code")
    private String verificationToken;
    @Column(name = "usr_is_ver")
    // Relación muchos a uno con la entidad Pais
    private Boolean isVerified;
    @Column(name = "usr_rec_tkn")
    private String recoveryToken;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "usr_cv_pth")
    private String cvPath;

    @ManyToOne
    @JoinColumn(name = "usr_city_id")
    private City city;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Resultados> results;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Herramienta> tools;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Laboral> jobs;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Academic> academics;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CargoUsuario> userJob;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Observacion> observations;



}