package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.UserJobApproval;
import com.elitsoft.proyectoCuestionario_backend.entities.UserJobApprovalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJobApprovalRepository extends JpaRepository<UserJobApproval, UserJobApprovalId> {

}
