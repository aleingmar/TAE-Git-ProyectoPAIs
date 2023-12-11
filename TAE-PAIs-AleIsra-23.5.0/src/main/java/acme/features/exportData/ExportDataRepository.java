
package acme.features.exportData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.roles.Medico;

@Repository
public interface ExportDataRepository extends CrudRepository<Medico, Long> {

	// Puedes agregar métodos personalizados de consulta si es necesario
}
