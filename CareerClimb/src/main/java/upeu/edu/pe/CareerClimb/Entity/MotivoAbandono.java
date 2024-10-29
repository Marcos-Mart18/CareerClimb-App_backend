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
@Table(name = "MOTIVO_ABANDONO")
public class MotivoAbandono {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_MOTIVO_ABANDONO")
    @SequenceGenerator(name = "SQ_MOTIVO_ABANDONO", sequenceName = "SQ_MOTIVO_ABANDONO", allocationSize = 1)
	@Column(name = "idmotivo_abandono",columnDefinition = "NUMBER")
	private Long idMotivoAbandono;
	@Column(name = "nombre",columnDefinition = "varchar(100)")
	private String nombre;
	@Column(name = "is_active",columnDefinition = "char(1)")
	private char isActive='A';
	
	@ManyToOne
	@JoinColumn(name = "idhistorial_abandono")
	private HistorialAbandono historialAbandono;
}
