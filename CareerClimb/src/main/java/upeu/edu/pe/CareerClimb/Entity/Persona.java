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
@Table(name = "PERSONA")
public class Persona {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERSONA")
    @SequenceGenerator(name = "SQ_PERSONA", sequenceName = "SQ_PERSONA", allocationSize = 1)
	@Column(name = "idpersona",columnDefinition = "NUMBER")
	private Long idPersona;
	@Column(name = "nombres",columnDefinition = "varchar(50)")
	private String nombres;
	@Column(name = "apellidos",columnDefinition = "varchar(50)")
	private String apellidos;
	@Column(name = "dni",columnDefinition = "char(8)")
	private char dni;
	@Column(name = "email",columnDefinition = "varchar2(50)")
	private char email;
	@Column(name = "telefono",columnDefinition = "varchar(15)")
	private String telefono;
	@Column(name = "direccion",columnDefinition = "varchar2(150)")
	private String direccion;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "idpersona_detalle_cargo")
	private PersonaDetalleCargo personaDetalleCargo;
	
	@OneToMany(mappedBy = "persona")
	@JsonIgnore
	private List<Usuario>usuarios;
	
	@OneToMany(mappedBy = "persona")
	@JsonIgnore
	private List<Representante>representantes;
}
