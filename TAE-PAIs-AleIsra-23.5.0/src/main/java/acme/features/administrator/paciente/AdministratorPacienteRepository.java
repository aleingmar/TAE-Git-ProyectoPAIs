
package acme.features.administrator.paciente;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.components.accounts.Administrator;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Paciente;

@Repository
public interface AdministratorPacienteRepository extends AbstractRepository {

	@Query("select p from Paciente p where p.id = :id")
	Paciente findOnePacienteById(int id);

	@Query("select a from Administrator a where a.id = :id")
	Administrator findOneAdministratorById(int id);
	
	@Query("select p from Paciente p ")
	Collection<Paciente> findPacientes();

}
