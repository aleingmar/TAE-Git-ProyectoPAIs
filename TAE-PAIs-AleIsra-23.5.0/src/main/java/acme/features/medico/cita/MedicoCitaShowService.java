/*
 * AdministratorMedicoShowService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

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
public class MedicoCitaShowService extends AbstractService<Medico, Cita> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoCitaRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		int id;
		Cita Cita;
		id = super.getRequest().getData("id", int.class);
		Cita = this.repository.findOneHistorialById(id);

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Cita object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneHistorialById(id);

		super.getBuffer().setData(object);
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
