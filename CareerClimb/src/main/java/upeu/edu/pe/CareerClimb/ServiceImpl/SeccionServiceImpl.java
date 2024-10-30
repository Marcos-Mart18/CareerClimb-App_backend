package upeu.edu.pe.CareerClimb.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upeu.edu.pe.CareerClimb.Dao.SeccionDao;
import upeu.edu.pe.CareerClimb.Entity.Seccion;
import upeu.edu.pe.CareerClimb.Service.SeccionService;

@Service
public class SeccionServiceImpl implements SeccionService {
    @Autowired
    private SeccionDao seccionDao;

    @Override
    public Seccion create(Seccion seccion) {
        return seccionDao.create(seccion);
    }

    @Override
    public Seccion update(Seccion seccion) {
        return seccionDao.update(seccion);
    }

    @Override
    public void delete(Long id) {
        seccionDao.delete(id);
    }

    @Override
    public Optional<Seccion> getById(Long id) {
        return seccionDao.getById(id);
    }

    @Override
    public List<Seccion> getAll() {
        return seccionDao.getAll();
    }
}
