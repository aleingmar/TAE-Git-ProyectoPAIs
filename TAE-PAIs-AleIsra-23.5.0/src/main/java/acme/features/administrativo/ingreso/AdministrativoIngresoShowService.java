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

package acme.features.administrativo.ingreso;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.entities.asistencia.Ingreso;
import acme.entities.enumerados.CentroClinico;
import acme.entities.enumerados.MotivoIngreso;
import acme.entities.enumerados.TipoFaseProceso;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class AdministrativoIngresoShowService extends AbstractService<Administrativo, Ingreso> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministrativoIngresoRepository repository;

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

		System.out.println("unbind se ejecuta");

		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;
		final Collection<Cita> citas;
		//		final Collection<TipoFaseProceso> faseProceso;
		//		final Collection<CentroClinico> centroIngreso;
		//		final Collection<MotivoIngreso> motivoIngreso;

		SelectChoices choicesP, choicesM;
		final SelectChoices choicesC;
		SelectChoices choicesFaseProceso, choicesCentroIngreso, choicesMotivoIngreso;
		Tuple tuple;

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();

		//solo las citas que no tengas ingresos asociados ya
		citas = this.repository.findAllCitas();

		System.out.println("El paciente es nulo ?" + object.getPaciente());
		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedico());
		choicesC = SelectChoices.from(citas, "fechaCita", object.getCita());

		//enumerados
		choicesFaseProceso = SelectChoices.from(TipoFaseProceso.class, object.getFaseProceso());
		choicesCentroIngreso = SelectChoices.from(CentroClinico.class, object.getCentroIngreso());
		choicesMotivoIngreso = SelectChoices.from(MotivoIngreso.class, object.getMotivoIngreso());

		tuple = super.unbind(object, "fechaIngreso");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		//tuple.put("paciente.dni", object.getPaciente().getDni());
		tuple.put("pacientes", choicesP);

		tuple.put("medico.dni", choicesM.getSelected().getKey());
		tuple.put("medicos", choicesM);

		tuple.put("cita.fechaCita", choicesC.getSelected().getKey());
		tuple.put("citas", choicesC);

		tuple.put("faseProceso", choicesFaseProceso.getSelected().getKey());
		tuple.put("fasesProceso", choicesFaseProceso);

		tuple.put("centroIngreso", choicesCentroIngreso.getSelected().getKey());
		tuple.put("centrosIngreso", choicesCentroIngreso);

		tuple.put("motivoIngreso", choicesMotivoIngreso.getSelected().getKey());
		tuple.put("motivosIngreso", choicesMotivoIngreso);

		//paso todos los pacientes

		super.getResponse().setData(tuple);

	}
}
