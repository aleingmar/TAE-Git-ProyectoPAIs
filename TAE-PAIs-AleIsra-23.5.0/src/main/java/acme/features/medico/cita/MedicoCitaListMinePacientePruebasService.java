
package acme.features.medico.cita;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;

@Service
public class MedicoCitaListMinePacientePruebasService extends AbstractService<Medico, Cita> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoCitaRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {

		Collection<Cita> objects;
		int id;

		id = super.getRequest().getData("paciente.id", int.class);

		objects = this.repository.findManyCitasByPacienteId(id);

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Cita object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "fechaCita", "centroCita", "tipoCita", "indicacionesCita", "resultadoCita", "paciente.userAccount.username", "medicoOrganiza.userAccount.username", "medicoTrata.userAccount.username", "ingreso.motivoIngreso");

		super.getResponse().setData(tuple);
	}

	public Collection<Cita> findManyCitasByPacienteId(final int pacienteId) {
		return this.repository.findManyCitasByPacienteId(pacienteId);
	}

}
