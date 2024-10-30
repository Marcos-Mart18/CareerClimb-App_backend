package upeu.edu.pe.CareerClimb.Dao;

import java.util.List;
import java.util.Optional;
import upeu.edu.pe.CareerClimb.Entity.Consolidado;

public interface ConsolidadoDao {
    Consolidado create(Consolidado consolidado);
    Consolidado update(Consolidado consolidado);
    void delete(Long id);
    Optional<Consolidado> getById(Long id);
    List<Consolidado> getAll();
}
