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
@Table(name = "ACTIVIDAD")
public class Actividad {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACTIVIDAD")
    @SequenceGenerator(name = "SQ_ACTIVIDAD", sequenceName = "SQ_ACTIVIDAD", allocationSize = 1)
	@Column(name = "idactividad",columnDefinition = "NUMBER")
	private Long idActividad;
	@Column(name = "nombre",columnDefinition = "varchar(40)")
	private String nombre;
	@Column(name = "horas",columnDefinition = "varchar(40)")
	private String horas;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "idarea")
	private Area area;
	
	@OneToMany(mappedBy = "actividad")
	@JsonIgnore
	private List<PPP>ppps;

}
