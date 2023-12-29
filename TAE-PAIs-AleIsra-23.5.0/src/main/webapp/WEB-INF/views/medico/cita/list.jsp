<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>

	<acme:list-column code="medico.historial.list.label.fechaCita" path="fechaCita" width="5%"/>
	<acme:list-column code="medico.historial.list.label.centroCita" path="centroCita" width="5%"/>
	<acme:list-column code="medico.historial.list.label.tipoCita" path="tipoCita" width="10%"/>
	<acme:list-column code="medico.historial.list.label.indicacionesCita" path="indicacionesCita" width="10%"/>
	<acme:list-column code="medico.historial.list.label.resultadoCita" path="resultadoCita" width="10%"/>
	<acme:list-column code="medico.historial.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
	<acme:list-column code="medico.historial.list.label.medicoOrganiza" path="medicoOrganiza.userAccount.username" width="10%"/>
	<acme:list-column code="medico.historial.list.label.medicoTrata" path="medicoTrata.userAccount.username" width="10%"/>
	<acme:list-column code="medico.historial.list.label.motivoIngreso" path="ingreso.motivoIngreso" width="10%"/>
	<acme:list-column code="medico.historial.list.label.motivoIngreso" path="ingreso.fechaValoracion" width="10%"/>
	<acme:list-column code="medico.historial.list.label.motivoIngreso" path="ingreso.resultadoValoracion" width="10%"/>
	
</acme:list>

