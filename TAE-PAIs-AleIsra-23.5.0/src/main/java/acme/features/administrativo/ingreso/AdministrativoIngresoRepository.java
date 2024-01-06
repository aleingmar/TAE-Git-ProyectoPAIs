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

package acme.features.administrativo.ingreso;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.asistencia.Ingreso;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Administrativo;
import acme.roles.Medico;
import acme.roles.Paciente;

@Repository
public interface AdministrativoIngresoRepository extends AbstractRepository {

	@Query("select i from Ingreso i where i.id = :id")
	Ingreso findOneIngresoById(int id);

	@Query("select i from Ingreso i")
	Collection<Ingreso> findIngresos();

	//recupera el administrativo que esta logueado
	@Query("select a from Administrativo a where a.id = :id")
	Administrativo findOneAdministrativoById(int id);

	@Query("select p from Paciente p where p.dni = :dni")
	Paciente findOnePacienteByDni(String dni);

	@Query("select m from Medico m where m.dni = :dni")
	Medico findOneMedicoByDni(String dni);

	@Query("select p from Paciente p")
	Collection<Paciente> findAllPacientes();

	@Query("select m from Medico m")
	Collection<Medico> findAllMedicos();

	@Query("select c from Paciente c where c.id = :pacienteId")
	Paciente findOnePacienteById(int pacienteId);

	@Query("select c from Medico c where c.id = :medicoId")
	Medico findOneMedicoById(int medicoId);

}
