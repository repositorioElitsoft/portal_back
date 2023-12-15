package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadosRepository extends JpaRepository <Resultados, Long> {


    List<Resultados> findByUsuario(User user);

}