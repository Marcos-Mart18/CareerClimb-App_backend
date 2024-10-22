package upeu.edu.pe.CareerClimb.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	@Column(name = "descripcion",columnDefinition = "varchar(40)")
	private String descripcion;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "iddetalle_cargo")
	private DetalleCargo detallecargo;
	
	@OneToMany(mappedBy = "personaDetalleCargo")
	@JsonIgnore
	private List<Persona>personas;
}
