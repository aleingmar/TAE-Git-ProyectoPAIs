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

package acme.features.medico.ingreso;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import acme.entities.asistencia.Ingreso;
import acme.framework.controllers.AbstractController;
import acme.roles.Medico;

@Controller
public class MedicoIngresoController extends AbstractController<Medico, Ingreso> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected MedicoIngresoListService		listService;

	@Autowired
	protected MedicoIngresoShowService		showService;

	@Autowired
	protected MedicoIngresoListMineService	listMineService;

	@Autowired
	protected MedicoIngresoListAltasService	listAltasService;

	@Autowired
	protected MedicoIngresoUpdateService	updateService;

	//	@Autowired
	//	protected MedicoIngresoDeleteService	deleteService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("update", this.updateService);
		//super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("list-mine-ingresos", "list", this.listMineService);
		super.addCustomCommand("list-mine-altas", "list", this.listMineService);

		super.addCustomCommand("list-ingresos", "list", this.listService);
		super.addCustomCommand("list-altas", "list", this.listService);

		super.addCustomCommand("list-altas-inicial", "list", this.listAltasService);
	}

	@GetMapping("/medico/ingreso/list-altas")
	public String listAltas(@RequestParam("pacienteId") final Integer pacienteId, final Model model) {
		final Collection<Ingreso> ingresos = this.listService.findManyIngresosByPacienteId(pacienteId);
		model.addAttribute("altas", ingresos);
		model.addAttribute("tipoLista", "altas");
		return "medico/ingreso/list";
	}

	@GetMapping("/medico/ingreso/list-valoraciones")
	public String listValoraciones(@RequestParam("pacienteId") final Integer pacienteId, final Model model) {
		final Collection<Ingreso> ingresos = this.listService.findManyIngresosByPacienteId(pacienteId);
		model.addAttribute("valoraciones", ingresos);
		model.addAttribute("tipoLista", "valoraciones");
		return "medico/ingreso/list";
	}

}
