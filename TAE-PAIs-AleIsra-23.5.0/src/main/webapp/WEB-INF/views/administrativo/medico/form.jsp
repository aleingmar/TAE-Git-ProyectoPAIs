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
	<jstl:if test="${acme:anyOf(_command, 'update|show')}">
		<acme:input-select code="administrativo.medico.form.label.userAccount" path="userAccount" choices="${userAccounts}" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${acme:anyOf(_command, 'create|delete')}">
		<acme:input-select code="administrativo.medico.form.label.userAccount" path="userAccount" choices="${userAccounts}"/>
	</jstl:if>
	
	<acme:input-textbox code="administrativo.medico.form.label.dni" path="dni"/>
	
	<acme:input-select  code="administrativo.medico.form.label.tipoMedico" path="tipoMedico" choices="${tiposMedico}"/>	
	<acme:input-select  code="administrativo.medico.form.label.especialidad" path="especialidad" choices="${especialidades}"/>
	
	<jstl:choose>	 
		
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete')}">
			<acme:submit code="administrativo.medico.form.button.update" action="/administrativo/medico/update"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrativo.medico.form.button.create" action="/administrativo/medico/create"/>
		</jstl:when>		
	</jstl:choose>
	

</acme:form>


