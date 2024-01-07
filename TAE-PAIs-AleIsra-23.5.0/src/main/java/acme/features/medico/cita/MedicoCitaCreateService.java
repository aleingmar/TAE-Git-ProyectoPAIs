
package acme.features.medico.cita;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.entities.enumerados.CentroClinico;
import acme.entities.enumerados.TipoCita;
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

		object.setMedicoOrganiza(medico);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Cita object) {
		assert object != null;

		//desplegables enumerados
		final CentroClinico centroCita = super.getRequest().getData("centroCita", CentroClinico.class);
		final TipoCita tipoCita = super.getRequest().getData("tipoCita", TipoCita.class);

		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int medicoTrataId = super.getRequest().getData("medicoTrata", int.class);

		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Medico medicoTrata = this.repository.findOneMedicoById(medicoTrataId);

		super.bind(object, "fechaCita", "indicacionesCita", "resultadoCita", "medicoOrganiza.userAccount.username");

		object.setPaciente(paciente);
		object.setMedicoTrata(medicoTrata);
		object.setCentroCita(centroCita);
		object.setTipoCita(tipoCita);

	}

	@Override
	public void validate(final Cita object) {

		assert object != null;

	}

	@Override
	public void perform(final Cita object) {
		System.out.println(object);

		assert object != null;
		System.out.println("perform se ejecuta");
		this.repository.save(object);

	}

	@Override
	public void unbind(final Cita object) {
		assert object != null;

		System.out.println("unbind se ejecuta");

		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;

		SelectChoices choicesP, choicesM, choicesCentroCita, choicesTipoCita;
		Tuple tuple;

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();

		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedicoTrata());

		//enumerados
		choicesTipoCita = SelectChoices.from(TipoCita.class, object.getTipoCita());
		choicesCentroCita = SelectChoices.from(CentroClinico.class, object.getCentroCita());

		tuple = super.unbind(object, "fechaCita", "indicacionesCita", "resultadoCita", "medicoOrganiza.userAccount.username");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		tuple.put("pacientes", choicesP);

		tuple.put("medicoTrata", choicesM.getSelected().getKey());
		tuple.put("medicosTrata", choicesM);

		tuple.put("tipoCita", choicesTipoCita.getSelected().getKey());
		tuple.put("tipos", choicesTipoCita);

		tuple.put("centroCita", choicesCentroCita.getSelected().getKey());
		tuple.put("centros", choicesCentroCita);

		//paso todos los pacientes

		super.getResponse().setData(tuple);
	}
}
