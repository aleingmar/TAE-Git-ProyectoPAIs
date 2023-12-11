
package api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/helloworld")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldRest {

	@GET
	public Response sayHello() {
		return Response.ok("Hello World desde el API REST", MediaType.APPLICATION_JSON).build();
	}

	/*
	 * Como podrás observar, esta es una clase común y corriente pero que tiene algunas anotaciones, las cuales serán
	 * reconocidas por el servidor de aplicaciones para finalmente exponer el servicio, analicemos para que esta cada una
	 * de ellas.
	 * 
	 * La anotación @Path indica la URL en la cual responderá este servicio, cabe mencionar que esta anotación se puede poner
	 * a nivel de clase y método, en este caso, al estar a nivel de clase, afecta a todos los servicios que definamos, pero eso
	 * lo vamos a analizar más adelante.
	 * 
	 * Las siguientes dos anotaciones son para indicar que tipo de mensaje esperamos como entrada (consumes) y
	 * que tipo de mensaje vamos a responder (produces). En este caso, estamos indicando que esperamos JSON como entrada
	 * y que vamos a responder igualmente con JSON.
	 * 
	 * Finalmente, siguen los métodos, una clase puede tener más de un método, y cada método se puede exponer como un servicio
	 * independiente, sin embargo, en esta primera introducción empezaremos con uno. La anotación @GET le indica al servidor
	 * de aplicaciones que el método responde por el método GET únicamente. Adicional tenemos anotaciones para los demás
	 * métodos, como @POST, @PUT, @DELETE, etc. pero estos los estaremos analizando más adelante.
	 * 
	 * Podrás observar que el método responde con un tipo llamado Response, esta es una clase de utilidad que
	 * nos proporciona el API de JAX-RS para convertir fácilmente un objeto en un JSON en nuestro caso. Esta
	 * clase nos proporciona el método ok, el cual nos crea una respuesta con status 200, es decir, respuesta exitosa,
	 * la cual recibe el mensaje que queremos responder y el tipo de datos del mensaje, en nuestro caso JSON.
	 */

}
