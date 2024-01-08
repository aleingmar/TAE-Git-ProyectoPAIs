<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>

		<jstl:if test="${_command == 'list-mine-altas'}">

		<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.motivoAlta" path="motivoAlta" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.fechaAlta" path="fechaAlta" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		
		</jstl:if>	
		
		<jstl:if test="${_command == 'list-mine-ingresos'}">

		<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.fechaIngreso" path="fechaIngreso" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.faseProceso" path="faseProceso" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.motivoIngreso" path="motivoIngreso" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.centroIngreso" path="centroIngreso" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		
		</jstl:if>
		
		<jstl:if test="${_command == 'list-mine-resultados'}">

		<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.fechaValoracion" path="fechaValoracion" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.resultadoValoracion" path="resultadoValoracion" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
		
		</jstl:if>
		
		
		
</acme:list>