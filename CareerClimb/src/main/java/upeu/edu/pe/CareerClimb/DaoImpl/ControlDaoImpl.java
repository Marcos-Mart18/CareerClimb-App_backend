package upeu.edu.pe.CareerClimb.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upeu.edu.pe.CareerClimb.Dao.ControlDao;
import upeu.edu.pe.CareerClimb.Entity.Control;
import upeu.edu.pe.CareerClimb.Repository.ControlRepository;

@Component
public class ControlDaoImpl implements ControlDao {
    @Autowired
    private ControlRepository controlRepository;

    @Override
    public Control create(Control control) {
        return controlRepository.save(control);
    }

    @Override
    public Control update(Control control) {
        return controlRepository.save(control);
    }

    @Override
    public void delete(Long id) {
        controlRepository.deleteById(id);
    }

    @Override
    public Optional<Control> getById(Long id) {
        return controlRepository.findById(id);
    }

    @Override
    public List<Control> getAll() {
        return controlRepository.findAll();
    }
}
