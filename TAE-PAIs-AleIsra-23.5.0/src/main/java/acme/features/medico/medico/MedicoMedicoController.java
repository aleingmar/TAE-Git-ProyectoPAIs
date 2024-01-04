
package acme.features.medico.medico;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.roles.Medico;

@Controller
public class MedicoMedicoController extends AbstractController<Medico, Medico> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected MedicoMedicoListService	listService;

	@Autowired
	protected MedicoMedicoShowService	showService;

	//	@Autowired
	//	protected TipoMedicoService			tipoMedicoService;

	@Autowired
	protected MedicoMedicoRepository	repository;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);

		//super.addCustomCommand("list-a-mi-mismo", "list", this.tipoMedicoService);

	}

	//	@ModelAttribute("currentMedico")
	//	public Medico getCurrentMedico() {
	//		return this.tipoMedicoService.getCurrentMedico();
	//	}

}
