
<%--
- lISTADO DE MEDICOS
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

<acme:list>

	<acme:list-column code="administrativo.paciente.list.label.userAccount" path="userAccount.username" width="10%"/>
	<acme:list-column code="administrativo.paciente.list.label.dni" path="dni" width="10%"/>
	<acme:list-column code="administrativo.paciente.list.label.telefono" path="telefono" width="10%"/>
	<acme:list-column code="administrativo.paciente.list.label.fechaNacimiento" path="fechaNacimiento" width="10%"/>
</acme:list>
 