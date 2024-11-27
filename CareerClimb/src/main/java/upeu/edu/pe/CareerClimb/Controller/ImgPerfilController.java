package upeu.edu.pe.CareerClimb.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import upeu.edu.pe.CareerClimb.Entity.ImgPerfil;
import upeu.edu.pe.CareerClimb.Repository.PerfilImgRepository;
import upeu.edu.pe.CareerClimb.Service.PerfilImgService;
import upeu.edu.pe.CareerClimb.dto.ResponseMessage;
import upeu.edu.pe.CareerClimb.dto.ResponseImgDTO;

@RestController
@RequestMapping("/api/imgManager")
@CrossOrigin(origins = "http://localhost:4200")
public class ImgPerfilController {

    @Autowired
    private PerfilImgService perfilImgService;
    
    @Autowired
    private PerfilImgRepository imgPerfilRepository;

    private static final List<String> ALLOWED_IMAGE_TYPES = List.of("image/jpeg", "image/png", "image/gif");
    
    @GetMapping("/images/url/{id}")
    public ResponseEntity<ResponseImgDTO> getImageUrl(@PathVariable Long id) {
        try {
            // Verifica si la imagen existe
            ImgPerfil imgPerfil = imgPerfilRepository.findById(id)
                    .orElseThrow(() -> new FileNotFoundException("Imagen no encontrada con el id: " + id));

            // Construye la URL completa
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            String imageUrl = baseUrl + "/api/images/" + imgPerfil.getId();

            // Construye el DTO de respuesta
            ResponseImgDTO response = ResponseImgDTO.builder()
                    .id(imgPerfil.getId())
                    .url(imageUrl)
                    .type(imgPerfil.getType())
                    .size(imgPerfil.getData().length) // Tamaño en bytes
                    .idPerfilUsuario(imgPerfil.getPerfilUsuario().getIdPerfilUsuario()) // ID del usuario asociado
                    .build();

            return ResponseEntity.ok(response);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    
    // Endpoint para subir una o más imágenes
    @PostMapping("/upload")
    public ResponseEntity<List<ResponseMessage>> uploadImages(
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("perfilUsuarioId") Long perfilUsuarioId) {
        List<ResponseMessage> responses = files.stream().map(file -> {
            try {
                // Validar que el archivo sea una imagen permitida
                validateImageFile(file);

                // Guardar la imagen asociada al usuario
                perfilImgService.store(file, perfilUsuarioId);
                return new ResponseMessage("Imagen '" + file.getOriginalFilename() + "' subida exitosamente");
            } catch (IllegalArgumentException e) {
                return new ResponseMessage("Error: " + e.getMessage());
            } catch (IOException e) {
                return new ResponseMessage("Error al subir imagen '" + file.getOriginalFilename() + "': " + e.getMessage());
            }
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    // Validar el tipo de archivo
    private void validateImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType)) {
            throw new IllegalArgumentException(
                    "El archivo '" + file.getOriginalFilename() + "' no es un tipo de imagen válido. Tipos permitidos: " + ALLOWED_IMAGE_TYPES);
        }
    }

    // Endpoint para obtener solo los metadatos de la imagen
    @GetMapping("/images/{id}")
    public ResponseEntity<ResponseImgDTO> getImageMetadata(@PathVariable Long id) throws FileNotFoundException {
        ResponseImgDTO responseImgDTO = perfilImgService.getImageMetadata(id)
                .orElseThrow(() -> new FileNotFoundException("Imagen no encontrada con el id: " + id));

        return ResponseEntity.status(HttpStatus.OK).body(responseImgDTO);
    }

    // Endpoint para obtener el contenido binario de la imagen para visualización o descarga
    @GetMapping("/images/view/{id}")
    public ResponseEntity<Resource> viewImage(@PathVariable Long id) throws FileNotFoundException {
        ImgPerfil img = perfilImgService.getImage(id)
                .orElseThrow(() -> new FileNotFoundException("Imagen no encontrada con el id: " + id));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Cambiar si el tipo es diferente, como PNG
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"image_" + img.getId() + ".jpg\"")
                .body(new ByteArrayResource(img.getData()));
    }

    // Endpoint para listar metadatos de todas las imágenes
    @GetMapping("/images")
    public ResponseEntity<List<ResponseImgDTO>> getListImages() {
        List<ResponseImgDTO> images = perfilImgService.getAllImages();
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }

    // Endpoint para eliminar una imagen por ID
    @DeleteMapping("/images/{id}")
    public ResponseEntity<ResponseMessage> deleteImage(@PathVariable Long id) {
        try {
            perfilImgService.deleteImage(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Imagen eliminada exitosamente"));
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }
    @PutMapping("/images/{id}")
    public ResponseEntity<ResponseMessage> updateImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            perfilImgService.updateImage(id, file);
            return ResponseEntity.ok(new ResponseMessage("Imagen actualizada correctamente."));
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseMessage("Error al actualizar la imagen."));
        }
    }


}
