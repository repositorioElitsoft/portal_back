package com.elitsoft.proyectoCuestionario_backend.services.impl;

import com.elitsoft.proyectoCuestionario_backend.config.jwt.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entities.*;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.CreateToolDTO;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.FileContentDTO;
import com.elitsoft.proyectoCuestionario_backend.repositories.*;
import com.elitsoft.proyectoCuestionario_backend.services.FileService;
import com.elitsoft.proyectoCuestionario_backend.services.ToolService;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.elitsoft.proyectoCuestionario_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

/**
 *
 * @author Maeva Martinez
 */

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    private ToolRepository toolRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductVersionRepository productVersionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private CertificationRepository certificationRepository;
    @Autowired
    private EmploymentRepository employmentRepository;


    @Transactional
    @Override
    public Boolean deleteUserTool(Long toolId, String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null){
            return false;
        }
        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()){
            return false;
        }

        employmentRepository.deleteToolsAssociation(toolId);

        toolRepository.deleteById(toolId);
        return true;
    }

    @Override
    public Boolean createTool(CreateToolDTO toolDTO, String Jwt) throws Exception {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(Jwt);
        if (token == null){
            return false;
        }

        Optional<User> usuarioOpt = userRepository.findByEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()){
            return false;
        }

        Tool newTool = new Tool();
        newTool.setUser(usuarioOpt.get());
        newTool.setLevel(toolDTO.getLevel());
        newTool.setYearsOfExperience(toolDTO.getYearsOfExperience());

        System.out.println("incoming tool "+ toolDTO);


        //Categoría será siempre desde base de datos, fija
        ProductCategory category = new ProductCategory();
        category.setId(toolDTO.getCategoryId());
        toolDTO.getProduct().setProductCategory(category);

        if(toolDTO.getProduct().getId() == 0){
            toolDTO.getProduct().setId(null);
            toolDTO.setProduct(productRepository.save(toolDTO.getProduct()));
        }

        if(toolDTO.getVersion().getId() == 0){
            toolDTO.getVersion().setId(null);
            toolDTO.getVersion().setProduct(toolDTO.getProduct());
            toolDTO.setVersion(productVersionRepository.save(toolDTO.getVersion()));
        }
        newTool.setProductVersion(toolDTO.getVersion());
        newTool.getProductVersion().setProduct(toolDTO.getProduct());


        toolRepository.save(newTool);
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

    @Override
    public Tool addToolCertification(Long toolId, MultipartFile certification, String jwt) throws IOException {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        Optional<Tool> toolOptional = toolRepository.findById(toolId);
        if (!toolOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró la herramienta");
        }


        //TODO check if user owns tool
        String filePath = fileService.saveCertification(certification);
        User user = userOptional.get();

        Tool tool = toolOptional.get();
        Certification newCertification = new Certification();
        newCertification.setUrl(filePath);
        tool.getCertifications().add(newCertification);

        //save certification
        return toolRepository.save(tool);
    }

    @Override
    public Boolean deleteToolCertification(Long toolId, Long certId, String jwt) throws IOException {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }
        Optional<Tool> toolOptional = toolRepository.findById(toolId);
        if(!toolOptional.isPresent()){
            throw new EntityNotFoundException();
        }

        if(userOptional.get().getTools().stream().noneMatch(tool -> tool.getId().equals(toolId))){
            return false;
        }
        // TODO QUE SE ELIMINEN DE LAS CARPETAS System.out.println("is qeauql");

        Optional<Certification> certificationOptional = toolOptional.get().getCertifications().stream()
                .filter(cert -> cert.getId().equals(certId)).findFirst();
        if (!certificationOptional.isPresent()){
            return false;
        }
        certificationRepository.deleteById(certificationOptional.get().getId());
        return fileService.deleteFile(certificationOptional.get().getUrl());
    }

    @Override
    public List<Tool> getToolsForExams(String jwt) {
        Optional<User> userOptional = userService.getUsuarioByToken(jwt);
        if (!userOptional.isPresent()){
            throw new EntityNotFoundException("No se encontró el usuario");
        }

        List<Tool> tools = toolRepository.findByUser(userOptional.get());


        return tools.stream().filter(tool -> {
            return tool.getProductVersion().getProduct().getQuestions().size() >= 10;
        }).collect(Collectors.toList());
    }

    @Override
    public FileContentDTO downloadCertification(Long certId) throws IOException, EntityNotFoundException {
        Optional<Certification> certification = certificationRepository.findById(certId);
        if(!certification.isPresent()){
            return null;
        }

        FileContentDTO fileContent = new FileContentDTO();
        fileContent.setFileName(certification.get().getUrl());
        fileContent.setResource(fileService.getCertification(certification.get().getUrl()));
        return fileContent;
    }


}


