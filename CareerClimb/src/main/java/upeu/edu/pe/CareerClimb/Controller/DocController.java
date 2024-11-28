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

import upeu.edu.pe.CareerClimb.Service.DocService;
import upeu.edu.pe.CareerClimb.dto.ResponseDocDTO;
import upeu.edu.pe.CareerClimb.dto.ResponseMessage;

@RestController
@RequestMapping("/api/docManager")
@CrossOrigin(origins = "http://localhost:4200")
public class DocController {

    @Autowired
    private DocService docService;

    @PostMapping("/upload")
    public ResponseEntity<List<ResponseMessage>> uploadFiles(
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam(value = "idDetalleDoc", required = false) Long idDetalleDoc) {
        List<ResponseMessage> responses = files.stream().map(file -> {
            try {
                docService.store(file, idDetalleDoc); // Pasamos el idDetalleDoc (puede ser null)
                return new ResponseMessage("Archivo '" + file.getOriginalFilename() + "' subido exitosamente");
            } catch (IOException e) {
                return new ResponseMessage("Error al subir archivo '" + file.getOriginalFilename() + "': " + e.getMessage());
            }
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    // Endpoint para obtener solo los metadatos del archivo
    @GetMapping("/files/{id}")
    public ResponseEntity<ResponseDocDTO> getFileMetadata(@PathVariable Long id) throws FileNotFoundException {
        ResponseDocDTO responseDocDTO = docService.getDocumentMetadata(id)
                .orElseThrow(() -> new FileNotFoundException("Archivo no encontrado con el id: " + id));

        return ResponseEntity.status(HttpStatus.OK).body(responseDocDTO);
    }

    // Endpoint para obtener el contenido binario del archivo para visualización o descarga
    @GetMapping("/files/view/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable Long id) throws FileNotFoundException {
        byte[] fileData = docService.getDocumentContent(id)
                .orElseThrow(() -> new FileNotFoundException("Archivo no encontrado con el id: " + id));

        ResponseDocDTO metadata = docService.getDocumentMetadata(id)
                .orElseThrow(() -> new FileNotFoundException("Metadatos no encontrados para el id: " + id));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(metadata.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + metadata.getName() + "\"")
                .body(new ByteArrayResource(fileData));
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseDocDTO>> getListFiles() {
        List<ResponseDocDTO> files = docService.getAllDocuments();
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable Long id) {
        try {
            docService.deleteDocument(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Archivo eliminado exitosamente"));
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }
}
