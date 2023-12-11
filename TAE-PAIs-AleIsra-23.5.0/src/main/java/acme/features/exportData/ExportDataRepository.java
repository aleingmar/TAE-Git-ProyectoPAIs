
package acme.features.exportData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.entities.cuidados.Diagnostico;

@Repository
public interface ExportDataRepository extends CrudRepository<Diagnostico, Long> {

	// Puedes agregar métodos personalizados de consulta si es necesario
}
