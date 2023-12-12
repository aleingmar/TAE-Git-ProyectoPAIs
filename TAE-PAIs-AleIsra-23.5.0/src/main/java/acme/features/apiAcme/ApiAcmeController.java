
package acme.features.apiAcme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.framework.components.accounts.Anonymous;
import acme.framework.controllers.AbstractController;
import acme.roles.Medico;

@RestController
@RequestMapping("/api/export")
@Controller
public class ApiAcmeController extends AbstractController<Anonymous, Medico> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected ApiAcmeService apiAcmeService;

	// Constructors -----------------------------------------------------------


	@GetMapping("/acme")
	@PostConstruct
	protected void initialise() {
		super.addCustomCommand("api", "list", this.apiAcmeService);

	}

}
