package upeu.edu.pe.CareerClimb.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upeu.edu.pe.CareerClimb.Dao.ImgDao;
import upeu.edu.pe.CareerClimb.Entity.Img;
import upeu.edu.pe.CareerClimb.Service.ImgService;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgDao imgDao;

    @Override
    public Img create(Img img) {
        return imgDao.create(img);
    }

    @Override
    public Img update(Img img) {
        return imgDao.update(img);
    }

    @Override
    public void delete(Long id) {
        imgDao.delete(id);
    }

    @Override
    public Optional<Img> getById(Long id) {
        return imgDao.getById(id);
    }

    @Override
    public List<Img> getAll() {
        return imgDao.getAll();
    }
}
