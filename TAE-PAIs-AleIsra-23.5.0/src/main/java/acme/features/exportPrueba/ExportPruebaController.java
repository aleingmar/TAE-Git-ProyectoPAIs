
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


	/*
	 * DOCUMENTACIÓN API
	 * modoAPI:
	 * true-> exclusión
	 * false -> inclusión
	 * Modo exclusión:
	 * Atributo=true -> excluirlo
	 * Modo inclusión
	 * atributo=true -> incluirlo
	 * 
	 */
	////////////////////////////////
	@GetMapping("/ingresoPrueba")
	public String exportAllData(

		////modoAPI=false -> inclusión
		@RequestParam(name = "modoAPI", defaultValue = "true") final boolean modoAPI, @RequestParam(name = "Paciente", defaultValue = "false") final boolean Paciente, @RequestParam(name = "Id", defaultValue = "false") final boolean Id)
		throws JsonProcessingException {

		// Crear un ObjectMapper y configurar dinámicamente los filtros
		final ObjectMapper objectMapper = new ObjectMapper();
		final SimpleFilterProvider filterProvider = new SimpleFilterProvider();

		//filterOutAllExcept -> solo muestra los de la url
		//serializeAllExcept -> todos, menos los de la url
		if (modoAPI) {

			filterProvider.addFilter("miFiltro", SimpleBeanPropertyFilter.serializeAllExcept(Id ? "id" : "", Paciente ? "paciente" : ""));
			objectMapper.setFilterProvider(filterProvider);

		} else { //modo exclusión=false -> modo Inclusión

			filterProvider.addFilter("miFiltro", SimpleBeanPropertyFilter.filterOutAllExcept(Id ? "id" : "", Paciente ? "paciente" : ""));
			objectMapper.setFilterProvider(filterProvider);
		}

		// Convertir el objeto a JSON
		final List<Ingreso> ingresos = this.exportDataService.getAllIngresos2();

		return objectMapper.writer(filterProvider).writeValueAsString(ingresos);

	}
}
