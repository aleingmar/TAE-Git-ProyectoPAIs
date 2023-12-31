/*
 * AdministratorMedicoController.java
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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.asistencia.Ingreso;
import acme.framework.controllers.AbstractController;
import acme.roles.Paciente;

@Controller
public class PacienteIngresoController extends AbstractController<Paciente, Ingreso> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected PacienteIngresoListService		listService;

	@Autowired
	protected PacienteIngresoShowService		showService;

	@Autowired
	protected PacienteIngresoListMineService	listMineService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);

		super.addCustomCommand("list-mine-ingresos", "list", this.listMineService);
		super.addCustomCommand("list-mine-altas", "list", this.listMineService);
		super.addCustomCommand("list-mine-resultados", "list", this.listMineService);
	}

}
