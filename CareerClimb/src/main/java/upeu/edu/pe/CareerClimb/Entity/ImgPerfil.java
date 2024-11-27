package upeu.edu.pe.CareerClimb.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "IMG_PERFIL")
public class ImgPerfil {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_IMG_PERFIL")
    @SequenceGenerator(name = "SQ_IMG_PERFIL", sequenceName = "SQ_IMG_PERFIL", allocationSize = 1)
    @Column(name = "idimg_perfil", columnDefinition = "NUMBER")
    private Long id;
    private String name;
    private String type;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "idperfil_usuario", nullable = false)
    private PerfilUsuario perfilUsuario;
}
