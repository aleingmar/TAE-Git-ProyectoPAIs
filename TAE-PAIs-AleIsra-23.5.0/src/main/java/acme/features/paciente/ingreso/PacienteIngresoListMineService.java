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

package acme.features.paciente.ingreso;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.framework.components.accounts.Principal;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Paciente;

@Service
public class PacienteIngresoListMineService extends AbstractService<Paciente, Ingreso> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PacienteIngresoRepository repository;

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
		Collection<Ingreso> objects;
		Principal principal;

		principal = super.getRequest().getPrincipal();
		objects = this.repository.findManyIngresosByPacienteId(principal.getActiveRoleId());

		super.getBuffer().setData(objects);
	}

	@Override
	public void unbind(final Ingreso object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "fechaIngreso", "faseProceso", "motivoIngreso", "centroIngreso", "fechaValoracion", "resultadoValoracion", "motivoAlta", "fechaAlta", "paciente.userAccount.username", "medico.userAccount.username");

		super.getResponse().setData(tuple);
	}

}
