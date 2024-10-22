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
@Table(name = "EVALUACION")
public class Evaluacion {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_EVALUACION")
    @SequenceGenerator(name = "SQ_EVALUACION", sequenceName = "SQ_EVALUACION", allocationSize = 1)
	@Column(name = "idevaluacion",columnDefinition = "NUMBER")
	private Long idEvaluacion;
	@Column(name = "nota",columnDefinition = "NUMBER")
	private Long nota;
	@Column(name = "horas",columnDefinition = "VARCHAR2(10)")
	private String horas;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "idppp")
	private PPP ppp;
	
	@ManyToOne
	@JoinColumn(name = "idrubro")
	private Rubro rubro;
}
