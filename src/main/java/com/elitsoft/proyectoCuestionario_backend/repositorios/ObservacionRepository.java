package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Observacion;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObservacionRepository extends JpaRepository <Observacion, Long> {

    List<Observacion> findByUsuario(Usuario usuario);

}