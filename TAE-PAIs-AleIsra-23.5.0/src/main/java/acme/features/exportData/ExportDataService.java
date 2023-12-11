
package acme.features.exportData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.roles.Medico;

@Service
public class ExportDataService {

	@Autowired
	private ExportDataRepository exportDataRepository; // Cambia a tu repositorio real


	public List<Medico> getAllData() {
		return (List<Medico>) this.exportDataRepository.findAll(); // Cambia según tus necesidades
	}
}
