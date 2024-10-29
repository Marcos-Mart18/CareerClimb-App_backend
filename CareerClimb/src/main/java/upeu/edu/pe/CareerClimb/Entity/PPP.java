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
import jakarta.persistence.OneToOne;
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
@Table(name = "PPP")
public class PPP {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PPP")
    @SequenceGenerator(name = "SQ_PPP", sequenceName = "SQ_PPP", allocationSize = 1)
	@Column(name = "idPPP",columnDefinition = "NUMBER")
	private Long idPPP;
	@Column(name = "periodo",columnDefinition = "varchar2(20)")
	private char periodo;
	@Column(name = "estado",columnDefinition = "varchar2(20)")
	private char estado;
	@Column(name = "hora_total",columnDefinition = "number(10,2)")
	private char horaTotal;
	@Column(name = "linea",columnDefinition = "varchar2(30)", nullable = true)
	private char linea;
	@Column(name = "is_active",columnDefinition = "char(1)")
	private char isActive='A';

	@ManyToOne
	@JoinColumn(name = "idsolicitud")
	private Solicitud solicitud;
	
	
	@OneToMany(mappedBy = "ppp")
	@JsonIgnore
	private List<Evaluacion>evaluaciones;
	
	@ManyToOne
	@JoinColumn(name = "idplan_carrera")
	private PlanCarrera planCarrera;

	
	@OneToMany(mappedBy = "ppp")
	@JsonIgnore
	private List<DetallePPP>detallePPPs;
	
	@OneToOne
	@JoinColumn(name = "idconsolidado")
	private Consolidado consolidado;
	
	@OneToMany(mappedBy = "ppp")
	@JsonIgnore
	private List<PPPLinea>pppLineas;
}
