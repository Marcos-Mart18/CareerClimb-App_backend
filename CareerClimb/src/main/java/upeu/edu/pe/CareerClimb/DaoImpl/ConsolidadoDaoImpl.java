package upeu.edu.pe.CareerClimb.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upeu.edu.pe.CareerClimb.Dao.ConsolidadoDao;
import upeu.edu.pe.CareerClimb.Entity.Consolidado;
import upeu.edu.pe.CareerClimb.Repository.ConsolidadoRepository;

@Component
public class ConsolidadoDaoImpl implements ConsolidadoDao {
    @Autowired
    private ConsolidadoRepository consolidadoRepository;

    @Override
    public Consolidado create(Consolidado consolidado) {
        return consolidadoRepository.save(consolidado);
    }

    @Override
    public Consolidado update(Consolidado consolidado) {
        return consolidadoRepository.save(consolidado);
    }

    @Override
    public void delete(Long id) {
        consolidadoRepository.deleteById(id);
    }

    @Override
    public Optional<Consolidado> getById(Long id) {
        return consolidadoRepository.findById(id);
    }

    @Override
    public List<Consolidado> getAll() {
        return consolidadoRepository.findAll();
    }
}
