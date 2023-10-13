package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Examen;
import com.elitsoft.proyectoCuestionario_backend.entidades.Pregunta;
import com.elitsoft.proyectoCuestionario_backend.entidades.Resultados;
import com.elitsoft.proyectoCuestionario_backend.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultadosRepository extends JpaRepository <Resultados, Long> {


    List<Resultados> findByUsuario(Usuario usuario);



}