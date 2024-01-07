
package acme.features.medico.ingreso;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class MedicoIngresoUpdateService extends AbstractService<Medico, Ingreso> {

	@Autowired
	protected MedicoIngresoRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Ingreso object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneIngresoById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Ingreso object) {
		assert object != null;

		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int medicoId = super.getRequest().getData("medico", int.class);

		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Medico medico = this.repository.findOneMedicoById(medicoId);

		super.bind(object, "fechaAlta", "motivoAlta");

		object.setPaciente(paciente);
		object.setMedico(medico);

	}

	@Override
	public void validate(final Ingreso object) {
		assert object != null;

	}

	@Override
	public void perform(final Ingreso object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Ingreso object) {
		assert object != null;

		SelectChoices choicesP, choicesM;
		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;

		System.out.println("unbind se ejecuta");

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();

		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedico());

		Tuple tuple;
		tuple = super.unbind(object, "fechaAlta", "motivoAlta");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		tuple.put("pacientes", choicesP);

		tuple.put("medico.dni", choicesM.getSelected().getKey());
		tuple.put("medicos", choicesM);

		//paso todos los pacientes

		super.getResponse().setData(tuple);

	}

}
