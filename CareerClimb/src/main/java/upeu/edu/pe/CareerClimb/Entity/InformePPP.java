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
@Table(name = "INFORME_PPP")
public class InformePPP {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INFORME_PPP")
    @SequenceGenerator(name = "SQ_INFORME_PPP", sequenceName = "SQ_INFORME_PPP", allocationSize = 1)
	@Column(name = "idinforme_ppp",columnDefinition = "NUMBER")
	private Long idInformePPP;
	@Column(name = "objetivo_general",columnDefinition = "varchar2(200)")
	private String objetivoGeneral;
	@Column(name = "objetivo_epecifico",columnDefinition = "varchar2(300)")
	private String objetivoEspecifico;
	@Column(name = "resumen",columnDefinition = "varchar2(500)")
	private String resumen;
	@Column(name = "logro",columnDefinition = "varchar2(500)")
	private String logro;
	@Column(name = "deficiencia_alumno",columnDefinition = "varchar2(500)")
	private String deficienciaAlumno;
	@Column(name = "deficiencia_empresa",columnDefinition = "varchar2(500)")
	private String deficienciaEmpresa;
	@Column(name = "recomendacion_empresa",columnDefinition = "varchar2(500)")
	private String recomendacionEmpresa;
	@Column(name = "recomendacion_ep",columnDefinition = "varchar2(500)")
	private String recomendacionEP;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@OneToOne
	@JoinColumn(name = "idppp")
	private PPP ppp;
}
