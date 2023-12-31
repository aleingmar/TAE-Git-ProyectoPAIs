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

package acme.features.medico.ingreso;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class MedicoIngresoShowService extends AbstractService<Medico, Ingreso> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoIngresoRepository repository;

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
		Ingreso Ingreso;
		id = super.getRequest().getData("id", int.class);
		Ingreso = this.repository.findOneIngresoById(id);

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Ingreso object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneIngresoById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Ingreso object) {
		assert object != null;

		SelectChoices choicesP, choicesM;
		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;

		System.out.println("unbind se ejecuta");

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();

		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedico());

		Tuple tuple;
		tuple = super.unbind(object, "fechaAlta", "motivoAlta");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		tuple.put("pacientes", choicesP);

		tuple.put("medico.dni", choicesM.getSelected().getKey());
		tuple.put("medicos", choicesM);

		//paso todos los pacientes

		super.getResponse().setData(tuple);

	}

}
