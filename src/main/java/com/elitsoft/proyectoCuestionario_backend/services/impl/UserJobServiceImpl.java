package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.UserJobApprovalDTO;
import com.elitsoft.proyectoCuestionario_backend.repositories.*;

import java.util.*;
import java.util.stream.Collectors;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.elitsoft.proyectoCuestionario_backend.services.UserJobService;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;

/**
 *
 * @author Maeva Martinez
 */
@Service
public class UserJobServiceImpl implements UserJobService {
    
    private final UserRepository userRepository;
    private final UserJobRepository cargoRepository;

    @Autowired
    private UserJobApprovalRepository userJobApprovalRepository;
    @Autowired
    private ApprovalRepository approvalRepository;
    @Autowired
    private ObservationRepository observationRepository;
    @Autowired
    private final UserService userService;

    public UserJobServiceImpl(UserRepository userRepository,
                              UserJobRepository cargoRepository,
                              UserService userService) {
        this.userRepository = userRepository;
        this.cargoRepository = cargoRepository;
        this.userService = userService;
    }

    @Override
    public Boolean guardarCargo(UserJob cargo, String jwt, Date applicationDate) throws Exception {

        Optional<User> usuarioOptional = userService.getUsuarioByToken(jwt);
        if(!usuarioOptional.isPresent()){
            return false;
        }

        User usuario = usuarioOptional.get();
        cargo.setUser(usuario);
        cargo.setApplicationDate(applicationDate);
        // Obtener la lista actual de cargos del usuario
        List<UserJob> cargosActuales = cargoRepository.findByUser(usuario);
        // Agregar el nuevo cargo a la lista
        cargosActuales.add(cargo);
        // Guardar la lista actualizada de cargos
        cargoRepository.saveAll(cargosActuales);
        return true;

    }


    @Override
    public List<UserJob> obtenerCargosPorUsuario(User usr_id) {
        return cargoRepository.findByUser(usr_id);
    }

    @Override
    public List<UserJob> obtenerListaCargos() {
        return cargoRepository.findAll();
    }

    @Override
    public List<UserJob> obtenerCargoUsuario(String jwt) throws Exception {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<UserJob> userJob = cargoRepository.findByUser(userOptional.get());
        if(userJob == null){
            throw new EntityNotFoundException();
        }
        if(userJob.isEmpty()){
            return null;
        }

        return userJob;
    }

    @Override
    public void eliminarCargoPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<UserJob> cargos = cargoRepository.findByUser(usuario.get());
            cargoRepository.deleteAll(cargos);
        }
    }

    @Override
    public boolean eliminarPostulacionPorId(Long postulacionId, String jwt) throws Exception{
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<UserJob> cargoOld = cargoRepository.findById(postulacionId);
        if (!cargoOld.isPresent()) {
            throw new EntityNotFoundException("No se encontró el cargo");
        }

        if (!cargoOld.get().getUser().getId().equals(userOptional.get().getId())) {
            throw new AccessDeniedException("Este usuario no está autorizado para eliminar este cargo");
        }

        Optional<List<Observation>> observationsUserJob = observationRepository.findAllByUserJobId(cargoOld.get().getId());
        observationsUserJob.ifPresent(observations -> observationRepository.deleteAll(observations));
        cargoRepository.deleteById(postulacionId);
        return true;
    }

    @Override
    public List<UserJobApproval> approveUserJob(Long userJobId, String jwt, UserJobApprovalDTO userJobApprovalDTO) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return null;
        }
        Optional<User> usuarioRec = userRepository.findByEmail(token.getPrincipal().toString());
        if (!usuarioRec.isPresent()){
            return null;
        }

        Optional<UserJob> oldUserJob = cargoRepository.findById(userJobId);
        if(!oldUserJob.isPresent()){
            return null;
        }

        Set<String> roles = usuarioRec.get().getRoles().stream().map(Role::getName).collect(Collectors.toSet());

        Approval approval = new Approval();


        if(roles.contains("ENTR_TEC")){
            //approval = approvalRepository.findById(1L).orElse(null);
            approval.setId(1L);
        }
        if(roles.contains("ENTR_GER")){
            //approval = approvalRepository.findById(2L).orElse(null);
            approval.setId(2L);
        }
        if(roles.contains("ENTR_OPER")){
            //approval = approvalRepository.findById(3L).orElse(null);
            approval.setId(3L);
        }

        if(approval == null){
            throw new EntityNotFoundException("Error locating approval table");
        }
        if(approval.getId() == null){
            throw new AccessDeniedException("User doesn't have the necessary roles for approving user jobs:");
        }


        UserJobApproval userJobApproval = new UserJobApproval();
        UserJobApprovalId userJobApprovalId = new UserJobApprovalId();
        userJobApprovalId.setApproval(approval);
        userJobApprovalId.setUserJob(oldUserJob.get());

        Optional<UserJobApproval> oldUserJobApproval = userJobApprovalRepository.findById(userJobApprovalId);
        oldUserJobApproval.ifPresent(jobApproval -> userJobApproval.setDate(jobApproval.getDate()));
        userJobApproval.setId(userJobApprovalId);
        userJobApproval.setIsApproved(userJobApprovalDTO.getIsApproved());
        userJobApprovalRepository.save(userJobApproval);

        return cargoRepository.findById(userJobId).get().getApprovals();
    }


    @Override
    public Boolean actualizarCargo(Long positionId, UserJob cargo, String jwt ) throws Exception{
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }
        Optional <UserJob> userJobOptional = cargoRepository.findById(positionId);
        if (!userJobOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el userjob");
        }
        UserJob userJob = userJobOptional.get();
        userJob.setSalary(cargo.getSalary());
        System.out.println("Pase por aqui");
        System.out.println("Pase por aqui  position Id"+positionId.toString());
        cargoRepository.save(userJob);;
        return true;
    }
}
