package com.elitsoft.proyectoCuestionario_backend.services;

import com.elitsoft.proyectoCuestionario_backend.entities.Gender;

import java.util.List;
import java.util.Optional;

public interface GenderService {

    List<Gender> findAllGenders();

    Optional<Gender> findGenderById(Long id);

    Gender saveGender(Gender gender);

    void deleteGender(Long id);

}
