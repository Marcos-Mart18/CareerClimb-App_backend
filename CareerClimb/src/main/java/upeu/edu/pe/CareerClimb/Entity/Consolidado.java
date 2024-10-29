package upeu.edu.pe.CareerClimb.Entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "CONSOLIDADO")
public class Consolidado {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CONSOLIDADO")
    @SequenceGenerator(name = "SQ_CONSOLIDADO", sequenceName = "SQ_CONSOLIDADO", allocationSize = 1)
	@Column(name = "idconsolidado",columnDefinition = "NUMBER")
	private Long idConsolidado;
	@Column(name = "titulo",columnDefinition = "varchar(40)")
	private String titulo;
	@Column(name = "is_active",columnDefinition = "char(1)")
	private char isActive='A';
	
	@OneToOne(mappedBy = "consolidado")
	@JsonIgnore
	private PPP ppp;
	
	@OneToMany(mappedBy = "consolidado")
	@JsonIgnore
	private List<Seccion>secciones;
}
