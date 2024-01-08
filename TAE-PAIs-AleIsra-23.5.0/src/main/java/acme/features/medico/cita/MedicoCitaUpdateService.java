
package acme.features.medico.cita;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
public class MedicoCitaUpdateService extends AbstractService<Medico, Cita> {

	@Autowired
	protected MedicoCitaRepository repository;

	//////////////////////////////


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
		Cita object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneCitaById(id);

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

		super.bind(object, "fechaCita", "indicacionesCita", "resultadoCita", "medicoOrganiza");

		object.setPaciente(paciente);
		object.setMedicoTrata(medicoTrata);
		object.setCentroCita(centroCita);
		object.setTipoCita(tipoCita);

	}

	// Consultas ->  Registrarlas con 24 horas de antelación minimas
	// Cirugias ->  Registrarlas con 4 días de antelación minimas
	// Prueba -> Registrarlas con 6 horas de antelación minimas
	@Override
	public void validate(final Cita object) {

		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("fechaCita")) {
			final Calendar calendarConsulta = Calendar.getInstance();
			calendarConsulta.add(Calendar.HOUR_OF_DAY, 24);
			final Date minDate = calendarConsulta.getTime();
			//
			final Calendar calendarCirugia = Calendar.getInstance();
			calendarCirugia.add(Calendar.HOUR_OF_DAY, 96);
			final Date minDate2 = calendarCirugia.getTime();
			//
			final Calendar calendarPrueba = Calendar.getInstance();
			calendarPrueba.add(Calendar.HOUR_OF_DAY, 6);
			final Date minDate3 = calendarPrueba.getTime();

			if ("CONSULTA".equals(object.getTipoCita().name()))
				super.state(object.getFechaCita().after(minDate), "fechaCita", "Las consultas debe registrarse con 24 horas de antelación minimas");
			if ("CIRUGÍA".equals(object.getTipoCita().name()))
				super.state(object.getFechaCita().after(minDate2), "fechaCita", "Las cirugía debe registrarse con 4 días de antelación minimas");
			if (object.getTipoCita().name().length() >= 7)
				if ("PRUEBA_".equals(object.getTipoCita().name().substring(0, 7)))
					super.state(object.getFechaCita().after(minDate3), "fechaCita", "Las pruebas debe registrarse con 6 horas de antelación minimas");
		}

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

		tuple = super.unbind(object, "fechaCita", "indicacionesCita", "resultadoCita", "medicoOrganiza");

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
