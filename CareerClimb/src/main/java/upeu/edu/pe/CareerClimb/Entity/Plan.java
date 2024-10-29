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
@Table(name = "PROGRAMA")
public class Plan {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROGRAMA")
    @SequenceGenerator(name = "SQ_PROGRAMA", sequenceName = "SQ_PROGRAMA", allocationSize = 1)
	@Column(name = "idprograma",columnDefinition = "NUMBER")
	private Long idPrograma;
	@Column(name = "plan",columnDefinition = "varchar2(15)")
	private String plan;
	@Column(name = "is_active",columnDefinition = "char(1)")
	private char isActive='A';

	
	@OneToMany(mappedBy = "plan")
	@JsonIgnore
	private List<PlanCarrera>planCarreras;
}
