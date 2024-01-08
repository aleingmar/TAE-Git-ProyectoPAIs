
package acme.features.administrativo.paciente;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.components.accounts.Administrator;
import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Paciente;

@Repository
public interface AdministrativoPacienteRepository extends AbstractRepository {

	@Query("select p from Paciente p where p.id = :id")
	Paciente findOnePacienteById(int id);

	@Query("select a from Administrator a where a.id = :id")
	Administrator findOneAdministratorById(int id);

	@Query("select p from Paciente p ")
	Collection<Paciente> findPacientes();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select p from Paciente p where p.userAccount.id = :id")
	Paciente findOnePacienteByUserAccountId(int id);

	@Query("select ua from UserAccount ua")
	Collection<UserAccount> findAllCuentas();

	@Query("SELECT ua FROM UserAccount ua " + "WHERE ua.id NOT IN (SELECT p.userAccount.id FROM Paciente p) " + "AND ua.id NOT IN (SELECT m.userAccount.id FROM Medico m) " + "AND ua.username NOT LIKE 'consumer1' " + "AND ua.username NOT LIKE 'provider1'"
		+ "AND ua.username NOT LIKE 'consumer2' " + "AND ua.username NOT LIKE 'provider2'" + "AND ua.username NOT LIKE 'anonymous'" + "AND ua.username NOT LIKE 'administrator'" + "AND ua.username NOT LIKE 'administrativo1'")
	Collection<UserAccount> findAllCuentasSinPacienteNiMedicoAsociado();

}
