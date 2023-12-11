
package acme.features.exportPrueba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.roles.Administrativo;

@Service
public class ExportPruebaService {

	@Autowired
	private ExportPruebaRepository exportPruebaRepository; // Cambia a tu repositorio real


	public List<Administrativo> getAllData() {
		return (List<Administrativo>) this.exportPruebaRepository.findAllAdministrativos(); // Cambia según tus necesidades
	}
}
