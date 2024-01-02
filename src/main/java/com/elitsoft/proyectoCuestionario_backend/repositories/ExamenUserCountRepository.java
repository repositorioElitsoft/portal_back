package com.elitsoft.proyectoCuestionario_backend.repositories;

import com.elitsoft.proyectoCuestionario_backend.entities.ExamUserCount;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ExamenUserCountRepository extends JpaRepository<ExamUserCount,Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE tbl_exm_usr_count SET count = count + 1 WHERE usr_id = :urs_id AND exam_id = :exam_id",nativeQuery = true)
    void updateExamTriesByUser(@Param("urs_id") Long userId, @Param("exam_id") Long examId);
    Optional<ExamUserCount> findByUserAndExam(User user, Exam exam);

}
