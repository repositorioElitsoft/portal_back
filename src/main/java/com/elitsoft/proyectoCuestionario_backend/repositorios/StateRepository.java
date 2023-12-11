package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.City;
import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State getReferenceById(Long id);

    List<State> findAll();

    List<State> findByCountryId(Long countryId);
    List<State> findAllByCountry(Country country);




}