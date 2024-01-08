<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<acme:form>

	<jstl:if test="${acme:anyOf(_command, 'update|show')}">
		<acme:input-select code="administrativo.paciente.form.label.userAccount" path="userAccount" choices="${userAccounts}" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${acme:anyOf(_command, 'create|delete')}">
		<acme:input-select code="administrativo.paciente.form.label.userAccount" path="userAccount" choices="${userAccounts}"/>
	</jstl:if>
	
	<acme:input-textbox code="administrativo.paciente.form.label.dni" path="dni"/>
	<acme:input-textbox code="administrativo.paciente.form.label.telefono" path="telefono"/>
	<acme:input-textbox code="administrativo.paciente.form.label.fechaNacimiento" path="fechaNacimiento"/>


	<jstl:choose>	 
		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="administrativo.paciente.form.button.update" action="/administrativo/paciente/update"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrativo.paciente.form.button.create" action="/administrativo/paciente/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>


	


