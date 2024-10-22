package upeu.edu.pe.CareerClimb.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "PERFIL_USUARIO")
public class PerfilUsuario {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PERFIL_USUARIO")
    @SequenceGenerator(name = "SQ_PERFIL_USUARIO", sequenceName = "SQ_PERFIL_USUARIO", allocationSize = 1)
	@Column(name = "idperfil_usuario",columnDefinition = "NUMBER")
	private Long idPerfilUsuario;
	@Column(name = "nombre",columnDefinition = "VARCHAR2(50)")
	private String nombre;
	@Column(name = "telefono",columnDefinition = "VARCHAR2(15)")
	private String telefono;
	@Column(name = "nacionalidad",columnDefinition = "VARCHAR2(15)")
	private String nacionalidad;
	@Column(name = "email",columnDefinition = "VARCHAR2(60)")
	private String email;
	@Column(name = "area",columnDefinition = "VARCHAR2(40)")
	private String area;
	@Column(name = "about_me",columnDefinition = "VARCHAR2(100)")
	private String aboutMe;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "idusario")
	private Usuario usuario;
	
	@OneToOne(mappedBy = "perfilUsuario")
	@JsonIgnore
	private HistorialAbandono historialAbandono;
}
