
package acme.roles;

import javax.persistence.Entity;

import acme.entities.enumerados.EspecialidadMedica;
import acme.entities.enumerados.TipoMedico;
import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

// @Proxy(lazy = false)
@Entity
@Getter
@Setter
public class Medico extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected EspecialidadMedica	especialidad;

	public TipoMedico				tipoMedico;
}
