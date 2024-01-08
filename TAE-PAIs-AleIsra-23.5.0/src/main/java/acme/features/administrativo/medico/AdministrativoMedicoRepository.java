/*
 * AdminisratorMedicoRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrativo.medico;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.components.accounts.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Medico;

@Repository
public interface AdministrativoMedicoRepository extends AbstractRepository {

	@Query("select m from Medico m where m.id = :id")
	Medico findOneMedicoById(int id);

	//no se usa
	@Query("select m.userAccount.username, m.especialidad, m.tipoMedico from Medico m")
	Collection<Medico> findMedicos();

	@Query("select m from Medico m")
	Collection<Medico> findMedicos2();

	//no se usa
	//evitar la carga perezosa (lazy loading) y traer de una vez toda la información necesaria en la consulta original. 
	@Query("select m from Medico m left join fetch m.userAccount ua ")
	Collection<Medico> findMedicos3();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

	@Query("select m from Medico m where m.userAccount.id = :id")
	Medico findOneMedicoByUserAccountId(int id);

	@Query("select ua from UserAccount ua")
	Collection<UserAccount> findAllCuentas();

	@Query("SELECT ua FROM UserAccount ua " + "WHERE ua.id NOT IN (SELECT p.userAccount.id FROM Paciente p) " + "AND ua.id NOT IN (SELECT m.userAccount.id FROM Medico m) " + "AND ua.username NOT LIKE 'consumer1' " + "AND ua.username NOT LIKE 'provider1'"
		+ "AND ua.username NOT LIKE 'consumer2' " + "AND ua.username NOT LIKE 'provider2'" + "AND ua.username NOT LIKE 'anonymous'" + "AND ua.username NOT LIKE 'administrator'" + "AND ua.username NOT LIKE 'administrativo1'")
	Collection<UserAccount> findAllCuentasSinPacienteNiMedicoAsociado();

}
