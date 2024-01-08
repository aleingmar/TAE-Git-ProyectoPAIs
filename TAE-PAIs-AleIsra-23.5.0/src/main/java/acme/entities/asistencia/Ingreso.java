
package acme.entities.asistencia;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFilter;

import acme.entities.enumerados.CentroClinico;
import acme.entities.enumerados.MotivoIngreso;
import acme.entities.enumerados.TipoFaseProceso;
import acme.framework.data.AbstractEntity;
import acme.roles.Administrativo;
import acme.roles.Medico;
import acme.roles.Paciente;
import lombok.Getter;
import lombok.Setter;

// declarar que es una entidad .)
@Entity
//generar automaticamente esos metodos
@Getter
@Setter
//para que persista en la BD como una tabla
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//si dejo esto, el controlador sin filtro no funciona

@JsonFilter("miFiltro")
public class Ingreso extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	// atributo para que el objeto sea serializable

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	/*
	 * Estos atributos los hereda los hereda de abstractEntity
	 *
	 * @Id
	 * //se calculan de forma automatica de forma secuencial
	 *
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE)
	 * protected int id;
	 *
	 *
	 * @Version
	 * protected int version;
	 */

	@NotNull
	protected TipoFaseProceso	faseProceso;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	//@PastOrPresent
	protected Date				fechaIngreso;

	protected MotivoIngreso		motivoIngreso;

	protected CentroClinico		centroIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	protected Date				fechaAlta;

	@Length(max = 500)
	protected String			motivoAlta;

	@Temporal(TemporalType.TIMESTAMP)
	@PastOrPresent
	protected Date				fechaValoracion;

	@Length(max = 500)
	protected String			resultadoValoracion;

	// Relationships ----------------------------------------------------------

	//relacion con la clase ingreso, este atributo (clave ajena en BD)
	//se pone de la clase de objeto con la que sea la relacion

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Paciente			paciente;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Medico			medico;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Administrativo	administrativo;

	@Valid
	@OneToOne(optional = true)
	protected Cita				cita;

	//un ingreso inicial se relaciona con N ingresos de proceso, un ingreso de proceso se relaciona con un ingreso inicial
	//un ingreso inicial, tiene el valor de este atributo a el mismo
	//un ingreso de proceso, tiene el valor del ingreso inicial en el que empezó todo el proceso

	//	@Valid
	//	@ManyToOne(optional = true)
	//	protected Ingreso			ingresoInicial;

}
