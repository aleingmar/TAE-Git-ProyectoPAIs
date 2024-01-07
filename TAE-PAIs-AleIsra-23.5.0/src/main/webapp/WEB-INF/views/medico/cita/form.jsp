<%--
Es necesario que se pueda crear una valoracion de ESA CITA EN CONCRETO
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>




<acme:form>
	<acme:input-moment code="medico.historial.form.label.fechaCita" path="fechaCita" />
	
	<acme:input-select code="medico.historial.form.label.centroCita" path="centroCita" choices="${centros}"/>
	<acme:input-select code="medico.historial.form.label.tipoCita" path="tipoCita" choices="${tipos}"/>
	<acme:input-select code="medico.historial.form.label.paciente" path="paciente" choices="${pacientes}"/>
	<acme:input-textbox code="medico.historial.form.label.medicoOrganiza" path="medicoOrganiza" readonly="true"/>
	<acme:input-select code="medico.historial.form.label.medicoTrata" path="medicoTrata" choices="${medicosTrata}"/>

	<acme:input-textbox code="medico.historial.form.label.indicacionesCita" path="indicacionesCita"/>
	<acme:input-textbox code="medico.historial.form.label.resultadoCita" path="resultadoCita"/>

	
	<acme:submit code="medico.historial.form.button.crearCita" action="/medico/cita/create"/> 
	

</acme:form>


