
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

	<acme:button code="medico.paciente.form.button.verPruebas" action="/medico/cita/list-pruebas?pacienteId=${id}"/>
	<acme:button code="medico.paciente.form.button.verValoraciones" action="/medico/ingreso/list-valoraciones?pacienteId=${id}"/>
	<acme:button code="medico.paciente.form.button.verDiagnosticos" action="/medico/diagnostico/list-diagnosticos?pacienteId=${id}"/>
	<acme:button code="medico.paciente.form.button.verAltas" action="/medico/ingreso/list-altas?pacienteId=${id}"/>



	<a href="<spring:url value='/generatePdf/${id}'/>" target="_blank">
        <button type="button">Generar PDF</button>
    </a>
</acme:form>


