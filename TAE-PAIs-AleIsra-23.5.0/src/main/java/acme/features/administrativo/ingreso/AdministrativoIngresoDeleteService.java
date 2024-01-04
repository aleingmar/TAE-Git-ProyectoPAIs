
package acme.features.administrativo.ingreso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;

@Service
public class AdministrativoIngresoDeleteService extends AbstractService<Administrativo, Ingreso> {

	@Autowired
	protected AdministrativoIngresoRepository repository;

}
