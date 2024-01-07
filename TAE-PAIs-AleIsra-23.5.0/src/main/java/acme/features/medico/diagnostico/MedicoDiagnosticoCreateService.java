
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
public class MedicoDiagnosticoCreateService extends AbstractService<Medico, Diagnostico> {

	@Autowired
	protected MedicoDiagnosticoRepository repository;

	//////////////////////////////


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
		Diagnostico object;

		object = new Diagnostico();

		//pongo la info de altas a null

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
		//hay que pensar las validaciones
		System.out.println("se ejecuta validate");

	}

	//guarda el diagnostico en la bd si todo esta bien (si pasa la validacion)
	@Override
	public void perform(final Diagnostico object) {
		System.out.println(object);

		assert object != null;
		System.out.println("perform se ejecuta");
		this.repository.save(object);

	}

	@Override
	public void unbind(final Diagnostico object) {
		assert object != null;

		System.out.println("unbind se ejecuta");
		SelectChoices choicesPaciente;

		SelectChoices choicesIngreso;

		final Collection<Paciente> pacientes;

		final Collection<Ingreso> ingresos;

		System.out.println("El ingreso es nulo ?" + object.getIngreso());

		pacientes = this.repository.findAllPacientes();

		//te sale solo los ingresos sin diagnostico
		ingresos = this.repository.findAllIngresosSinDiagnostico();

		if (object.getIngreso() != null)
			choicesPaciente = SelectChoices.from(pacientes, "dni", object.getIngreso().getPaciente());
		else
			choicesPaciente = SelectChoices.from(pacientes, "dni", null);

		choicesIngreso = SelectChoices.from(ingresos, "fechaIngreso", object.getIngreso());

		Tuple tuple;

		tuple = super.unbind(object, "fechaDiagnostico", "confirmado", "estadio", "patologia", "detallesDiagnostico");

		super.getResponse().setData(tuple);

		tuple.put("paciente.dni", choicesPaciente.getSelected().getKey());
		tuple.put("pacientes", choicesPaciente);

		tuple.put("ingreso.fechaIngreso", choicesIngreso.getSelected().getKey());
		tuple.put("ingresos", choicesIngreso);

	}

}
