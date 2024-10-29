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
@Table(name = "IMG")
public class Img {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_IMG")
    @SequenceGenerator(name = "SQ_IMG", sequenceName = "SQ_IMG", allocationSize = 1)
	@Column(name = "idimg",columnDefinition = "NUMBER")
	private Long idImg;
	@Column(name = "url",columnDefinition = "varchar2(250)")
	private String url;
	@Column(name = "is_active",columnDefinition = "char(1)")
	private char isActive='A';
	
	@ManyToOne
	@JoinColumn(name = "idseccion")
	private Seccion seccion;
}
