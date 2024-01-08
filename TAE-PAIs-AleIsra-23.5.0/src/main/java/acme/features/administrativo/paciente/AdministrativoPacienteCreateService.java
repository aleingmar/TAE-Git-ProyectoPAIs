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

		object = new Paciente();

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Paciente object) {
		assert object != null;

		final int userAccountId = super.getRequest().getData("userAccount", int.class);

		final UserAccount userAccount = this.repository.findOneUserAccountById(userAccountId);

		super.bind(object, "telefono", "fechaNacimiento", "dni");

		object.setUserAccount(userAccount);

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

		final Collection<UserAccount> cuentas;
		final SelectChoices choicesCuentas;

		//Solo cuentas de usuario sin pacientes asociados
		cuentas = this.repository.findAllCuentasSinPacienteNiMedicoAsociado();
		choicesCuentas = SelectChoices.from(cuentas, "username", object.getUserAccount());

		Tuple tuple;

		tuple = super.unbind(object, "telefono", "dni", "fechaNacimiento");

		tuple.put("userAccount.username", choicesCuentas.getSelected().getKey());
		//tuple.put("paciente.dni", object.getPaciente().getDni());
		tuple.put("userAccounts", choicesCuentas);

		super.getResponse().setData(tuple);
	}

}
