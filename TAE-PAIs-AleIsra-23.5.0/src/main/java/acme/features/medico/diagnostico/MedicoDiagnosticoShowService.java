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

package acme.features.medico.diagnostico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cuidados.Diagnostico;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;

@Service
public class MedicoDiagnosticoShowService extends AbstractService<Medico, Diagnostico> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoDiagnosticoRepository repository;

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
		Diagnostico Diagnostico;
		id = super.getRequest().getData("id", int.class);
		Diagnostico = this.repository.findOneDiagnosticoById(id);

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Diagnostico object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneDiagnosticoById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Diagnostico object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "ingreso.paciente.userAccount.username", "fechaDiagnostico", "confirmado", "estadio", "patologia", "detallesDiagnostico", "ingreso.medico.userAccount.username");

		super.getResponse().setData(tuple);
	}

}
