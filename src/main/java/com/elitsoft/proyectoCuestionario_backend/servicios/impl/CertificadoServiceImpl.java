
package com.elitsoft.proyectoCuestionario_backend.servicios.impl;

import com.elitsoft.proyectoCuestionario_backend.entidades.Certificado;
import com.elitsoft.proyectoCuestionario_backend.repositorios.CertificadoRepository;
import com.elitsoft.proyectoCuestionario_backend.servicios.CertificadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Maeva Martínez
 */
@Service
public class CertificadoServiceImpl implements CertificadoService {

    private final CertificadoRepository certificadoRepository;

    @Autowired
    public CertificadoServiceImpl(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    @Override
    public List<Certificado> findAll() {

        return certificadoRepository.findAll();
    }

    @Override
    public List<Certificado> findByNombre(String nombre) {
        return certificadoRepository.findByNombre(nombre);
    }


    @Override
    public Boolean guardar_certificado(Certificado certificado){
        certificadoRepository.save(certificado);
        return true;
    }

    @Override
    public Boolean remove_certificado(Long certificado) {
        certificadoRepository.deleteById(certificado);
        return true;

    }


}
