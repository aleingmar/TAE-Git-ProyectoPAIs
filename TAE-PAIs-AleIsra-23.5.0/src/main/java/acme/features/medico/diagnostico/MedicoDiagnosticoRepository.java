
package acme.features.medico.diagnostico;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.asistencia.Ingreso;
import acme.entities.cuidados.Diagnostico;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Medico;
import acme.roles.Paciente;

@Repository
public interface MedicoDiagnosticoRepository extends AbstractRepository {

	@Query("select d from Diagnostico d where d.id = :id") //Pasa todos los historiales, no solo los del paciente
	Diagnostico findOneDiagnosticoById(int id);

	@Query("select d from Diagnostico d")
	Collection<Diagnostico> findDiagnosticos();

	@Query("select d from Diagnostico d where d.ingreso.paciente.id = :pacienteId")
	Collection<Diagnostico> findManyDiagnosticosByPacienteId(int pacienteId);

	@Query("select p from Paciente p where p.id = :id")
	Paciente findOnePacienteById(int id);

	@Query("select d from Diagnostico d where d.ingreso.medico.id = :medicoId")
	Collection<Diagnostico> findManyDiagnosticosByMedicoId(int medicoId);

	@Query("select p from Paciente p")
	Collection<Paciente> findAllPacientes();

	@Query("select m from Medico m")
	Collection<Medico> findAllMedicos();

	@Query("select m from Ingreso m")
	Collection<Ingreso> findAllIngresos();

	@Query("select a from Medico a where a.id = :id")
	Medico findOneMedicoById(int id);

	@Query("select a from Ingreso a where a.id = :id")
	Ingreso findOneIngresoById(int id);

	@Query("select i from Ingreso i WHERE i.id NOT IN (select d.ingreso.id from Diagnostico d)")
	Collection<Ingreso> findAllIngresosSinDiagnostico();

}
