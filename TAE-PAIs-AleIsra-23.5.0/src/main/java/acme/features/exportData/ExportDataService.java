
package acme.features.exportData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.roles.Medico;

@Service
public class ExportDataService {

	@Autowired
	private ExportDataRepository exportDataRepository; // Cambia a tu repositorio real


	public List<Ingreso> getAllIngresos() {
		return (List<Ingreso>) this.exportDataRepository.findAllIngresos(); // Cambia según tus necesidades
	}

	public List<Ingreso> getAllIngresos2() {
		final List<Ingreso> ingresos = (List<Ingreso>) this.exportDataRepository.findAllIngresos();
		for (final Ingreso i : ingresos) {
			System.out.println(i.getPaciente().getUserAccount());
			System.out.println(i.getMedico().getUserAccount());
			System.out.println(i.getAdministrativo().getUserAccount());
		}
		return ingresos;

	}

	public List<Medico> getAllMedicos() {
		return (List<Medico>) this.exportDataRepository.findAllMedicos(); // Cambia según tus necesidades
	}

}
