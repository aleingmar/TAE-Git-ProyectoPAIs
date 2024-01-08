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

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.accounts.UserAccount;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;
import acme.roles.Paciente;

@Service
public class AdministrativoPacienteShowService extends AbstractService<Administrativo, Paciente> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministrativoPacienteRepository repository;

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
		Paciente Paciente;
		id = super.getRequest().getData("id", int.class);
		Paciente = this.repository.findOnePacienteById(id);

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Paciente object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePacienteById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Paciente object) {
		assert object != null;

		final Collection<UserAccount> cuentas;
		final SelectChoices choicesCuentas;

		//Solo cuentas de usuario sin pacientes asociados

		cuentas = this.repository.findAllCuentas();

		choicesCuentas = SelectChoices.from(cuentas, "username", object.getUserAccount());

		Tuple tuple;

		tuple = super.unbind(object, "telefono", "dni", "fechaNacimiento");

		tuple.put("userAccount.username", choicesCuentas.getSelected().getKey());
		tuple.put("userAccounts", choicesCuentas);

		super.getResponse().setData(tuple);
	}

}
