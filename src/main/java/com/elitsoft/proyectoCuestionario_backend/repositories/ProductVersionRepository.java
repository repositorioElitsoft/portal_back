package com.elitsoft.proyectoCuestionario_backend.repositories;


import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import com.elitsoft.proyectoCuestionario_backend.entities.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long> {

    @Query("SELECT p FROM ProductVersion p WHERE p.id = :prd_id")
    List<ProductVersion> findByProd_Id(@Param("prd_id") Long prd_id);

    @Query("SELECT v FROM ProductVersion v WHERE v.name = :vrsName AND v.product = :producto")
    Optional<ProductVersion> findByVrsNameAndPrd(@Param("vrsName") String vrsName, @Param("producto") Product product);

}
