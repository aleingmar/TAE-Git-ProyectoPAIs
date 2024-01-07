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

package acme.features.medico.diagnostico;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import acme.entities.cuidados.Diagnostico;
import acme.framework.controllers.AbstractController;
import acme.roles.Medico;

@Controller
public class MedicoDiagnosticoController extends AbstractController<Medico, Diagnostico> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected MedicoDiagnosticoListService		listService;

	@Autowired
	protected MedicoDiagnosticoShowService		showService;

	public MedicoDiagnosticoRepository			repository;

	@Autowired
	protected MedicoDiagnosticoCreateService	createService;

	@Autowired
	protected MedicoDiagnosticoUpdateService	updateService;
	//
	@Autowired
	protected MedicoDiagnosticoDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);
	}

	@GetMapping("/medico/diagnostico/list-diagnosticos")
	public String listDiagnosticos(@RequestParam("pacienteId") final Integer pacienteId, final Model model) {
		final Collection<Diagnostico> diagnosticos = this.listService.findManyDiagnosticosByPacienteId(pacienteId);
		model.addAttribute("diagnosticos", diagnosticos);
		return "medico/diagnostico/list";
	}
}
