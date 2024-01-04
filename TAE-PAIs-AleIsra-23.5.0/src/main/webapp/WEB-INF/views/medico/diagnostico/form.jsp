
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:input-textbox code="medico.diagnostico.form.label.paciente" path="ingreso.paciente.userAccount.username" readonly="true"/>
	<acme:input-email code="medico.diagnostico.form.label.fechaDiagnostico" path="fechaDiagnostico" readonly="true"/>
	<acme:input-textbox code="medico.diagnostico.form.label.confirmado" path="confirmado" readonly="true"/>
	<acme:input-textbox code="medico.diagnostico.form.label.estadio" path="estadio" readonly="true"/>
	<acme:input-textbox code="medico.diagnostico.form.label.patologia" path="patologia" readonly="true"/>
	<acme:input-textbox code="medico.diagnostico.form.label.detallesDiagnostico" path="detallesDiagnostico" readonly="true"/>
	<acme:input-textbox code="medico.diagnostico.form.label.medico" path="ingreso.medico.userAccount.username" readonly="true"/>

</acme:form>


