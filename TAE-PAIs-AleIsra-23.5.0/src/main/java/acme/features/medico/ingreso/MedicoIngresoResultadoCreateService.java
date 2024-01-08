
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
public class MedicoIngresoResultadoCreateService extends AbstractService<Medico, Ingreso> {

	@Autowired
	protected MedicoIngresoRepository repository;


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

	}

	@Override
	public void bind(Ingreso object) {
		assert object != null;

		final int ingresoId = super.getRequest().getData("id", int.class);
		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int medicoId = super.getRequest().getData("medico", int.class);

		final Ingreso ingreso = this.repository.findOneIngresoById(ingresoId);
		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Medico medico = this.repository.findOneMedicoById(medicoId);

		object = ingreso;

		super.bind(object, "fechaValoracion", "resultadoValoracion");

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
		final SelectChoices choicesI;
		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;
		final Collection<Ingreso> ingresos;

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();
		ingresos = this.repository.findAllIngresos();

		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedico());
		choicesI = SelectChoices.from(ingresos, "fechaIngreso", object);

		Tuple tuple;
		tuple = super.unbind(object, "fechaValoracion", "resultadoValoracion");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		tuple.put("pacientes", choicesP);

		tuple.put("medico.dni", choicesM.getSelected().getKey());
		tuple.put("medicos", choicesM);

		tuple.put("fechaIngreso", choicesI.getSelected().getKey());
		tuple.put("ingresos", choicesI);

		//paso todos los pacientes

		super.getResponse().setData(tuple);

	}

}
