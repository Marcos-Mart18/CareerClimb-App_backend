package upeu.edu.pe.CareerClimb.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upeu.edu.pe.CareerClimb.Dao.ConsolidadoDao;
import upeu.edu.pe.CareerClimb.Entity.Consolidado;
import upeu.edu.pe.CareerClimb.Service.ConsolidadoService;

@Service
public class ConsolidadoServiceImpl implements ConsolidadoService {
    @Autowired
    private ConsolidadoDao consolidadoDao;

    @Override
    public Consolidado create(Consolidado consolidado) {
        return consolidadoDao.create(consolidado);
    }

    @Override
    public Consolidado update(Consolidado consolidado) {
        return consolidadoDao.update(consolidado);
    }

    @Override
    public void delete(Long id) {
        consolidadoDao.delete(id);
    }

    @Override
    public Optional<Consolidado> getById(Long id) {
        return consolidadoDao.getById(id);
    }

    @Override
    public List<Consolidado> getAll() {
        return consolidadoDao.getAll();
    }
}
