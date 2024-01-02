
package com.elitsoft.proyectoCuestionario_backend.repositories;

import java.util.List;

import com.elitsoft.proyectoCuestionario_backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Maeva Martínez
 */
public interface ExamRepository extends JpaRepository<Exam, Long>{
    
    List<Exam> findByCategory(ExamCategory examCategory);
    List<Exam> findByProducts(Product product);
    
}
