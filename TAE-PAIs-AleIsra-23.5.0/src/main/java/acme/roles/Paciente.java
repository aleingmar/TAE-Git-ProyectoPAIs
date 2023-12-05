
package acme.roles;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Paciente extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	// name, surname e email ya estan definido en el AbstractRole
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				fechaNacimiento;

	@NotBlank
	protected String			telefono;

	//@Length(min = 6, max = 12)
	@NotBlank
	protected String			dni;

}
