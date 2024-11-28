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

import upeu.edu.pe.CareerClimb.Entity.Doc;
import upeu.edu.pe.CareerClimb.Entity.DetalleDoc;
import upeu.edu.pe.CareerClimb.Repository.DocRepository;
import upeu.edu.pe.CareerClimb.Repository.DetalleDocRepository;
import upeu.edu.pe.CareerClimb.Service.DocService;
import upeu.edu.pe.CareerClimb.dto.ResponseDocDTO;

@Service
public class DocServiceImpl implements DocService {

    @Autowired
    private DocRepository docRepository;

    @Autowired
    private DetalleDocRepository detalleDocRepository;

    @Override
    public void store(MultipartFile file, Long idDetalleDoc) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        DetalleDoc detalleDoc;

        // Verificar si el idDetalleDoc es proporcionado
        if (idDetalleDoc != null) {
            detalleDoc = detalleDocRepository.findById(idDetalleDoc)
                    .orElseThrow(() -> new IllegalArgumentException("DetalleDoc no encontrado con id: " + idDetalleDoc));
        } else {
            // Crear automáticamente un nuevo DetalleDoc si no se proporciona idDetalleDoc
            detalleDoc = new DetalleDoc();
            detalleDoc = detalleDocRepository.save(detalleDoc);
        }

        // Crear y guardar el documento asociado al DetalleDoc
        Doc doc = Doc.builder()
                .nombre(fileName)
                .tipo(file.getContentType())
                .data(file.getBytes())
                .detalleDoc(detalleDoc) // Asociar al DetalleDoc
                .build();

        docRepository.save(doc);
    }

    @Override
    public Optional<byte[]> getDocumentContent(Long id) throws FileNotFoundException {
        return docRepository.findById(id).map(Doc::getData);
    }

    @Override
    public Optional<ResponseDocDTO> getDocumentMetadata(Long id) throws FileNotFoundException {
        return docRepository.findById(id).map(dbDoc -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/docManager/files/")
                    .path(dbDoc.getId().toString())
                    .toUriString();

            return ResponseDocDTO.builder()
                    .id(dbDoc.getId())
                    .name(dbDoc.getNombre())
                    .url(fileDownloadUri)
                    .type(dbDoc.getTipo())
                    .size(dbDoc.getData() != null ? dbDoc.getData().length : 0)
                    .idDetalleDoc(dbDoc.getDetalleDoc() != null ? dbDoc.getDetalleDoc().getIdDetalleDoc() : null)
                    .fechaSubida(dbDoc.getFechaSubida() != null ? dbDoc.getFechaSubida().toString() : null)
                    .build();
        });
    }


    @Override
    public List<ResponseDocDTO> getAllDocuments() {
        return docRepository.findAll().stream().map(dbDoc -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/docManager/files/")
                    .path(dbDoc.getId().toString())
                    .toUriString();

            return ResponseDocDTO.builder()
                    .id(dbDoc.getId())
                    .name(dbDoc.getNombre())
                    .url(fileDownloadUri)
                    .type(dbDoc.getTipo())
                    .size(dbDoc.getData() != null ? dbDoc.getData().length : 0)
                    .idDetalleDoc(dbDoc.getDetalleDoc() != null ? dbDoc.getDetalleDoc().getIdDetalleDoc() : null)
                    .fechaSubida(dbDoc.getFechaSubida() != null ? dbDoc.getFechaSubida().toString() : null)
                    .build();
        }).collect(Collectors.toList());
    }


    @Override
    public void deleteDocument(Long id) throws FileNotFoundException {
        if (!docRepository.existsById(id)) {
            throw new FileNotFoundException("Documento no encontrado con el id: " + id);
        }
        docRepository.deleteById(id);
    }
}
