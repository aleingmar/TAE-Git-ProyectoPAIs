
package acme.features.medico.ingreso;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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

		super.bind(object, "motivoAlta");

		final Calendar calendar = Calendar.getInstance();
		final Date currentDate = calendar.getTime();

		//System.out.println("fecha" + currentDate);

		object.setFechaAlta(currentDate);
		object.setPaciente(paciente);
		object.setMedico(medico);

	}

	@Override
	public void validate(final Ingreso object) {
		assert object != null;

		//@Past or present -> la fecha se quedo en 2022 -> por versiones de java
		//compruebo aquí que la fecha es presente o pasado
		if (!super.getBuffer().getErrors().hasErrors("fechaAlta")) {
			final Calendar calendar = Calendar.getInstance();
			final Date currentDate = calendar.getTime();
			super.state(!object.getFechaAlta().after(currentDate), "fechaAlta", "La fecha de alta no puede ser futuro");
			super.state(object.getFechaAlta().after(object.getFechaIngreso()), "fechaAlta", "La fecha de alta debe ser posterior a la del comienzo del PAI");
			super.state("INICIAL".equals(object.getFaseProceso().name().trim()), "fechaAlta", "El ingreso debe de ser inicial de PAI");
		}

		if (!super.getBuffer().getErrors().hasErrors("motivoAlta"))
			super.state("INICIAL".equals(object.getFaseProceso().name().trim()), "motivoAlta", "El ingreso debe de ser inicial de PAI");

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
