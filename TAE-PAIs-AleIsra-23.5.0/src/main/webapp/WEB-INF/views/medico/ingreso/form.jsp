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

	<acme:input-select code="paciente.ingreso.form.label.paciente" path="paciente" choices="${pacientes}" readonly="true"/>
	<acme:input-select code="paciente.ingreso.form.label.medico" path="medico" choices="${medicos}" readonly="true"/>
	<acme:input-textbox code="paciente.ingreso.form.label.motivoAlta" path="motivoAlta"/>
	<acme:input-moment code="paciente.ingreso.form.label.fechaAlta" path="fechaAlta"/>

	<acme:submit code="administrativo.alta.form.button.update" action="/medico/ingreso/update"/>
</acme:form>


