<%--
- form.jsp
-
- Copyright (C) 2012-2023 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:input-textbox code="medico.paciente.form.label.nombreUsuario" path="userAccount.username" readonly="true"/>
	<acme:input-email code="medico.paciente.form.label.email" path="userAccount.identity.email" readonly="true"/>
	<acme:input-textbox code="medico.paciente.form.label.telefono" path="telefono" readonly="true"/>
	<acme:input-textbox code="medico.paciente.form.label.fechaNacimiento" path="fechaNacimiento" readonly="true"/>
	<acme:input-textbox code="medico.paciente.form.label.dni" path="dni" readonly="true"/>

	<acme:button code="medico.paciente.form.button.verHistorial" action="/medico/cita/list"/>

	<a href="<spring:url value='/generatePdf/${id}'/>" target="_blank">
        <button type="button">Generar PDF</button>
    </a>
</acme:form>


