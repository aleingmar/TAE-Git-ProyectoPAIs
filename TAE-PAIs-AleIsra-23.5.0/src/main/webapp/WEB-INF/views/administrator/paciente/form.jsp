<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.paciente.form.label.nombreUsuario" path="userAccount.username" readonly="true"/>
	<acme:input-email code="administrator.paciente.form.label.email" path="userAccount.identity.email" readonly="true"/>
	<acme:input-textbox code="administrator.paciente.form.label.dni" path="dni"/>
	<acme:input-textbox code="administrator.paciente.form.label.telefono" path="telefono"/>
	<acme:input-textbox code="administrator.paciente.form.label.fechaNacimiento" path="fechaNacimiento"/>

</acme:form>


