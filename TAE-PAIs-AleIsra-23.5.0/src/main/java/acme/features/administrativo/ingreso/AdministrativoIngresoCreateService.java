
package acme.features.administrativo.ingreso;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
public class AdministrativoIngresoCreateService extends AbstractService<Administrativo, Ingreso> {

	@Autowired
	protected AdministrativoIngresoRepository repository;

	//////////////////////////////


	//comprueba si la petición enviada tiene los parámetros necesarios. Para crear una entidad nueva no se necesitan parámetros 
	//por lo que el método debe indicar en la respuesta que todo está correcto y que la petición debe seguir adelante.
	@Override
	public void check() {
		super.getResponse().setChecked(true);
	}

	//que comprueba si el usuario que realizó la petición tiene los permisos para poder realizar dicha petición. En este caso, 
	//cualquier usuario autenticado en el sistema como employer puede crear un job, por lo que este método debe indicar 
	//que la petición está autorizada, 
	//dado que el tipo de rol es comprobado por el framework y que por lo tanto puede seguir adelante.

	@Override
	public void authorise() {
		super.getResponse().setAuthorised(true);
	}

	//que prepara la entidad o entidades necesarias que serán posteriormente añadidas a la respuesta. En este caso, se requiere
	//por un lado, recuperar el employer que está logueado en la aplicación y que ha realizado la petición de crear un nuevo job. 
	//También se necesita crear un nuevo objeto de tipo “Job” vacío, para que sus campos sean cumplimentados a través del formulario
	//por el usuario final. Solo los campos del nuevo job “employer” y “draft” pueden ser actualizados porque no serán proporcionados 
	//por el usuario final a través del formulario que se mostrará en la vista. Finalmente, el objeto de tipo “Job” creado será añadido
	//al buffer.

	@Override
	public void load() {
		Ingreso object;
		Administrativo administrativo;

		administrativo = this.repository.findOneAdministrativoById(super.getRequest().getPrincipal().getActiveRoleId());
		object = new Ingreso();
		//object.setDraftMode(true);
		object.setAdministrativo(administrativo);

		//pongo la info de altas a null
		object.setFechaAlta(null);
		object.setMotivoAlta(null);
		object.setResultadoValoracion(null);
		object.setFechaValoracion(null);
		//object.setCita(null);

		super.getBuffer().setData(object);
	}

	//que recibe la información del formulario y crea un objeto Java para persistir posteriormente en la base de datos. 
	//En este caso, de la petición se recupera el id del contractor, éste se busca en la base de datos y después se fija en el 
	//objeto Java de tipo “Job”. La llamada al método padre “bind” lo que hace es pasar toda la información recibida del formulario 
	//e indicada en la llamada al objeto Java “object”.

	@Override
	public void bind(final Ingreso object) {
		assert object != null;

		//desplegables enumerados
		final CentroClinico centroIngreso = super.getRequest().getData("centroIngreso", CentroClinico.class);
		final MotivoIngreso motivoIngreso = super.getRequest().getData("motivoIngreso", MotivoIngreso.class);
		final TipoFaseProceso faseProceso = super.getRequest().getData("faseProceso", TipoFaseProceso.class);

		final int pacienteId = super.getRequest().getData("paciente", int.class);
		final int medicoId = super.getRequest().getData("medico", int.class);
		final int citaId = super.getRequest().getData("cita", int.class);

		final Paciente paciente = this.repository.findOnePacienteById(pacienteId);
		final Medico medico = this.repository.findOneMedicoById(medicoId);
		final Cita cita = this.repository.findOneCitaById(citaId);

		//super.bind(object, "fechaIngreso");

		//		Date moment;
		//		moment = MomentHelper.getCurrentMoment();
		//@Past or present -> la fecha se quedo en 2022

		final Calendar calendar = Calendar.getInstance();
		final Date currentDate = calendar.getTime();

		System.out.println("fecha" + currentDate);

		object.setFechaIngreso(currentDate);
		object.setPaciente(paciente);
		object.setMedico(medico);
		object.setCentroIngreso(centroIngreso);
		object.setMotivoIngreso(motivoIngreso);
		object.setFaseProceso(faseProceso);
		object.setCita(cita);

	}

	@Override
	public void validate(final Ingreso object) {
		System.out.println(object);
		System.out.println(object.getCentroIngreso());

		assert object != null;
		//hay que pensar las validaciones
		System.out.println("se ejecuta validate");

		//@Past or present -> la fecha se quedo en 2022 -> por versiones de java
		//compruebo aquí que la fecha es presente o pasado
		if (!super.getBuffer().getErrors().hasErrors("fechaIngreso")) {
			final Calendar calendar = Calendar.getInstance();
			final Date currentDate = calendar.getTime();
			super.state(!object.getFechaIngreso().after(currentDate), "fechaIngreso", "La fecha no puede ser futuro");
		}

		if (!super.getBuffer().getErrors().hasErrors("faseProceso")) {
			final Calendar calendar = Calendar.getInstance();
			final Date currentDate = calendar.getTime();
			super.state(!object.getFechaIngreso().after(currentDate), "fechaIngreso", "La fecha no puede ser futuro");

			if ("PROCESO".equals(object.getFaseProceso().name())) {
				final Collection<Ingreso> ingresoInicialPrevio = this.repository.findingresoInicialPrevio(object.getFechaIngreso(), object.getMotivoIngreso(), object.getPaciente());
				super.state(!ingresoInicialPrevio.isEmpty(), "faseProceso", "Debe de existir un ingreso inicial del proceso que sea anterior");
			}
			if ("INICIAL".equals(object.getFaseProceso().name())) {
				final Collection<Ingreso> ingresoInicialPrevio2 = this.repository.findingresoInicialPrevio2(object.getMotivoIngreso(), object.getPaciente());
				super.state(ingresoInicialPrevio2.isEmpty(), "faseProceso", "Ya existe un ingreso inicial relacionado con ese PAI");
			}
		}

	}

	//guarda el ingreso en la bd si todo esta bien (si pasa la validacion)
	@Override
	public void perform(final Ingreso object) {
		System.out.println(object);

		assert object != null;
		System.out.println("perform se ejecuta");
		this.repository.save(object);

		//		final String view = "redirect:/administrativo/ingreso/list";
		//
		//		super.getResponse().setView(view);
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

		System.out.println("El paciente es nulo ?" + object.getPaciente());
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
} //LLAVE FINAL
