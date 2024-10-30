package upeu.edu.pe.CareerClimb.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upeu.edu.pe.CareerClimb.Dao.ControlDao;
import upeu.edu.pe.CareerClimb.Entity.Control;
import upeu.edu.pe.CareerClimb.Service.ControlService;

@Service
public class ControlServiceImpl implements ControlService {
    @Autowired
    private ControlDao controlDao;

    @Override
    public Control create(Control control) {
        return controlDao.create(control);
    }

    @Override
    public Control update(Control control) {
        return controlDao.update(control);
    }

    @Override
    public void delete(Long id) {
        controlDao.delete(id);
    }

    @Override
    public Optional<Control> getById(Long id) {
        return controlDao.getById(id);
    }

    @Override
    public List<Control> getAll() {
        return controlDao.getAll();
    }
}
