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

	<acme:input-textbox code="paciente.cita.form.label.paciente" path="paciente.userAccount.username" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.fechaCita" path="fechaCita" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.centroCita" path="centroCita" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.tipoCita" path="tipoCita" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.indicacionesCita" path="indicacionesCita" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.resultadoCita" path="resultadoCita" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.medicoOrganiza" path="medicoOrganiza.userAccount.username" readonly="true"/>
	<acme:input-textbox code="paciente.cita.form.label.medicoTrata" path="medicoTrata.userAccount.username" readonly="true"/>

</acme:form>


