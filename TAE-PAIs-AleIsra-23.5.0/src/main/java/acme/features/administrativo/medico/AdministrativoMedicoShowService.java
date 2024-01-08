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
public class AdministrativoMedicoShowService extends AbstractService<Administrativo, Medico> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministrativoMedicoRepository repository;

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

		Collection<UserAccount> cuentas;
		SelectChoices choicesCuentas, choicesTipoMedico, choicesEspecialidad;

		//enumerados
		choicesEspecialidad = SelectChoices.from(EspecialidadMedica.class, object.getEspecialidad());
		choicesTipoMedico = SelectChoices.from(TipoMedico.class, object.getTipoMedico());
		//Solo cuentas de usuario sin pacientes asociados

		cuentas = this.repository.findAllCuentas();

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
