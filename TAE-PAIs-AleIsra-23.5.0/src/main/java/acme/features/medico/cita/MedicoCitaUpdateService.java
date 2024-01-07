
package acme.features.medico.cita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.framework.services.AbstractService;
import acme.roles.Medico;

@Service
public class MedicoCitaUpdateService extends AbstractService<Medico, Cita> {

	@Autowired
	protected MedicoCitaRepository repository;

	//////////////////////////////
}
