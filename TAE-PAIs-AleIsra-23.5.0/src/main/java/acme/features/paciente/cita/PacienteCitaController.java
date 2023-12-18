
package acme.features.paciente.cita;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.asistencia.Cita;
import acme.framework.controllers.AbstractController;
import acme.roles.Paciente;

@Controller
public class PacienteCitaController extends AbstractController<Paciente, Cita> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected PacienteCitaListService	listService;

	@Autowired
	protected PacienteCitaShowService	showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

}
