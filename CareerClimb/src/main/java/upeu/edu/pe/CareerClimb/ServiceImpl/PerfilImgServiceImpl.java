package upeu.edu.pe.CareerClimb.ServiceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import upeu.edu.pe.CareerClimb.Entity.ImgPerfil;
import upeu.edu.pe.CareerClimb.Entity.PerfilUsuario;
import upeu.edu.pe.CareerClimb.Repository.PerfilImgRepository;
import upeu.edu.pe.CareerClimb.Repository.PerfilUsuarioRepository;
import upeu.edu.pe.CareerClimb.Service.PerfilImgService;
import upeu.edu.pe.CareerClimb.dto.ResponseImgDTO;

@Service
public class PerfilImgServiceImpl implements PerfilImgService {

    @Autowired
    private PerfilImgRepository imgPerfilRepository;

    @Autowired
    private PerfilUsuarioRepository perfilUsuarioRepository;

    @Override
    public ImgPerfil store(MultipartFile file, Long perfilUsuarioId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Busca el PerfilUsuario asociado o lanza una excepción
        PerfilUsuario perfilUsuario = perfilUsuarioRepository.findById(perfilUsuarioId)
                .orElseThrow(() -> new IllegalArgumentException("PerfilUsuario no encontrado con id: " + perfilUsuarioId));

        // Construye y guarda la entidad ImgPerfil
        ImgPerfil imgPerfil = ImgPerfil.builder()
                .data(file.getBytes())
                .perfilUsuario(perfilUsuario) // Relación con PerfilUsuario
                .build();

        return imgPerfilRepository.save(imgPerfil);
    }

    @Override
    public Optional<ImgPerfil> getImage(Long id) throws FileNotFoundException {
        return imgPerfilRepository.findById(id);
    }

    @Override
    public Optional<ResponseImgDTO> getImageMetadata(Long id) throws FileNotFoundException {
        return imgPerfilRepository.findById(id).map(img -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/images/")
                    .path(img.getId().toString())
                    .toUriString();

            return ResponseImgDTO.builder()
                    .id(img.getId())
                    .url(fileDownloadUri)
                    .type("image/png") // Asume el tipo como PNG, se puede cambiar según sea necesario
                    .size(img.getData() != null ? img.getData().length : 0)
                    .idPerfilUsuario(img.getPerfilUsuario() != null ? img.getPerfilUsuario().getIdPerfilUsuario() : null)
                    .build();
        });
    }

    @Override
    public List<ResponseImgDTO> getAllImages() {
        return imgPerfilRepository.findAll().stream().map(img -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/images/")
                    .path(img.getId().toString())
                    .toUriString();

            return ResponseImgDTO.builder()
                    .id(img.getId())
                    .url(fileDownloadUri)
                    .type("image/png") // Cambiar según el tipo real si es necesario
                    .size(img.getData() != null ? img.getData().length : 0)
                    .idPerfilUsuario(img.getPerfilUsuario() != null ? img.getPerfilUsuario().getIdPerfilUsuario() : null)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteImage(Long id) throws FileNotFoundException {
        if (!imgPerfilRepository.existsById(id)) {
            throw new FileNotFoundException("Imagen no encontrada con el id: " + id);
        }
        imgPerfilRepository.deleteById(id);
    }

	@Override
	public ImgPerfil updateImage(Long id, MultipartFile file) throws IOException {
		// Buscar la imagen por su ID
        ImgPerfil imgPerfil = imgPerfilRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("Imagen no encontrada con el id: " + id));

        // Validar que el archivo sea una imagen
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("El archivo proporcionado no es una imagen válida.");
        }

        // Actualizar los datos de la imagen
        imgPerfil.setName(file.getOriginalFilename());
        imgPerfil.setType(contentType);
        imgPerfil.setData(file.getBytes());

        // Guardar los cambios en la base de datos
        return imgPerfilRepository.save(imgPerfil);
	}
}
