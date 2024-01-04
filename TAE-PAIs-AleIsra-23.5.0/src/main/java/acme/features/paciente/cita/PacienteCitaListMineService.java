/*
 * EmployerJobListMineService.java
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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Paciente;

@Service
public class PacienteCitaListMineService extends AbstractService<Paciente, Cita> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PacienteCitaRepository repository;

	// AbstractService interface ----------------------------------------------


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
		Collection<Cita> objects;
		Principal principal;

		principal = super.getRequest().getPrincipal();
		objects = this.repository.findManyCitasByPacienteId(principal.getActiveRoleId());

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Cita object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "fechaCita", "centroCita", "tipoCita", "indicacionesCita", "resultadoCita", "paciente.userAccount.username", "medicoOrganiza.userAccount.username", "medicoTrata.userAccount.username", "ingreso.motivoIngreso");

		super.getResponse().setData(tuple);
	}

}
