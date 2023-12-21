package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.Tool;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.repositories.ToolRepository;
import com.elitsoft.proyectoCuestionario_backend.repositories.UserRepository;
import com.elitsoft.proyectoCuestionario_backend.services.ToolService;

import java.util.*;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Maeva Martinez
 */

@Service
public class ToolServiceImpl implements ToolService {

    private final ToolRepository toolRepository;
    private final UserRepository userRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    public ToolServiceImpl(ToolRepository toolRepository, UserRepository userRepository, UserService userService) {
        this.toolRepository = toolRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public Boolean guardarHerramientas(List<Tool> tools, String Jwt) throws Exception {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(Jwt);
        if (token == null){
            return false;
        }

        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()){
            return false;
        }



        List<Tool> herramientasAntiguas = toolRepository.findByUser(usuarioOpt.get());

        Set<Long> idsEncontradas = new HashSet<>();

        for (Tool tool : tools){
            tool.setUser(usuarioOpt.get());
            toolRepository.save(tool);
            idsEncontradas.add(tool.getId());
        }

        for (Tool toolAntigua : herramientasAntiguas){
                if(!idsEncontradas.contains(toolAntigua.getId())){
                    toolRepository.deleteLaboralHerramientaReferences(toolAntigua.getId());
                    toolRepository.delete(toolAntigua);
                }
        }




        return true;
    }



    @Override
    public List<Tool> obtenerListaHerramientasPorUsuario(String jwt) {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Tool> tools = toolRepository.findByUser(userOptional.get());
        if(tools == null){
            return Collections.emptyList();
        }

        return tools;
    }
    
    @Override
    public Tool obtenerHerramienta(Long herr_usr_id) {
        return toolRepository.findById(herr_usr_id).orElse(null);
    }
    
    
    @Override
    public List<Tool> obtenerHerramientasConProductosPorUsuario(Long usuarioId) {
        return toolRepository.obtenerHerramientasConProductosPorUsuario(usuarioId);
    }

    @Override
    public void eliminarHerramientaPorUsuario(Long usuarioId) {
        Optional<User> usuario = userRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            List<Tool> tools = toolRepository.findByUser(usuario.get());
            toolRepository.deleteAll(tools);
        }
    }

   
}


