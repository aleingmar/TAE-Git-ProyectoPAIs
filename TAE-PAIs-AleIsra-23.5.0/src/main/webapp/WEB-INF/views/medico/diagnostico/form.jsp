
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>
	<acme:input-select code="medico.diagnostico.form.label.paciente" path="paciente" choices="${pacientes}"/>
		
	<acme:input-select code="medico.diagnostico.form.label.ingreso" path="ingreso" choices="${ingresos}"/>
	
		<acme:input-select code="medico.diagnostico.form.label.medico" path="medico" choices="${medicos}"/>
	
	<acme:input-moment code="medico.diagnostico.form.label.fechaDiagnostico" path="fechaDiagnostico" />
	
	<acme:input-checkbox code="medico.diagnostico.form.label.confirmado" path="confirmado"/>
	
	<acme:input-textbox code="medico.diagnostico.form.label.estadio" path="estadio" />
	
	<acme:input-textbox code="medico.diagnostico.form.label.patologia" path="patologia" />
	
	<acme:input-textbox code="medico.diagnostico.form.label.detallesDiagnostico" path="detallesDiagnostico"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="medico.diagnostico.form.button.update" action="/medico/diagnostico/update"/>
			<acme:submit code="medico.diagnostico.form.button.delete" action="/medico/diagnostico/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="medico.diagnostico.form.button.create" action="/medico/diagnostico/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>


