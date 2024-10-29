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
@Table(name = "PERSONA_DETALLE_CARGO")
public class PersonaDetalleCargo {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERSONA_DETALLE_CARGO")
    @SequenceGenerator(name = "SQ_PERSONA_DETALLE_CARGO", sequenceName = "SQ_PERSONA_DETALLE_CARGO", allocationSize = 1)
	@Column(name = "idpersona_detalle_cargo",columnDefinition = "NUMBER")
	private Long idPersonaDetalleCargo;
	
	@ManyToOne
	@JoinColumn(name = "iddetalle_cargo")
	private DetalleCargo detallecargo;
	
	@ManyToOne
	@JoinColumn(name = "idpersona")
	private Persona persona;
	
	
	
}
