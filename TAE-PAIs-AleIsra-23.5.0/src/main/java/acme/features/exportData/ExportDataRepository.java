
package acme.features.exportData;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.entities.cuidados.Diagnostico;

@Repository
public interface ExportDataRepository extends CrudRepository<Diagnostico, Long> {

	// Puedes agregar métodos personalizados de consulta si es necesario
	@Query("select d from Diagnostico d")
	Collection<Diagnostico> findAllDiagnosticos();
}
