package upeu.edu.pe.CareerClimb.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
@Table(name = "DOC")
public class Doc {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOC")
    @SequenceGenerator(name = "SQ_DOC", sequenceName = "SQ_DOC", allocationSize = 1)
	@Column(name = "iddoc",columnDefinition = "NUMBER")
    private Long id;
    private String nombre;
    private String tipo;

    @Lob
    private byte[] data;


    @Column(name = "fecha_subida", nullable = false, updatable = false)
    private LocalDate fechaSubida;

    @PrePersist
    protected void onCreate() {
        this.fechaSubida = LocalDate.now();
    }
    
    @ManyToOne
    @JoinColumn(name = "iddetalle_doc")
    private DetalleDoc detalleDoc ;
}
