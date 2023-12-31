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

package acme.features.medico.medico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;

@Service
public class MedicoMedicoShowService extends AbstractService<Medico, Medico> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoMedicoRepository repository;

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
		Medico Medico;
		id = super.getRequest().getData("id", int.class);
		Medico = this.repository.findOneMedicoById(id);

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Medico object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneMedicoById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Medico object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "userAccount.username", "userAccount.identity.email", "tipoMedico", "especialidad");

		super.getResponse().setData(tuple);
	}

}
