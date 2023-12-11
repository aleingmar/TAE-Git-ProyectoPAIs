
package acme.features.apiRest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.entities.cuidados.Diagnostico;

@RestController
@RequestMapping("/apirest")
public class ApiRestController {

	@Autowired
	ApiRestService apiRestService;


	@GetMapping()
	public ArrayList<Diagnostico> obtenerDiagnosticos() {
		return this.apiRestService.obtenerDiagnosticos();
	}

}
