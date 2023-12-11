
package acme.features.apiRest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import acme.entities.cuidados.Diagnostico;

@Repository
public interface ApiRestRepository extends CrudRepository<Diagnostico, Long> {

	@Query("select d from Diagnostico d")
	Collection<Diagnostico> findDiagnosticos();
}
