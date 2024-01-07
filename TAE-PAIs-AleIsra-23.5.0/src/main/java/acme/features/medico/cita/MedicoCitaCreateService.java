
package acme.features.medico.cita;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.entities.asistencia.Ingreso;
import acme.entities.enumerados.CentroClinico;
import acme.entities.enumerados.MotivoIngreso;
import acme.entities.enumerados.TipoFaseProceso;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class MedicoCitaCreateService extends AbstractService<Medico, Cita> {

	@Autowired
	protected MedicoCitaRepository repository;

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
		Cita object;
		Medico medico;

		medico = this.repository.findOneMedicoById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Cita();
		//object.setDraftMode(true);
		object.setMedicoOrganiza(medico);

		//pongo la info de altas a null
		object.setIngreso(null);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Ingreso object) {
		assert object != null;

		//desplegables enumerados
		final CentroClinico centroIngreso = super.getRequest().getData("centroIngreso", CentroClinico.class);
		final MotivoIngreso motivoIngreso = super.getRequest().getData("motivoIngreso", MotivoIngreso.class);
		final TipoFaseProceso faseProceso = super.getRequest().getData("faseProceso", TipoFaseProceso.class);

		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int medicoId = super.getRequest().getData("medico", int.class);

		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Medico medico = this.repository.findOneMedicoById(medicoId);

		super.bind(object, "fechaIngreso");

		object.setPaciente(paciente);
		object.setMedico(medico);
		object.setCentroIngreso(centroIngreso);
		object.setMotivoIngreso(motivoIngreso);
		object.setFaseProceso(faseProceso);

	}

	@Override
	public void validate(final Ingreso object) {

		assert object != null;

	}

	@Override
	public void perform(final Ingreso object) {
		System.out.println(object);

		assert object != null;
		System.out.println("perform se ejecuta");
		this.repository.save(object);

	}

	@Override
	public void unbind(final Ingreso object) {
		assert object != null;

		System.out.println("unbind se ejecuta");

		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;

		SelectChoices choicesP, choicesM, choicesFaseProceso, choicesCentroIngreso, choicesMotivoIngreso;
		Tuple tuple;

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();

		System.out.println("El paciente es nulo ?" + object.getPaciente());
		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedico());

		//enumerados
		choicesFaseProceso = SelectChoices.from(TipoFaseProceso.class, object.getFaseProceso());
		choicesCentroIngreso = SelectChoices.from(CentroClinico.class, object.getCentroIngreso());
		choicesMotivoIngreso = SelectChoices.from(MotivoIngreso.class, object.getMotivoIngreso());

		tuple = super.unbind(object, "fechaIngreso");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		//tuple.put("paciente.dni", object.getPaciente().getDni());
		tuple.put("pacientes", choicesP);

		tuple.put("medico.dni", choicesM.getSelected().getKey());
		tuple.put("medicos", choicesM);

		tuple.put("faseProceso", choicesFaseProceso.getSelected().getKey());
		tuple.put("fasesProceso", choicesFaseProceso);

		tuple.put("centroIngreso", choicesCentroIngreso.getSelected().getKey());
		tuple.put("centrosIngreso", choicesCentroIngreso);

		tuple.put("motivoIngreso", choicesMotivoIngreso.getSelected().getKey());
		tuple.put("motivosIngreso", choicesMotivoIngreso);

		//paso todos los pacientes

		super.getResponse().setData(tuple);
	}
}
