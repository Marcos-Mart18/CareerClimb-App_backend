package upeu.edu.pe.CareerClimb.Entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "DOCUMENTO")
public class DetalleDoc {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DOCUMENTO")
    @SequenceGenerator(name = "SQ_DOCUMENTO", sequenceName = "SQ_DOCUMENTO", allocationSize = 1)
	@Column(name = "iddocumento",columnDefinition = "NUMBER")
	private Long idDocumento;
	@Column(name = "nombre",columnDefinition = "varchar2(40)")
	private String nombre;
	@Column(name = "url",columnDefinition = "VARCHAR2(100)")
	private String url;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "iddetalle_ppp")
	private DetallePPP detallePPP;
}
