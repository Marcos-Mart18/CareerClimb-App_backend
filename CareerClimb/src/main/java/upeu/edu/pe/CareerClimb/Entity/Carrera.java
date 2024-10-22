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
@Table(name = "CARRERA")
public class Carrera {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CARRERA")
    @SequenceGenerator(name = "SQ_CARRERA", sequenceName = "SQ_CARRERA", allocationSize = 1)
	@Column(name = "idcarrera",columnDefinition = "NUMBER")
	private Long idCarrera;
	@Column(name = "director_ep",columnDefinition = "varchar(40)")
	private String directorEp;
	@Column(name = "nro_estudiantes",columnDefinition = "NUMBER")
	private Long nroEstudiantes;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;

	
	@OneToMany(mappedBy = "carrera")
	@JsonIgnore
	private List<PlanCarrera>planCarreras;
	
	@ManyToOne
	@JoinColumn(name = "idmatricula")
	private Matricula matricula;
}
