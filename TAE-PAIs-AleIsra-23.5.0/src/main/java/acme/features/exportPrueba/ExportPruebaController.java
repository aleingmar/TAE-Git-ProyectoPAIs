
package acme.features.exportPrueba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.roles.Administrativo;

@RestController
@RequestMapping("/api/export")
public class ExportPruebaController {

	@Autowired
	private ExportPruebaService exportDataService; // Asume que tienes un servicio que maneja la lógica de negocio

	//poniendo esto en la clave ajena parece que ha funcionado @JsonIgnore


	@GetMapping("/prueba")
	public List<Administrativo> exportAllData() {
		return this.exportDataService.getAllData(); // Implementa este método en tu servicio
	}
}
