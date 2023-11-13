package com.elitsoft.proyectoCuestionario_backend.repositorios;


import com.elitsoft.proyectoCuestionario_backend.entidades.Producto;
import com.elitsoft.proyectoCuestionario_backend.entidades.VersionProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VersionProductoRepository extends JpaRepository<VersionProducto, Long> {

    @Query("SELECT p FROM VersionProducto p WHERE p.prd.id = :prd_id")
    List<VersionProducto> findByProd_Id(@Param("prd_id") Long prd_id);

    @Query("SELECT v FROM VersionProducto v WHERE v.vrs_name = :vrsName AND v.prd = :producto")
    Optional<VersionProducto> findByVrsNameAndPrd(@Param("vrsName") String vrsName, @Param("producto") Producto producto);

}
