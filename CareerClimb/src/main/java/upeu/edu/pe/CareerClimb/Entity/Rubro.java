package upeu.edu.pe.CareerClimb.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "RUBRO")
public class Rubro {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RUBRO")
    @SequenceGenerator(name = "SQ_RUBRO", sequenceName = "SQ_RUBRO", allocationSize = 1)
	@Column(name = "idrubro",columnDefinition = "NUMBER")
	private Long idRubro;
	
	@OneToMany(mappedBy = "rubro")
	@JsonIgnore
	private List<DetalleEvaluacion>detalleEvaluaciones;

	@OneToMany(mappedBy = "rubro")
	@JsonIgnore
	private List<Evaluacion>evaluaciones;
}