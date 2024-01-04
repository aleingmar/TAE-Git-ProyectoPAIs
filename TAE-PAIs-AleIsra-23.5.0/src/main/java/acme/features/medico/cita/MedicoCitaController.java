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

package acme.features.medico.cita;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import acme.entities.asistencia.Cita;
import acme.framework.controllers.AbstractController;
import acme.roles.Medico;

@Controller
public class MedicoCitaController extends AbstractController<Medico, Cita> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected MedicoCitaListService					listService;

	@Autowired
	protected MedicoCitaShowService					showService;

	@Autowired
	public MedicoCitaListMinePacientePruebasService	listMineService;

	public MedicoCitaRepository						repository;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);

		super.addCustomCommand("list-pruebas", "list", this.listMineService);
	}

	@GetMapping("/medico/cita/list-pruebas")
	public String listPruebas(@RequestParam("pacienteId") final Integer pacienteId, final Model model) {
		System.out.println("pacienteId: " + pacienteId);  // registro de depuración
		final Collection<Cita> citas = this.listMineService.findManyCitasByPacienteId(pacienteId);
		System.out.println("citas: " + citas);  // registro de depuración
		model.addAttribute("citas", citas);
		return "medico/cita/list";
	}
}
