package upeu.edu.pe.CareerClimb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upeu.edu.pe.CareerClimb.Entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
    void deleteByUsuario_IdUsuario(Long userId);
}
