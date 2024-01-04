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

package acme.features.administrativo.cita;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.asistencia.Cita;
import acme.framework.controllers.AbstractController;
import acme.roles.Administrativo;

@Controller
public class AdministrativoCitaController extends AbstractController<Administrativo, Cita> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected AdministrativoCitaListService	listService;

	@Autowired
	protected AdministrativoCitaShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
