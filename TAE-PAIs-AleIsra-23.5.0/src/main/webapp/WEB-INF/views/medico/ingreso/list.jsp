<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



	<jstl:if test="${_command == 'list-mine-altas'}">
		<acme:list>
			<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.faseProceso" path="faseProceso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.motivoAlta" path="motivoAlta" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.fechaAlta" path="fechaAlta" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		</acme:list>
	</jstl:if>	
	
	<jstl:if test="${_command == 'list-altas-inicial'}">
		<acme:list>
			<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.motivoAlta" path="motivoAlta" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.fechaAlta" path="fechaAlta" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		</acme:list>
	</jstl:if>	
		
	<jstl:if test="${_command == 'list-mine-ingresos'}">
		<acme:list>
			<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.fechaIngreso" path="fechaIngreso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.faseProceso" path="faseProceso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.motivoIngreso" path="motivoIngreso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.centroIngreso" path="centroIngreso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		</acme:list>
	</jstl:if>
		
	<jstl:if test="${_command == 'list'}">
		<acme:list>
			<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.fechaIngreso" path="fechaIngreso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.faseProceso" path="faseProceso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.motivoIngreso" path="motivoIngreso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.centroIngreso" path="centroIngreso" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		</acme:list>
	</jstl:if>
	
	<jstl:if test="${_command == 'list-resultado'}">
		<acme:list>
			<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.faseProceso" path="fechaValoracion" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.motivoAlta" path="resultadoValoracion" width="10%"/>
			<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		</acme:list>
		
		<acme:button code="medico.historial.form.button.crearValoracion" action="medico/ingreso/update-resultado"/>
	</jstl:if>	
		
		

	<ul>
   		<jstl:forEach var="alta" items="${altas}">
       		<li>Paciente: ${alta.paciente.userAccount.username} | Fecha alta: ${alta.fechaAlta} | Motivo alta: ${alta.motivoAlta} | Medico que da el Alta: ${alta.medico.userAccount.username}</li>
   		</jstl:forEach>
	</ul>
	
 
	
	<ul>
   		<jstl:forEach var="valoracion" items="${valoraciones}">
       		<li>Paciente: ${valoracion.paciente.userAccount.username} | Fecha valoración: ${valoracion.fechaValoracion} | Resultado valoración: ${valoracion.resultadoValoracion} | Medico que da la valoración: ${valoracion.medico.userAccount.username}</li>
   		</jstl:forEach>
	</ul>

	
