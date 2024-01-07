<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>

	<acme:list-column code="administrativo.ingreso.list.label.fechaIngreso" path="fechaIngreso" width="5%"/>
	<acme:list-column code="administrativo.ingreso.list.label.faseProceso" path="faseProceso" width="5%"/>
	<acme:list-column code="administrativo.ingreso.list.label.motivoIngreso" path="motivoIngreso" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.centroIngreso" path="centroIngreso" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.fechaValoracion" path="fechaValoracion" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.resultadoValoracion" path="resultadoValoracion" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.motivoAlta" path="motivoAlta" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.fechaAlta" path="fechaAlta" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
	<acme:list-column code="administrativo.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
	
</acme:list>
<acme:button code="medico.diagnostico.form.button.crearIngreso" action="/administrativo/ingreso/create"/> 

