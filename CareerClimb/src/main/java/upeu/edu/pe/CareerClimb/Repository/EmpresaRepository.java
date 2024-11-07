package upeu.edu.pe.CareerClimb.Repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import upeu.edu.pe.CareerClimb.Entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	@Modifying
	@Transactional
	@Query(value = "CALL sp_delete_empresa_by_ruc(:empresaRuc)", nativeQuery = true)
	void deleteEmpresa(@Param("empresaRuc") String empresaRuc);
}
