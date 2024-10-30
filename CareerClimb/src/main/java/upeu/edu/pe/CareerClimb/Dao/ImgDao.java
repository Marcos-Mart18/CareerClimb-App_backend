package upeu.edu.pe.CareerClimb.Dao;

import java.util.List;
import java.util.Optional;
import upeu.edu.pe.CareerClimb.Entity.Img;

public interface ImgDao {
    Img create(Img img);
    Img update(Img img);
    void delete(Long id);
    Optional<Img> getById(Long id);
    List<Img> getAll();
}
