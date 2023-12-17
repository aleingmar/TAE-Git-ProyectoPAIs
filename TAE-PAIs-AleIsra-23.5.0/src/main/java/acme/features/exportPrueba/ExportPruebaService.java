
package acme.features.exportPrueba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import acme.entities.asistencia.Ingreso;


@Service
public class ExportPruebaService {

	@Autowired
	private ExportPruebaRepository exportPruebaRepository; // Cambia a tu repositorio real



	public List<Ingreso> getAllIngresos() {
		return (List<Ingreso>) this.exportPruebaRepository.findAllIngresos(); // Cambia según tus necesidades
	}

	public List<Ingreso> getAllIngresos2() {
		final List<Ingreso> ingresos = (List<Ingreso>) this.exportPruebaRepository.findAllIngresos();
		for (final Ingreso i : ingresos) {
			System.out.println(i.getPaciente().getUserAccount());
			System.out.println(i.getMedico().getUserAccount());
			System.out.println(i.getAdministrativo().getUserAccount());
		}
		return ingresos;

	}


}
