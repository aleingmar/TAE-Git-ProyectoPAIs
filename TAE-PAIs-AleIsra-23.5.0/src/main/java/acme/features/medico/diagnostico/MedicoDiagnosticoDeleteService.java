
package acme.features.medico.diagnostico;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.entities.cuidados.Diagnostico;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class MedicoDiagnosticoDeleteService extends AbstractService<Medico, Diagnostico> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoDiagnosticoRepository repository;

	// AbstractService interface ----------------------------------------------


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
		Diagnostico object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneDiagnosticoById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Diagnostico object) {
		assert object != null;
		final Medico medico;
		medico = this.repository.findOneMedicoById(super.getRequest().getPrincipal().getActiveRoleId());

		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int ingresoId = super.getRequest().getData("ingreso", int.class);

		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Ingreso ingreso = this.repository.findOneIngresoById(ingresoId);

		super.bind(object, "fechaDiagnostico", "confirmado", "estadio", "patologia", "detallesDiagnostico");

		object.setIngreso(ingreso);
		object.getIngreso().setPaciente(paciente);
		object.getIngreso().setMedico(medico);

	}

	@Override
	public void validate(final Diagnostico object) {
		assert object != null;

	}

	@Override
	public void perform(final Diagnostico object) {
		assert object != null;

		//no hay nada que depende de el
		this.repository.delete(object);

	}

	@Override
	public void unbind(final Diagnostico object) {
		assert object != null;

		SelectChoices choicesPaciente;

		SelectChoices choicesIngreso;

		final Collection<Paciente> pacientes;

		final Collection<Ingreso> ingresos;

		pacientes = this.repository.findAllPacientes();
		ingresos = this.repository.findAllIngresos();

		choicesPaciente = SelectChoices.from(pacientes, "dni", object.getIngreso().getPaciente());
		choicesIngreso = SelectChoices.from(ingresos, "fechaIngreso", object.getIngreso());

		Tuple tuple;

		tuple = super.unbind(object, "fechaDiagnostico", "confirmado", "estadio", "patologia", "detallesDiagnostico");

		super.getResponse().setData(tuple);

		tuple.put("paciente", choicesPaciente.getSelected().getKey());
		tuple.put("pacientes", choicesPaciente);

		tuple.put("ingreso", choicesIngreso.getSelected().getKey());
		tuple.put("ingresos", choicesIngreso);

	}
}
