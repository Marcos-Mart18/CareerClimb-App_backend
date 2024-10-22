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
@Table(name = "AREA")
public class Area {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AREA")
    @SequenceGenerator(name = "SQ_AREA", sequenceName = "SQ_AREA", allocationSize = 1)
	@Column(name = "idarea",columnDefinition = "NUMBER")
	private Long idArea;
	@Column(name = "nombre",columnDefinition = "varchar(40)")
	private String nombre;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@OneToMany(mappedBy = "area")
	@JsonIgnore
	private List<Actividad>actividades;
	
	@ManyToOne
	@JoinColumn(name = "idlinea")
	private Linea linea;
}
