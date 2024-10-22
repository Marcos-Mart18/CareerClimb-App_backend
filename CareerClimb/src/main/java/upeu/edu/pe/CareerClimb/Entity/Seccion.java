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
@Table(name = "SECCION")
public class Seccion {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SECCION")
    @SequenceGenerator(name = "SQ_SECCION", sequenceName = "SQ_SECCION", allocationSize = 1)
	@Column(name = "idseccion",columnDefinition = "NUMBER")
	private Long idSeccion;
	@Column(name = "nombre",columnDefinition = "varchar(40)")
	private String nombre;
	@Column(name = "descripcion",columnDefinition = "varchar(40)")
	private String descripcion;
	@Column(name = "estado",columnDefinition = "char(1)")
	private char estado;
	
	@ManyToOne
	@JoinColumn(name = "idconsolidado")
	private Consolidado consolidado;
	
	
    @OneToMany(mappedBy = "seccion")
    @JsonIgnore
    private List<Seccion> secciones;

    @ManyToOne
    @JoinColumn(name = "idseccion", insertable = false, updatable = false)
    private Seccion seccion;
	
	@OneToMany(mappedBy = "seccion")
	private List<Img>imgs;
}
