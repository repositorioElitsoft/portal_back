package com.elitsoft.proyectoCuestionario_backend.repositorios;

import com.elitsoft.proyectoCuestionario_backend.entidades.CatObservacionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatObservacionRepository extends JpaRepository<CatObservacionDTO, Long> {

    @Query("SELECT new com.elitsoft.proyectoCuestionario_backend.entidades.CatObservacionDTO(" +
            "c.cat_obs_id, c.cat_obs_desc, o.obs_id, o.usuario.usr_id, o.apr_ger, o.apr_oper,  o.apr_tec, o.obs_desc, o.obs_fec_cre, o.obs_fec_mod, " +
            "o.usr_id_obs, o.usr_id_obs_mod, " +
            "u1.usr_id, u1.usr_nom, u1.usr_ap_pat, u1.usr_email, " +
            "u2.usr_id, u2.usr_nom, u2.usr_ap_pat, u2.usr_email) " +
            "FROM Observacion o " +
            "LEFT JOIN o.categoriaObservacion c " +
            "LEFT JOIN o.usuario u " +
            "LEFT JOIN Usuario u1 ON o.usr_id_obs = u1.usr_id " +
            "LEFT JOIN Usuario u2 ON o.usr_id_obs_mod = u2.usr_id " +
            "WHERE u.usr_id = :usr_id")
    List<CatObservacionDTO> findCatObservacionUsuarioDetails(@Param("usr_id") Long usr_id);

}
