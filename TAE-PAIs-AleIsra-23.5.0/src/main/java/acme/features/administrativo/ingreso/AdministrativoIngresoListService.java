/*
 * AdministratorMedicoListService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.asistencia.Ingreso;
import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Administrativo;

@Service
public class AdministrativoIngresoListService extends AbstractService<Administrativo, Ingreso> {

	// Internal state ---------------------------------------------------------

	//Se debe de indicar la anotación @Autowired para que Spring se encargue de crear una clase 
	//que implemente el repositorio y a su vez cree un objeto de esa clase y lo inyecte 
	//en las variables definidas.
	@Autowired
	protected AdministrativoIngresoRepository repository;

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

		Collection<Ingreso> objects;

		objects = this.repository.findIngresos();

		super.getBuffer().setData(objects);
	}

	//se encarga de preparar todos los objetos que se van a visualizar en una tupla y de ahí, 
	//se incluyen en la respuesta a la petición. --> **pasa de objeto a texto**
	@Override
	public void unbind(final Ingreso object) {

		//verifica que el objeto no sea nulo
		assert object != null;
		Tuple tuple;
		tuple = super.unbind(object, "fechaIngreso", "faseProceso", "motivoIngreso", "centroIngreso", "fechaValoracion", "resultadoValoracion", "motivoAlta", "fechaAlta", "paciente.userAccount.username", "medico.userAccount.username");

		super.getResponse().setData(tuple);
	}

}
