<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
		<acme:list-column code="paciente.ingreso.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.motivoAlta" path="motivoAlta" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.fechaAlta" path="fechaAlta" width="10%"/>
		<acme:list-column code="paciente.ingreso.list.label.medico" path="medico.userAccount.username" width="10%"/>
</acme:list>

