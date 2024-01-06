
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

import acme.entities.enumerados.CentroClinico;
import acme.entities.enumerados.TipoCita;
import acme.framework.data.AbstractEntity;
import acme.roles.Medico;
import acme.roles.Paciente;
import lombok.Getter;
import lombok.Setter;

// declarar que es una entidad :)
@Entity
//generar automaticamente esos metodos
@Getter
@Setter
//para que persista en la BD como una tabla
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Cita extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------
	// atributo para que el objeto sea serializable

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@PastOrPresent
	protected Date				fechaCita;

	@NotNull
	protected CentroClinico		centroCita;

	@NotNull
	protected TipoCita			tipoCita;

	@Length(max = 500)
	protected String			indicacionesCita;

	@Length(max = 500)
	protected String			resultadoCita;

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
	protected Medico			medicoTrata;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	protected Medico			medicoOrganiza;

	@Valid
	@OneToOne(optional = true)
	protected Ingreso			ingreso;

}
