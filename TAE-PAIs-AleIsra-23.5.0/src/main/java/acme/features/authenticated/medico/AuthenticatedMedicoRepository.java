/*
 * AuthenticatedMedicoRepository.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.medico;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;
import acme.roles.Medico;

@Repository
public interface AuthenticatedMedicoRepository extends AbstractRepository {

	@Query("select m from Medico m where m.id = :id")
	Medico findOneMedicoById(int id);

	@Query("select m from Medico m")
	Collection<Medico> findMedicos();

}
