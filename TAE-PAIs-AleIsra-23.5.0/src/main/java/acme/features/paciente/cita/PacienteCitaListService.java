
package acme.features.paciente.cita;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Cita;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Paciente;

@Service
public class PacienteCitaListService extends AbstractService<Paciente, Cita> {

	// Internal state ---------------------------------------------------------

	//Se debe de indicar la anotación @Autowired para que Spring se encargue de crear una clase 
	//que implemente el repositorio y a su vez cree un objeto de esa clase y lo inyecte 
	//en las variables definidas.
	@Autowired
	protected PacienteCitaRepository repository;

	// AbstractService interface ----------------------------------------------


	//comprueba si se han recibido los parámetros para procesar la petición
	@Override
	public void check() {

		super.getResponse().setChecked(true);
	}

	//comprueba si el usuario que ha realizado la petición tiene permisos para poder llevarla a cabo.
	@Override
	public void authorise() {

		super.getResponse().setAuthorised(true);
	}

	//se encarga de obtener los objetos de la BD que se van a manejar e introducirlos en el buffer
	@Override
	public void load() {

		Collection<Cita> objects;

		objects = this.repository.findCitas();

		super.getBuffer().setData(objects);
	}

	//se encarga de preparar todos los objetos que se van a visualizar en una tupla y de ahí, 
	//se incluyen en la respuesta a la petición. --> **pasa de objeto a texto**
	@Override
	public void unbind(final Cita object) {

		//verifica que el objeto no sea nulo
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "fechaCita", "centroCita", "tipoCita", "indicacionesCita", "resultadoCita", "paciente.userAccount.username", "medicoOrganiza.userAccount.username", "medicoTrata.userAccount.username");

		super.getResponse().setData(tuple);
	}

}
