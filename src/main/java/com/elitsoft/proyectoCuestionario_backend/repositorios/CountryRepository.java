
package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.Country;
import com.elitsoft.proyectoCuestionario_backend.entidades.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Maeva Martínez
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    
    
    public Country getReferenceById(Long id);
    List<Country> findAll();
    //@Query("SELECT c.states FROM Country c WHERE c.id = :countryId")
   // List<State> findStatesByCountryId(@Param("countryId") Long countryId);

    
}
