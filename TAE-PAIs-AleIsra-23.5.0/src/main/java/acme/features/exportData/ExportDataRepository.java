
package acme.features.exportData;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.entities.asistencia.Ingreso;
import acme.roles.Medico;

@Repository
public interface ExportDataRepository extends CrudRepository<Ingreso, Long> {

	// Puedes agregar métodos personalizados de consulta si es necesario

	@Query("select i from Ingreso i")
	Collection<Ingreso> findAllIngresos();

	@Query("select m from Medico m")
	Collection<Medico> findAllMedicos();
}
