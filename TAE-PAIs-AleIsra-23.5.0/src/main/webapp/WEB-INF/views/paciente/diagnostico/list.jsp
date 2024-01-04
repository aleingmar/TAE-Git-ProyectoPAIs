
<%--
- lISTADO DE PACIENTES
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>

	<jstl:if test="${_command == 'list-mine-diagnosticos'}">
		<acme:list>
			<acme:list-column code="medico.diagnostico.list.label.paciente" path="ingreso.paciente.userAccount.username" width="10%"/>
			<acme:list-column code="medico.diagnostico.list.label.fechaDiagnostico" path="fechaDiagnostico" width="10%"/>
			<acme:list-column code="medico.diagnostico.list.label.confirmado" path="confirmado" width="10%"/>
			<acme:list-column code="medico.diagnostico.list.label.estadio" path="estadio" width="10%"/>
			<acme:list-column code="medico.diagnostico.list.label.patologia" path="patologia" width="10%"/>
			<acme:list-column code="medico.diagnostico.list.label.detallesDiagnostico" path="detallesDiagnostico" width="10%"/>
			<acme:list-column code="medico.diagnostico.list.label.medico" path="ingreso.medico.userAccount.username" width="10%"/>
		</acme:list>
	</jstl:if>
