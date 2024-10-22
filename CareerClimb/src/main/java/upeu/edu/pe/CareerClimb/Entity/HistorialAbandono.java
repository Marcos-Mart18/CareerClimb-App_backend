package upeu.edu.pe.CareerClimb.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "HISTORIAL_ABANDONO")
public class HistorialAbandono {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORIAL_ABANDONO")
    @SequenceGenerator(name = "SQ_HISTORIAL_ABANDONO", sequenceName = "SQ_HISTORIAL_ABANDONO", allocationSize = 1)
	@Column(name = "idhistorial_abandono",columnDefinition = "NUMBER")
	private Long idHistorialAbandono;
	@Column(name = "fecha",columnDefinition = "DATE")
	private String fecha;
	@Column(name = "nombre",columnDefinition = "varchar(40)")
	private String nombre;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@OneToOne
	@JoinColumn(name = "idperfil_usuario")
	private PerfilUsuario perfilUsuario;
}
