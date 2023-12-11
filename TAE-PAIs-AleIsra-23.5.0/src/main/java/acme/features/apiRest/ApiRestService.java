
package acme.features.apiRest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.cuidados.Diagnostico;

@Service
public class ApiRestService {

	@Autowired
	ApiRestRepository apiRestRepository;


	public ArrayList<Diagnostico> obtenerDiagnosticos() {
		//castea a tipo Ingreso
		return (ArrayList<Diagnostico>) this.apiRestRepository.findDiagnosticos();
	}

}
