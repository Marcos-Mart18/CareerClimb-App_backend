package upeu.edu.pe.CareerClimb.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upeu.edu.pe.CareerClimb.Dao.SeccionDao;
import upeu.edu.pe.CareerClimb.Entity.Seccion;
import upeu.edu.pe.CareerClimb.Repository.SeccionRepository;

@Component
public class SeccionDaoImpl implements SeccionDao {
    @Autowired
    private SeccionRepository seccionRepository;

    @Override
    public Seccion create(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    @Override
    public Seccion update(Seccion seccion) {
        return seccionRepository.save(seccion);
    }

    @Override
    public void delete(Long id) {
        seccionRepository.deleteById(id);
    }

    @Override
    public Optional<Seccion> getById(Long id) {
        return seccionRepository.findById(id);
    }

    @Override
    public List<Seccion> getAll() {
        return seccionRepository.findAll();
    }
}
