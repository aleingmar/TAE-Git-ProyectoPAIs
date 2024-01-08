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

package acme.features.paciente.cita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Paciente;

@Service
public class PacienteCitaShowService extends AbstractService<Paciente, Cita> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PacienteCitaRepository repository;

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
		Cita = this.repository.findOneCitaById(id);

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
	public void unbind(final Cita object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "fechaCita", "centroCita", "tipoCita", "indicacionesCita", "resultadoCita", "paciente.userAccount.username", "medicoOrganiza.userAccount.username", "medicoTrata.userAccount.username");

		super.getResponse().setData(tuple);
	}

}
