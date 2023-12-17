
package acme.features.exportPrueba;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import acme.entities.asistencia.Ingreso;

@Repository
public interface ExportPruebaRepository extends CrudRepository<Ingreso, Long> {

	// Puedes agregar métodos personalizados de consulta si es necesario

	@Query("select i from Ingreso i")
	Collection<Ingreso> findAllIngresos();


}
