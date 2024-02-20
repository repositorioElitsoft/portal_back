
package com.elitsoft.proyectoCuestionario_backend.services;
import com.elitsoft.proyectoCuestionario_backend.entities.User;
import com.elitsoft.proyectoCuestionario_backend.entities.UserJobAvailability;
import com.elitsoft.proyectoCuestionario_backend.entities.UserPreferredJob;
import com.elitsoft.proyectoCuestionario_backend.entities.dto.VerifyDTO;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Optional;



/**
 *
 * @author Maeva Martínez
 */
@Service
public interface UserService {
    
    public User guardarUsuario(User user, Long Id) throws Exception;

    public User obtenerUsuarioId(Long usr_id)throws Exception ;

    public Boolean verificarUsuario(VerifyDTO body);
    public User getUsuarioByEmail(String email);

    public Optional<User> getUsuarioByToken(String jwt);
    public void pedirRestaurarPassword(User user) throws MessagingException, UnsupportedEncodingException;
    public Boolean cambiarPassword(String code, String password);

    void uploadUserCv(String jwt, MultipartFile cv) throws IOException;

    public Boolean updateUser(User user, String jwt);

    public Resource getCVByUser(Long userId) throws IOException, EntityNotFoundException;

    User actualizarUsuarioId(Long usr_id, User user)throws SQLIntegrityConstraintViolationException;


    User obtenerDatosUsuario(String jwt) throws Exception;

    public  List<User> getAllUsers();

    public List<User> getGuestUsers();

    void eliminarUsuarioId (Long usr_id);

    UserPreferredJob createOrUpdatePreferredJob(UserPreferredJob userPreferredJob, String jwt);

    UserPreferredJob getPreferredJob(String jwt);

    UserJobAvailability updateAvailability(UserJobAvailability userJobAvailability, String jwt);

    void deleteUserCV(String jwt) throws IOException;
}
