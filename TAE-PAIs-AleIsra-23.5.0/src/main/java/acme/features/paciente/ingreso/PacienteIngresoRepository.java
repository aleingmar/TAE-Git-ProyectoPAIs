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

package acme.features.paciente.ingreso;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.asistencia.Ingreso;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PacienteIngresoRepository extends AbstractRepository {

	@Query("select i from Ingreso i where i.id = :id")
	Ingreso findOneIngresoById(int id);

	@Query("select i from Ingreso i")
	Collection<Ingreso> findIngresos();

}
