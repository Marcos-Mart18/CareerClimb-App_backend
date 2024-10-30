package upeu.edu.pe.CareerClimb.Service;

import java.util.List;
import java.util.Optional;
import upeu.edu.pe.CareerClimb.Entity.Seccion;

public interface SeccionService {
    Seccion create(Seccion seccion);
    Seccion update(Seccion seccion);
    void delete(Long id);
    Optional<Seccion> getById(Long id);
    List<Seccion> getAll();
}
