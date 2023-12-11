
package acme.features.exportData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.entities.cuidados.Diagnostico;

@RestController
@RequestMapping("/api/export")
public class ExportDataController {

	@Autowired
	private ExportDataService exportDataService; // Asume que tienes un servicio que maneja la lógica de negocio


	@GetMapping("/all")
	public List<Diagnostico> exportAllData() {
		return this.exportDataService.getAllData(); // Implementa este método en tu servicio
	}
}
