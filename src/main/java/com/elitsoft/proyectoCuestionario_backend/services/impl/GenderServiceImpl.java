package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.entities.Gender;
import com.elitsoft.proyectoCuestionario_backend.repositories.GenderRepository;
import com.elitsoft.proyectoCuestionario_backend.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public List<Gender> findAllGenders() {
        return genderRepository.findAll();
    }

    @Override
    public Optional<Gender> findGenderById(Long id) {
        return genderRepository.findById(id);
    }

    @Override
    public Gender saveGender(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public void deleteGender(Long id) {
        genderRepository.deleteById(id);
    }


}
