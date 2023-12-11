
package acme.features.exportPrueba;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.roles.Administrativo;

@Repository
public interface ExportPruebaRepository extends CrudRepository<Administrativo, Long> {

	// Puedes agregar métodos personalizados de consulta si es necesario

	@Query("select a from Administrativo a")
	Collection<Administrativo> findAllAdministrativos();

	@Query("select a from Administrativo a")
	Collection<Object> findAll3();

}
