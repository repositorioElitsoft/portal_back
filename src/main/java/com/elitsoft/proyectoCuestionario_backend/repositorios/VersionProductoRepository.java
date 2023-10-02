package com.elitsoft.proyectoCuestionario_backend.repositorios;


import com.elitsoft.proyectoCuestionario_backend.entidades.VersionProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VersionProductoRepository extends JpaRepository<VersionProducto, Long> {

    @Query("SELECT p FROM VersionProducto p WHERE p.prd.id = :prd_id")
    List<VersionProducto> findByProd_Id(@Param("prd_id") Long prd_id);

}
