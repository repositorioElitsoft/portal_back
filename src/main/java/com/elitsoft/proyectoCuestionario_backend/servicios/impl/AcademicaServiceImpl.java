package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.AcademicaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.AcademicaService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class AcademicaServiceImpl implements AcademicaService {
    
    private final AcademicaRepository academicaRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UsuarioService usuarioService;

    public AcademicaServiceImpl(AcademicaRepository academicaRepository, UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.academicaRepository = academicaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Boolean guardarAcademica(Academic academic, String jwt) throws Exception {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        academic.setUser(userOptional.get());

      //  if (academica.getReferenciaAcademicas() != null) {
        //    for (ReferenciaAcademica referencia : academica.getReferenciaAcademicas()) {
          //      referencia.setAcademica(academica);
        //    }
      //  }

        academicaRepository.save(academic);
        return true;
    }
    
    @Override
    public Boolean guardarListaAcademicas(List<Academic> academics, String jwt)  {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        academicaRepository.findByUsuario(userOptional.get())
                .forEach((academicaRepository::delete));

        academics.forEach(academica -> {
            academica.setUser(userOptional.get());
            academicaRepository.save(academica);
        });



        return true;
    }
    
    @Override
    public List<Academic> obtenerAcademicasPorUsuario(User usr_id) {
        return academicaRepository.findByUsuario(usr_id);
    }

    @Override
    public Boolean actualizarAcademica(Long academicaId, Academic academic, String jwt) throws Exception{
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }


        academic.getReferenciaAcademicas().forEach(a -> {
        System.out.println(a.toString());
        });

        academic.setInf_acad_id(academicaId);
        academic.setUser(userOptional.get());

        academicaRepository.save(academic);
        return true;
    }

    @Override
    public List<Academic> obtenerListaAcademicas(String jwt) {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Academic> academics = academicaRepository.findByUsuario(userOptional.get());
        if(academics == null){
            return Collections.emptyList();
        }

        return academics;
    }
    
    @Override
    public List<String> obtenerEstadosAcademicosUnicos() {
        return academicaRepository.findAllDistinctInfAcadEst();
    }

    @Override
    public Boolean deleteAcademica(Long academicaId, String jwt) throws Exception {
        Optional<User> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Academic> academicaOld = academicaRepository.findById(academicaId);
        if( !academicaOld.isPresent()){
            throw new EntityNotFoundException("No se encontró la entidad laboral");
        }

        if(!academicaOld.get().getUser().getUsr_id().equals(userOptional.get().getUsr_id())){
            throw new AccessDeniedException("Este usuario no está autorizado para actualizar este entidad");
        }

        academicaRepository.deleteById(academicaId);
        return true;
    }

    @Override
    public void eliminarAcademicasPorUsuario(Long usuarioId) {
        Optional<User> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Academic> academics = academicaRepository.findByUsuario(usuario.get());
            academicaRepository.deleteAll(academics);
        }
    }


    @Override
    public Academic obtenerAcademica(Long academicaId) {
        Optional<Academic> academicaOptional = academicaRepository.findById(academicaId);
        if (academicaOptional.isPresent()) {
            return academicaOptional.get();
        } else {
            throw new EntityNotFoundException("No se encontró la entidad con el ID: " + academicaId);
        }
    }



}
