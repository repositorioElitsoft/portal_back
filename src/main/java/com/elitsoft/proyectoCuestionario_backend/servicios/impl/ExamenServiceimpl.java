package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.Config.JWT.TokenUtils;
import com.elitsoft.proyectoCuestionario_backend.entidades.*;
import com.elitsoft.proyectoCuestionario_backend.repositorios.ExamenRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.PreguntaRepository;
import com.elitsoft.proyectoCuestionario_backend.repositorios.UsuarioRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.ExamenService;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.elitsoft.proyectoCuestionario_backend.servicios.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @author Maeva Martínez
 */

@Service
public class ExamenServiceimpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private PreguntaRepository preguntaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Long examenId, Examen examen) {
        Examen examenExistente = examenRepository.findById(examenId)
                .orElseThrow(() -> new NoSuchElementException("El examen con ID " + examenId + " no se encontró."));

        actualizarCampoSiNoNulo(examenExistente::setTitulo, examen.getTitulo());
        actualizarCampoSiNoNulo(examenExistente::setDescripcion, examen.getDescripcion());
        actualizarCampoSiNoNulo(examenExistente::setPuntosMaximos, examen.getPuntosMaximos());
        actualizarCampoSiNoNulo(examenExistente::setNumeroDePreguntas, examen.getNumeroDePreguntas());
        actualizarCampoSiNoNulo(examenExistente::setCategoria, examen.getCategoria());
        actualizarCampoSiNoNulo(examenExistente::setProductos, examen.getProductos());


        if (examen.getPreguntas() != null) {
            Set<Long> remainingIds = new HashSet<>();
            examen.getPreguntas().forEach(pregunta -> {
                if (pregunta.getPreguntaId() != null) {
                    remainingIds.add(pregunta.getPreguntaId());
                }
            });

            if (examenExistente.getPreguntas() != null) {
                examenExistente.getPreguntas().removeIf(preguntaVieja ->
                        preguntaVieja != null && preguntaVieja.getPreguntaId() != null &&
                                !remainingIds.contains(preguntaVieja.getPreguntaId()));
            }
            actualizarCampoSiNoNulo(examenExistente::setPreguntas, examen.getPreguntas());
        }

        return examenRepository.save(examenExistente);
    }

    private <T> void actualizarCampoSiNoNulo(Consumer<T> setter, T valor) {
        if (valor != null) {
            setter.accept(valor);
        }
    }


    @Override
    public List<Examen> obtenerExamenes() {
        return examenRepository.findAll();
    }

    @Override
    public Examen obtenerExamen(Long exam_id) {
        return examenRepository.findById(exam_id).orElse(null);
    }

    @Override
    public void eliminarExamen(Long examenId) {
        examenRepository.deleteById(examenId);
    }

    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesByUser(String jwt) {
        UsernamePasswordAuthenticationToken token = TokenUtils.getAuthentication(jwt);
        if (token == null) {
            return Collections.emptyList();
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsrEmail(token.getPrincipal().toString());

        if (!usuarioOpt.isPresent()) {
            return Collections.emptyList();
        }
        ArrayList<Examen> examenes = new ArrayList<>();

        Set<Producto> productsUsed = new HashSet<>();

        if (usuarioOpt.get().getLaborales() != null) {
            usuarioOpt.get().getLaborales().forEach(laboral -> {
                if (laboral.getHerramientas() != null) {
                    laboral.getHerramientas().forEach(herramienta -> {
                        productsUsed.add(herramienta.getVersionProducto().getPrd());
                    });
                }
            });
        }

        productsUsed.forEach(producto -> {
            List<Examen> examsByProduct = examenRepository.findByProductos(producto);
            examenes.addAll(examsByProduct);
        });

        return new ArrayList<>(examenes);
    }
}