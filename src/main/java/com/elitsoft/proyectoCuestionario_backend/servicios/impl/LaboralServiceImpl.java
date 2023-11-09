package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.HerramientaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.LaboralRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.LaboralService;

import java.util.*;

import com.elitsoft.proyectoCuestionario_backend.servicios.UsuarioService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martinez
 */
@Service
public class LaboralServiceImpl implements LaboralService {
    
    private final LaboralRepository laboralRepository;
    private final UsuarioRepository usuarioRepository;
    private final HerramientaRepository herramientaRepository;
    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    public LaboralServiceImpl(LaboralRepository laboralRepository,
                              UsuarioRepository usuarioRepository,
                              HerramientaRepository herramientaRepository,
                              UsuarioService usuarioService) {
        this.laboralRepository = laboralRepository;
        this.usuarioRepository = usuarioRepository;
        this.herramientaRepository = herramientaRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Boolean guardarLaborales(List<Laboral> laborales, String jwt) throws Exception {

        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }

        laboralRepository.findByUsuario(userOptional.get())
                .forEach((laboralRepository::delete));

        laborales.forEach(laboral -> {
            laboral.setUsuario(userOptional.get());
            laboralRepository.save(laboral);
        });


        return true;
    }

    @Override
    public Boolean guardarLaboral(Laboral laboral, String jwt) throws Exception {
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);

        if (!userOptional.isPresent()){
            return false;
        }
        laboral.setUsuario(userOptional.get());

        if (laboral.getReferenciasLaborales() != null) {
            for (ReferenciaLaboral referencia : laboral.getReferenciasLaborales()) {
                referencia.setLaboral(laboral);
            }
        }

        laboralRepository.save(laboral);
        return true;
    }

    @Override
    public Boolean actualizarLaboral(Long laboralId, Laboral laboral, String jwt) throws Exception{
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Laboral> laboralOld = laboralRepository.findById(laboralId);
        if( !laboralOld.isPresent()){
            throw new EntityNotFoundException("No se encontró la entidad laboral");
        }

        if(!laboralOld.get().getUsuario().getUsr_id().equals(userOptional.get().getUsr_id())){
            throw new AccessDeniedException("Este usuario no está autorizado para actualizar este entidad");
        }

        Laboral laboralExistente = laboralOld.get();

        // Actualizamos los campos de Laboral
        laboralExistente.setInf_lab_crg_emp(laboral.getInf_lab_crg_emp());
        laboralExistente.setInf_lab_emp(laboral.getInf_lab_emp());
        laboralExistente.setInf_lab_act(laboral.getInf_lab_act());
        laboralExistente.setInf_lab_fec_ini(laboral.getInf_lab_fec_ini());
        laboralExistente.setInf_lab_fec_fin(laboral.getInf_lab_fec_fin());

        // Actualizamos la lista de referencias laborales
        List<ReferenciaLaboral> referenciasActualizadas = laboral.getReferenciasLaborales();
        if (referenciasActualizadas != null) {
            // Actualizamos las referencias existentes o añadir las nuevas
            for (ReferenciaLaboral referencia : referenciasActualizadas) {
                if (referencia.getRef_lab_id() == null) {
                    referencia.setLaboral(laboralExistente);
                    laboralExistente.getReferenciasLaborales().add(referencia);
                } else {
                    ReferenciaLaboral referenciaExistente = laboralExistente.getReferenciasLaborales().stream()
                            .filter(r -> r.getRef_lab_id().equals(referencia.getRef_lab_id()))
                            .findFirst()
                            .orElse(null);
                    if (referenciaExistente != null) {
                        referenciaExistente.setRef_lab_nom(referencia.getRef_lab_nom());
                        referenciaExistente.setRef_lab_emp(referencia.getRef_lab_emp());
                        referenciaExistente.setRef_lab_email(referencia.getRef_lab_email());
                        referenciaExistente.setRef_lab_tel(referencia.getRef_lab_tel());
                    } else {
                        throw new EntityNotFoundException("Referencia laboral no encontrada con id: " + referencia.getRef_lab_id());
                    }
                }
            }
            laboralExistente.getReferenciasLaborales().removeIf(
                    refExistente -> referenciasActualizadas.stream()
                            .noneMatch(refActualizada -> refActualizada.getRef_lab_id().equals(refExistente.getRef_lab_id()))
            );
        }

        laboralRepository.save(laboralExistente);

        return true;
    }


    @Override
    public List<Laboral> obtenerListaLaboral(String jwt) throws Exception{
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Laboral> laborales = laboralRepository.findByUsuario(userOptional.get());
        if(laborales == null){
            return Collections.emptyList();
        }

        return laborales;
    }

    @Override
    public Boolean deleteLaboral(Long laboralId, String jwt) throws Exception {
        Optional<Usuario> userOptional = usuarioService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Laboral> laboralOld = laboralRepository.findById(laboralId);
        if( !laboralOld.isPresent()){
            throw new EntityNotFoundException("No se encontró la entidad laboral");
        }

        if(!laboralOld.get().getUsuario().getUsr_id().equals(userOptional.get().getUsr_id())){
            throw new AccessDeniedException("Este usuario no está autorizado para actualizar este entidad");
        }

        laboralRepository.deleteById(laboralId);
        return true;
    }


    @Override
    public void eliminarLaboralPorUsuario(Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Laboral> laborales = laboralRepository.findByUsuario(usuario.get());
            laboralRepository.deleteAll(laborales);
        }
    }

}
