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

	<acme:input-textbox code="administrator.ingreso.form.label.fechaIngreso" path="fechaIngreso" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.faseProceso" path="faseProceso" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.motivoIngreso" path="motivoIngreso" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.centroIngreso" path="centroIngreso" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.fechaValoracion" path="fechaValoracion" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.resultadoValoracion" path="resultadoValoracion" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.motivoAlta" path="motivoAlta" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.fechaAlta" path="fechaAlta" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.paciente" path="paciente.userAccount.username" readonly="true"/>
	<acme:input-textbox code="administrator.ingreso.form.label.medico" path="medico.userAccount.username" readonly="true"/>
	
</acme:form>


