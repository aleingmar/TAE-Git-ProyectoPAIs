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

package acme.features.paciente.diagnostico;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.cuidados.Diagnostico;
import acme.framework.controllers.AbstractController;
import acme.roles.Paciente;

@Controller
public class PacienteDiagnosticoController extends AbstractController<Paciente, Diagnostico> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected PacienteDiagnosticoListService		listService;

	@Autowired
	protected PacienteDiagnosticoShowService		showService;

	@Autowired
	protected PacienteDiagnosticoListMineService	listMineService;

	public PacienteDiagnosticoRepository			repository;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);

		super.addCustomCommand("list-mine-diagnosticos", "list", this.listMineService);
	}

}
