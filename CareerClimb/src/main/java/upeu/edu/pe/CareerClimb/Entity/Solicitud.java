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
@Table(name = "SOLICITUD")
public class Solicitud {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SOLICITUD")
    @SequenceGenerator(name = "SQ_SOLICITUD", sequenceName = "SQ_SOLICITUD", allocationSize = 1)
	@Column(name = "idsolicitud",columnDefinition = "NUMBER")
	private Long idsolicitud;
	@Column(name = "empresa",columnDefinition = "varchar(40)")
	private String empresa;
	@Column(name = "ruc",columnDefinition = "varchar(40)")
	private String ruc;
	@Column(name = "em_email",columnDefinition = "varchar(40)")
	private String emEmail;
	@Column(name = "representante",columnDefinition = "varchar(40)")
	private String representante;
	@Column(name = "re_cargo",columnDefinition = "varchar(40)")
	private String re_cargo;
	@Column(name = "re_email",columnDefinition = "varchar(40)")
	private String re_email;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@OneToMany(mappedBy = "solicitud")
	@JsonIgnore
	private List<PPP>ppps;
	
	@ManyToOne
	@JoinColumn(name = "idmatricula")
	private Matricula matricula;
}
