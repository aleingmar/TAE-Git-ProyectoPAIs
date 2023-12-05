
package acme.entities.cuidados;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;

import acme.framework.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

// declarar que es una entidad
@Entity
//generar automaticamente esos metodos
@Getter
@Setter
//para que persista en la BD como una tabla
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Diagnostico extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@PastOrPresent
	protected Date				fechaDiagnostico;

	@NotNull
	protected Boolean			confirmado;

	protected int				estadio;

	@NotBlank
	@Length(max = 500)
	protected String			patologia;

	@Length(max = 500)
	protected String			detallesDiagnostico;

	// Relationships ----------------------------------------------------------

	//relacion con la clase ingreso, este atributo (clave ajena en BD)
	//se pone de la clase de objeto con la que sea la relacion

}
