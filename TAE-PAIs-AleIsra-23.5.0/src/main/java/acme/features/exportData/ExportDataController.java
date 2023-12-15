
package acme.features.exportData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/export")
public class ExportDataController {

	@Autowired
	private ExportDataService exportDataService; // Asume que tienes un servicio que maneja la lógica de negocio

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
	@GetMapping("/ingresoData")
	public String exportAllData(

		////modoAPI=false -> inclusión
		@RequestParam(name = "modoAPI", defaultValue = "true") final boolean modoAPI, @RequestParam(name = "Paciente", defaultValue = "false") final boolean Paciente, @RequestParam(name = "Id", defaultValue = "false") final boolean Id)
		throws JsonProcessingException {

		return this.exportDataService.getAllIngresos2(modoAPI, Paciente, Id);

	}
}
