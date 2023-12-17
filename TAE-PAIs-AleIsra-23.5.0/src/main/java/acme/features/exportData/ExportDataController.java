
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

		////Parámetros para definir si el modo de la API es de exclusión o de inclusión: modoAPI=false -> inclusión
		@RequestParam(name = "modoAPI", defaultValue = "true") final boolean modoAPI,
		//Parámetro para manipular la respuesta de la API
		@RequestParam(name = "paciente", defaultValue = "false") final boolean paciente, 
		@RequestParam(name = "Id", defaultValue = "false") final boolean Id, 
		@RequestParam(name = "medico", defaultValue = "false") final boolean medico,
		@RequestParam(name = "centroIngreso", defaultValue = "false") final boolean centroIngreso, 
		@RequestParam(name = "motivoIngreso", defaultValue = "false") final boolean motivoIngreso) throws JsonProcessingException {
		//LLamada al método del servicio que implementa la lógica de la API, pasándole como parámetro los parámetros de la solícitud enviadas por el cliente
		return this.exportDataService.getAllIngresos2(modoAPI, paciente, Id, medico, centroIngreso, motivoIngreso);

	}
}
