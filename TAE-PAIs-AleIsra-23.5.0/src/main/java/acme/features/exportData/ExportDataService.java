
package acme.features.exportData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cuidados.Diagnostico;

@Service
public class ExportDataService {

	@Autowired
	private ExportDataRepository exportDataRepository; // Cambia a tu repositorio real


	public List<Diagnostico> getAllData() {
		return (List<Diagnostico>) this.exportDataRepository.findAll(); // Cambia según tus necesidades
	}
}
