
package acme.features.exportPrueba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.entities.asistencia.Ingreso;

@RestController
@RequestMapping("/api/export")
public class ExportPruebaController {

	@Autowired
	private ExportPruebaService exportDataService; // Asume que tienes un servicio que maneja la lógica de negocio

	//poniendo esto en la clave ajena parece que ha funcionado @JsonIgnore


	@GetMapping("/ingreso")
	public List<Ingreso> exportAllData() {
		return this.exportDataService.getAllIngresos2(); // Implementa este método en tu servicio
	}
}
