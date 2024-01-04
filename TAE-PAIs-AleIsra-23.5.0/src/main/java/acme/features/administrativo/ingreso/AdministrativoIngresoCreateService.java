
package acme.features.administrativo.ingreso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.entities.enumerados.CentroClinico;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;

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

		super.getBuffer().setData(object);
	}

	//que recibe la información del formulario y crea un objeto Java para persistir posteriormente en la base de datos. 
	//En este caso, de la petición se recupera el id del contractor, éste se busca en la base de datos y después se fija en el 
	//objeto Java de tipo “Job”. La llamada al método padre “bind” lo que hace es pasar toda la información recibida del formulario 
	//e indicada en la llamada al objeto Java “object”.

	@Override
	public void bind(final Administrativo object) {
		assert object != null;

		contractorId = super.getRequest().getData("contractor", int.class);

		//le doy las opciones al desplegable
		object.setCentroIngresoValues(CentroClinico.values());

		super.bind(object, "fechaIngreso", "title", "deadline", "salary", "score", "moreInfo", "description");
		object.setContractor(contractor);
	}

}
