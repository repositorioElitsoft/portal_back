
package com.elitsoft.proyectoCuestionario_backend.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Maeva Martínez
 */
@Entity
@Table(name = "TBL_CERT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Certificado {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cert_id;
    
    private String cert;

    public Certificado() {
    }

    public Long getCert_id() {
        return cert_id;
    }

    public void setCert_id(Long cert_id) {
        this.cert_id = cert_id;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }

   
    
    
    
}
