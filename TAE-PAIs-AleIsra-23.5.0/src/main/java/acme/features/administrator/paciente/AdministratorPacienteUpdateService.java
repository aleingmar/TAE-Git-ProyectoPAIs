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

package acme.features.administrator.paciente;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Administrator;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Paciente;

@Service
public class AdministratorPacienteUpdateService extends AbstractService<Administrator, Paciente> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorPacienteRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);
		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {
		boolean status;
		int masterId;
		Paciente paciente;
		Administrator administrator;

		masterId = super.getRequest().getData("id", int.class);
		paciente = this.repository.findOnePacienteById(masterId);
		administrator = paciente == null ? null : paciente.getAdministrator();
		status = paciente != null && paciente.isDraftMode() && super.getRequest().getPrincipal().hasRole(administrator);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Paciente object;
		Administrator administrator;
		final Date moment = null;

		administrator = this.repository.findOneAdministratorById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Paciente();
		object.setTelefono("");
		object.setFechaNacimiento(moment);
		object.setDni("");

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Paciente object) {
		assert object != null;

		super.bind(object, "telefono", "fechaNacimiento", "dni");

	}

	@Override
	public void validate(final Paciente object) {
		assert object != null;

	}

	@Override
	public void perform(final Paciente object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Paciente object) {
		assert object != null;
		Tuple tuple;

		tuple = super.unbind(object, "dni", "fechaNacimiento", "telefono");

		super.getResponse().setData(tuple);
	}

}
