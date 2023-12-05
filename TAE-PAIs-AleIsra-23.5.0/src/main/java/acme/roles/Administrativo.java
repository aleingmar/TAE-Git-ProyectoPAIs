
package acme.roles;

import javax.persistence.Entity;

import acme.entities.enumerados.CentroClinico;
import acme.framework.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Administrativo extends AbstractRole {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected CentroClinico		centroClinico;

}
