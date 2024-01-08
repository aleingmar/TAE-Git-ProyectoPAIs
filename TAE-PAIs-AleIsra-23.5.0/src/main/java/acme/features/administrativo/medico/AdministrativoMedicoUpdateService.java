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

package acme.features.administrativo.medico;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.enumerados.EspecialidadMedica;
import acme.entities.enumerados.TipoMedico;
import acme.framework.components.accounts.UserAccount;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;
import acme.roles.Medico;

@Service
public class AdministrativoMedicoUpdateService extends AbstractService<Administrativo, Medico> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministrativoMedicoRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {

		super.getResponse().setChecked(true);
	}

	@Override
	public void authorise() {

		boolean status;

		status = super.getRequest().getPrincipal().hasRole(Administrativo.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Medico object;

		object = new Medico();

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Medico object) {
		assert object != null;

		final int userAccountId = super.getRequest().getData("userAccount", int.class);

		final TipoMedico tipoMedico = super.getRequest().getData("tipoMedico", TipoMedico.class);

		final EspecialidadMedica especialidad = super.getRequest().getData("especialidad", EspecialidadMedica.class);

		final UserAccount userAccount = this.repository.findOneUserAccountById(userAccountId);

		super.bind(object, "dni");

		object.setUserAccount(userAccount);
		object.setTipoMedico(tipoMedico);
		object.setEspecialidad(especialidad);

	}

	@Override
	public void validate(final Medico object) {
		assert object != null;

	}

	@Override
	public void perform(final Medico object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Medico object) {
		assert object != null;

		final Collection<UserAccount> cuentas;
		SelectChoices choicesCuentas, choicesTipoMedico, choicesEspecialidad;

		//enumerados
		choicesEspecialidad = SelectChoices.from(EspecialidadMedica.class, object.getEspecialidad());
		choicesTipoMedico = SelectChoices.from(TipoMedico.class, object.getTipoMedico());

		//Solo cuentas de usuario sin pacientes ni medicos asociados
		cuentas = this.repository.findAllCuentasSinPacienteNiMedicoAsociado();
		choicesCuentas = SelectChoices.from(cuentas, "username", object.getUserAccount());

		Tuple tuple;

		tuple = super.unbind(object, "dni");

		tuple.put("userAccount.username", choicesCuentas.getSelected().getKey());
		tuple.put("userAccounts", choicesCuentas);

		tuple.put("tipoMedico", choicesTipoMedico.getSelected().getKey());
		tuple.put("tiposMedico", choicesTipoMedico);

		tuple.put("especialidad", choicesEspecialidad.getSelected().getKey());
		tuple.put("especialidades", choicesEspecialidad);

		super.getResponse().setData(tuple);
	}

}
