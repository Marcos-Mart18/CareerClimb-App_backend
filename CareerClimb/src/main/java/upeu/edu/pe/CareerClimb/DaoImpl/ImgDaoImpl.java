package upeu.edu.pe.CareerClimb.DaoImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import upeu.edu.pe.CareerClimb.Dao.ImgDao;
import upeu.edu.pe.CareerClimb.Entity.Img;
import upeu.edu.pe.CareerClimb.Repository.ImgRepository;

@Component
public class ImgDaoImpl implements ImgDao {
    @Autowired
    private ImgRepository imgRepository;

    @Override
    public Img create(Img img) {
        return imgRepository.save(img);
    }

    @Override
    public Img update(Img img) {
        return imgRepository.save(img);
    }

    @Override
    public void delete(Long id) {
        imgRepository.deleteById(id);
    }

    @Override
    public Optional<Img> getById(Long id) {
        return imgRepository.findById(id);
    }

    @Override
    public List<Img> getAll() {
        return imgRepository.findAll();
    }
}
