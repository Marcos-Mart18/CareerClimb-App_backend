package upeu.edu.pe.CareerClimb.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upeu.edu.pe.CareerClimb.Dao.AccesoDao;
import upeu.edu.pe.CareerClimb.Entity.Acceso;
import upeu.edu.pe.CareerClimb.Service.AccesoService;

@Service
public class AccesoServiceImpl implements AccesoService {
    @Autowired
    private AccesoDao accesoDao;

    @Override
    public Acceso create(Acceso acceso) {
        return accesoDao.create(acceso);
    }

    @Override
    public Acceso update(Acceso acceso) {
        return accesoDao.update(acceso);
    }

    @Override
    public void delete(Long id) {
        accesoDao.delete(id);
    }

    @Override
    public Optional<Acceso> getById(Long id) {
        return accesoDao.getById(id);
    }

    @Override
    public List<Acceso> getAll() {
        return accesoDao.getAll();
    }

	@Override
	public List<Acceso> findAccesosByRol(String rol) {
		// TODO Auto-generated method stub
		return accesoDao.findAccesosByRol(rol);
	}


}
