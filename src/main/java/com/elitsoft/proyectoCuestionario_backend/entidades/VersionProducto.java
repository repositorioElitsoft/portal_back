package com.elitsoft.proyectoCuestionario_backend.entidades;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_vrs_prd")
public class VersionProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vrs_id;
    private String vrs_name;
    @ManyToOne
    @JoinColumn(name = "prd_id")
    private Producto prd;
}
