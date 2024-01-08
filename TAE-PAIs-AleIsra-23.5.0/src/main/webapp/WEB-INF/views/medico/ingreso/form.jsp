
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>

	<acme:input-select code="paciente.ingreso.form.label.paciente" path="paciente" choices="${pacientes}" readonly="true"/>
	<acme:input-select code="paciente.ingreso.form.label.medico" path="medico" choices="${medicos}" readonly="true"/>
	<acme:input-textbox code="paciente.ingreso.form.label.motivoAlta" path="motivoAlta"/>
	<acme:input-moment code="paciente.ingreso.form.label.fechaAlta" path="fechaAlta" readonly="true"/>

	<acme:submit code="administrativo.alta.form.button.update" action="/medico/ingreso/update"/>
</acme:form>


