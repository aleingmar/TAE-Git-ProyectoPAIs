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
	
	<acme:input-select  code="administrativo.ingreso.form.label.faseProceso" path="faseProceso" choices="${fasesProceso}"/>	
	<acme:input-select  code="administrativo.ingreso.form.label.centroIngreso" path="centroIngreso" choices="${centrosIngreso}"/>
	
	<jstl:if test="${acme:anyOf(_command, 'update|show')}">
		<acme:input-moment code="administrativo.ingreso.form.label.fechaIngreso" path="fechaIngreso" readonly="true"/>
		<acme:input-select code="administrativo.ingreso.form.label.paciente" path="paciente" choices="${pacientes}" readonly="true"/>
		<acme:input-select  code="administrativo.ingreso.form.label.cita" path="cita" choices="${citas}" readonly="true"/>
		
	</jstl:if>
	
	<jstl:if test="${acme:anyOf(_command, 'create|delete')}">
		<acme:input-select code="administrativo.ingreso.form.label.paciente" path="paciente" choices="${pacientes}"/>
		<acme:input-select  code="administrativo.ingreso.form.label.cita" path="cita" choices="${citas}"/>
	</jstl:if>
	
	<acme:input-select code="administrativo.ingreso.form.label.motivoIngreso" path="motivoIngreso" choices="${motivosIngreso}"/>
	<acme:input-select code="administrativo.ingreso.form.label.medico" path="medico" choices="${medicos}"/>
	
	<%--
	<acme:input-moment code="administrativo.ingreso.form.label.fechaValoracion" path="fechaValoracion"/>
	<acme:input-textbox code="administrativo.ingreso.form.label.motivoAlta" path="motivoAlta" readonly="true"/>
	<acme:input-moment code="administrativo.ingreso.form.label.fechaAlta" path="fechaAlta" readonly="true"/>
	<acme:input-textbox code="administrativo.ingreso.form.label.resultadoValoracion" path="resultadoValoracion" readonly="true"/>
	--%>
	
	<jstl:choose>	 
		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="administrativo.ingreso.form.button.update" action="/administrativo/ingreso/update"/>
			<acme:submit code="administrativo.ingreso.form.button.delete" action="/administrativo/ingreso/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrativo.ingreso.form.button.create" action="/administrativo/ingreso/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>


