package upeu.edu.pe.CareerClimb.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import upeu.edu.pe.CareerClimb.Entity.ImgPerfil;
import upeu.edu.pe.CareerClimb.dto.ResponseImgDTO;

public interface PerfilImgService {
    
    // Método para almacenar la imagen
    ImgPerfil store(MultipartFile file, Long perfilUsuarioId) throws IOException;

    // Método para obtener la imagen en formato binario
    Optional<ImgPerfil> getImage(Long id) throws FileNotFoundException;

    // Método para obtener los metadatos de la imagen
    Optional<ResponseImgDTO> getImageMetadata(Long id) throws FileNotFoundException;

    // Método para listar los metadatos de todas las imágenes
    List<ResponseImgDTO> getAllImages();

    // Método para eliminar una imagen
    void deleteImage(Long id) throws FileNotFoundException;
    
    ImgPerfil updateImage(Long id, MultipartFile file) throws IOException;
}
