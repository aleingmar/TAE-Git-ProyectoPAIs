
package acme.features.administrativo.ingreso;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.entities.asistencia.Ingreso;
import acme.entities.enumerados.CentroClinico;
import acme.entities.enumerados.MotivoIngreso;
import acme.entities.enumerados.TipoFaseProceso;
import acme.framework.components.jsp.SelectChoices;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class AdministrativoIngresoUpdateService extends AbstractService<Administrativo, Ingreso> {

	@Autowired
	protected AdministrativoIngresoRepository repository;


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Ingreso object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneIngresoById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void bind(final Ingreso object) {
		assert object != null;

		//desplegables enumerados
		final CentroClinico centroIngreso = super.getRequest().getData("centroIngreso", CentroClinico.class);
		final MotivoIngreso motivoIngreso = super.getRequest().getData("motivoIngreso", MotivoIngreso.class);
		final TipoFaseProceso faseProceso = super.getRequest().getData("faseProceso", TipoFaseProceso.class);

		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int medicoId = super.getRequest().getData("medico", int.class);

		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Medico medico = this.repository.findOneMedicoById(medicoId);

		//super.bind(object, "fechaIngreso");

		object.setPaciente(paciente);
		object.setMedico(medico);
		object.setCentroIngreso(centroIngreso);
		object.setMotivoIngreso(motivoIngreso);
		object.setFaseProceso(faseProceso);
	}

	@Override
	public void validate(final Ingreso object) {
		assert object != null;

	}

	@Override
	public void perform(final Ingreso object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void unbind(final Ingreso object) {
		assert object != null;

		System.out.println("unbind se ejecuta");

		final Collection<Paciente> pacientes;
		final Collection<Medico> medicos;
		final Collection<Cita> citas;
		//		final Collection<TipoFaseProceso> faseProceso;
		//		final Collection<CentroClinico> centroIngreso;
		//		final Collection<MotivoIngreso> motivoIngreso;

		SelectChoices choicesP, choicesM;
		final SelectChoices choicesC;
		SelectChoices choicesFaseProceso, choicesCentroIngreso, choicesMotivoIngreso;
		Tuple tuple;

		pacientes = this.repository.findAllPacientes();
		medicos = this.repository.findAllMedicos();

		//solo las citas que no tengas ingresos asociados ya
		citas = this.repository.findAllCitasSinIngresos();

		choicesP = SelectChoices.from(pacientes, "dni", object.getPaciente());
		choicesM = SelectChoices.from(medicos, "dni", object.getMedico());
		choicesC = SelectChoices.from(citas, "fechaCita", object.getCita());

		//enumerados
		choicesFaseProceso = SelectChoices.from(TipoFaseProceso.class, object.getFaseProceso());
		choicesCentroIngreso = SelectChoices.from(CentroClinico.class, object.getCentroIngreso());
		choicesMotivoIngreso = SelectChoices.from(MotivoIngreso.class, object.getMotivoIngreso());

		tuple = super.unbind(object, "fechaIngreso");

		//paso el paciente concreto
		tuple.put("paciente.dni", choicesP.getSelected().getKey());
		//tuple.put("paciente.dni", object.getPaciente().getDni());
		tuple.put("pacientes", choicesP);

		tuple.put("medico.dni", choicesM.getSelected().getKey());
		tuple.put("medicos", choicesM);

		tuple.put("cita.fechaCita", choicesC.getSelected().getKey());
		tuple.put("citas", choicesC);

		tuple.put("faseProceso", choicesFaseProceso.getSelected().getKey());
		tuple.put("fasesProceso", choicesFaseProceso);

		tuple.put("centroIngreso", choicesCentroIngreso.getSelected().getKey());
		tuple.put("centrosIngreso", choicesCentroIngreso);

		tuple.put("motivoIngreso", choicesMotivoIngreso.getSelected().getKey());
		tuple.put("motivosIngreso", choicesMotivoIngreso);

		//paso todos los pacientes

		super.getResponse().setData(tuple);

	}
}
