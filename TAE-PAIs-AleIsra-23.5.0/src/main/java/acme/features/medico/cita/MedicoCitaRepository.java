
package acme.features.medico.cita;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.asistencia.Cita;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Medico;
import acme.roles.Paciente;

@Repository
public interface MedicoCitaRepository extends AbstractRepository {

	@Query("select c from Cita c where c.id = :id") //Pasa todos los historiales, no solo los del paciente
	Cita findOneHistorialById(int id);

	@Query("select c from Cita c")
	Collection<Cita> findHistoriales();

	@Query("select c from Cita c where c.paciente.id = :pacienteId")
	Collection<Cita> findManyCitasByPacienteId(int pacienteId);

	@Query("select p from Paciente p where p.id = :id")
	Paciente findOnePacienteById(int id);

	@Query("select c from Cita c where c.medicoOrganiza.id = :medicoId")
	Collection<Cita> findManyCitasByMedicoId(int medicoId);

	@Query("select a from Medico a where a.id = :id")
	Medico findOneMedicoById(int id);

}
