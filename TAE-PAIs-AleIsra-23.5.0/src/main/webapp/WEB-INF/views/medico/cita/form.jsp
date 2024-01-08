<%--
Es necesario que se pueda crear una valoracion de ESA CITA EN CONCRETO
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>




<acme:form>
	<acme:input-moment code="medico.cita.form.label.fechaCita" path="fechaCita" />
	
	<acme:input-select code="medico.cita.form.label.centroCita" path="centroCita" choices="${centros}"/>
	<acme:input-select code="medico.cita.form.label.tipoCita" path="tipoCita" choices="${tipos}"/>
	<acme:input-select code="medico.cita.form.label.paciente" path="paciente" choices="${pacientes}"/>
	<acme:input-textbox code="medico.cita.form.label.medicoOrganiza" path="medicoOrganiza.userAccount.username" readonly="true"/>
	<acme:input-select code="medico.cita.form.label.medicoTrata" path="medicoTrata" choices="${medicosTrata}"/>

	<acme:input-textbox code="medico.cita.form.label.indicacionesCita" path="indicacionesCita"/>
	

<jstl:choose>	 
		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
				<acme:submit code="medico.cita.form.button.updateCita" action="/medico/cita/update"/>
				<acme:submit code="medico.cita.form.button.deleteCita" action="/medico/cita/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="medico.cita.form.button.create" action="/medico/cita/create"/>
		</jstl:when>		
	</jstl:choose>
		
</acme:form>


