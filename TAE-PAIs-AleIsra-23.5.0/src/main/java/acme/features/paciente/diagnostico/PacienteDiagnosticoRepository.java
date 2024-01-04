
package acme.features.paciente.diagnostico;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cuidados.Diagnostico;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Paciente;

@Repository
public interface PacienteDiagnosticoRepository extends AbstractRepository {

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

}
