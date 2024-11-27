package upeu.edu.pe.CareerClimb.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseImgDTO {
    private Long id;
    private String url;
    private String type;
    private long size;
    private Long idPerfilUsuario;
}
