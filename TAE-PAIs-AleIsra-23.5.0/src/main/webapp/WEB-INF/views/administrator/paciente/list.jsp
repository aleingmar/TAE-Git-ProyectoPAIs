
<%--
- lISTADO DE MEDICOS
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>

	<acme:list-column code="administrator.paciente.list.label.nombreUsuario" path="userAccount.username" width="10%"/>
	<acme:list-column code="administrator.paciente.list.label.email" path="userAccount.identity.email" width="10%"/>
	<acme:list-column code="administrator.paciente.list.label.dni" path="dni" width="10%"/>
	<acme:list-column code="administrator.paciente.list.label.telefono" path="telefono" width="10%"/>
	<acme:list-column code="administrator.paciente.list.label.fechaNacimiento" path="fechaNacimiento" width="10%"/>
</acme:list>

