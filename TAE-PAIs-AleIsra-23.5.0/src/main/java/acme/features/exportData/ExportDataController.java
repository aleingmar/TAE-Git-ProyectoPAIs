
package acme.features.exportData;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acme.roles.Medico;

@RestController
@RequestMapping("/api/export")
public class ExportDataController {

	@Autowired
	private ExportDataService exportDataService; // Asume que tienes un servicio que maneja la lógica de negocio


	@GetMapping("/medicos")
	public List<Medico> obtenerMedicos(@RequestParam(name = "excluirEspecialidad", defaultValue = "false") final boolean excluirEspecialidad) {

		final List<Medico> medicos = this.exportDataService.getAllMedicos();

		// Aplicar exclusión de propiedades directamente en la lista
		return medicos.stream().map(medico -> {
			if (excluirEspecialidad)
				medico.setEspecialidad(null);
			return medico;
		}).collect(Collectors.toList());
	}
}
