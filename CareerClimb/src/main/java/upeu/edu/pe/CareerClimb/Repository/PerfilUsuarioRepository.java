package upeu.edu.pe.CareerClimb.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upeu.edu.pe.CareerClimb.Entity.PerfilUsuario;
@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {
	 Optional<PerfilUsuario> findByUsuarioId(Long usuarioId);
}
