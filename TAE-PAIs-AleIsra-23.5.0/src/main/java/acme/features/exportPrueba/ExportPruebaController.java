
package acme.features.exportPrueba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import acme.entities.asistencia.Ingreso;

@RestController
@RequestMapping("/api/export")
public class ExportPruebaController {

	@Autowired
	private ExportPruebaService exportDataService; // Asume que tienes un servicio que maneja la lógica de negocio

	//poniendo esto en la clave ajena parece que ha funcionado @JsonIgnore


	////////////////////////////////
	@GetMapping("/ingresoPrueba")
	public String exportAllData(@RequestParam(name = "excluirId", defaultValue = "false") final boolean excluirId, @RequestParam(name = "excluirPaciente", defaultValue = "false") final boolean excluirPaciente,
		@RequestParam(name = "contraseña") final String contraseña) throws JsonProcessingException {

		String res = "Contraseña incorrecta, no tiene permiso para usarla";

		if ("TAE".equals(contraseña)) {
			// Crear un ObjectMapper y configurar dinámicamente los filtros

			final ObjectMapper objectMapper = new ObjectMapper();

			final SimpleFilterProvider filterProvider = new SimpleFilterProvider();

			//filterOutAllExcept -> solo muestra los de la url
			//serializeAllExcept -> todos, menos los de la url
			filterProvider.addFilter("miFiltro", SimpleBeanPropertyFilter.serializeAllExcept(excluirId ? "id" : "", excluirPaciente ? "paciente" : ""));

			objectMapper.setFilterProvider(filterProvider);

			// Convertir el objeto a JSON

			final List<Ingreso> ingresos = this.exportDataService.getAllIngresos2();
			res = objectMapper.writer(filterProvider).writeValueAsString(ingresos);
		}
		return res;

	}
}
