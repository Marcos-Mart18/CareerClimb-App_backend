package upeu.edu.pe.CareerClimb.Service;

import java.util.List;
import java.util.Optional;
import upeu.edu.pe.CareerClimb.Entity.Consolidado;

public interface ConsolidadoService {
    Consolidado create(Consolidado consolidado);
    Consolidado update(Consolidado consolidado);
    void delete(Long id);
    Optional<Consolidado> getById(Long id);
    List<Consolidado> getAll();
}
