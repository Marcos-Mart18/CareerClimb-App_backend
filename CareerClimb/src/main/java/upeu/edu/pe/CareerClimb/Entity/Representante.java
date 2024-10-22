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
@Table(name = "REPRESENTANTE")
public class Representante {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REPRESENTANTE")
    @SequenceGenerator(name = "SQ_REPRESENTANTE", sequenceName = "SQ_REPRESENTANTE", allocationSize = 1)
	@Column(name = "idrepresentante",columnDefinition = "NUMBER")
	private Long idRepresentante;
	
	@ManyToOne
	@JoinColumn(name = "idpersona")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "idempresa")
	private Empresa empresa;
	
	
}
