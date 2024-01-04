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

package acme.features.administrativo.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.Principal;
import acme.framework.components.accounts.UserAccount;
import acme.framework.components.models.Tuple;
import acme.framework.controllers.HttpMethod;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;
import acme.roles.Paciente;

@Service
public class AdministrativoPacienteCreateService extends AbstractService<Administrativo, Paciente> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministrativoPacienteRepository repository;

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
		Paciente object;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = super.getRequest().getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		object = new Paciente();
		object.setUserAccount(userAccount);

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

	@Override
	public void onSuccess() {
		if (super.getRequest().getMethod().equals(HttpMethod.POST))
			PrincipalHelper.handleUpdate();
	}

}
