
package acme.features.apiAcme;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Medico;

@Repository
public interface ApiAcmeRepository extends AbstractRepository {

	@Query("select m from Medico m")
	Collection<Medico> findMedicos2();

	//no se usa
	//evitar la carga perezosa (lazy loading) y traer de una vez toda la información necesaria en la consulta original. 
	@Query("select m from Medico m left join fetch m.userAccount ua ")
	Collection<Medico> findMedicos3();

}
