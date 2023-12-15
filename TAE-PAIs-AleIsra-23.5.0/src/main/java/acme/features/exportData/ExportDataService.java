
package acme.features.exportData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import acme.entities.asistencia.Ingreso;

@Service
public class ExportDataService {

	@Autowired
	private ExportDataRepository exportDataRepository; // Cambia a tu repositorio real


	public String getAllIngresos2(final Boolean modoAPI, final Boolean Paciente, final Boolean Id) throws JsonProcessingException {

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

		final List<Ingreso> ingresos = (List<Ingreso>) this.exportDataRepository.findAllIngresos();
		return objectMapper.writer(filterProvider).writeValueAsString(ingresos);

	}

}
