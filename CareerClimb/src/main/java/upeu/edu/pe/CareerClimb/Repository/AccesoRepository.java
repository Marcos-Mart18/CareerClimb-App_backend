package upeu.edu.pe.CareerClimb.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import upeu.edu.pe.CareerClimb.Entity.Acceso;

public interface AccesoRepository extends JpaRepository<Acceso, Long> {
	@Query(value = "SELECT A.* FROM ACCESO A " +
            "JOIN ACCESO_ROL AR ON A.idacceso = AR.idacceso " +
            "JOIN ROL R ON AR.idrol = R.idrol " +
            "WHERE R.nombre = :rol AND A.is_active = 'A'", nativeQuery = true)
	List<Acceso> findAccesosByRol(@Param("rol") String rol);

}
