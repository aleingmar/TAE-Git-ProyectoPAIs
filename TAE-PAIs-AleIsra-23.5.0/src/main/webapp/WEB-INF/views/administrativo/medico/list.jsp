
<%--
- lISTADO DE MEDICOS
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrativo.medico.list.label.especialidad" path="especialidad" width="10%"/>
	<acme:list-column code="administrativo.medico.list.label.tipoMedico" path="tipoMedico" width="10%"/>
	<acme:list-column code="administrativo.medico.list.label.dni" path="dni" width="10%"/>
	<acme:list-column code="administrativo.medico.list.label.nombreUsuario" path="userAccount.username" width="10%"/>
	<acme:list-column code="administrativo.medico.list.label.email" path="userAccount.identity.email" width="10%"/>
</acme:list>

