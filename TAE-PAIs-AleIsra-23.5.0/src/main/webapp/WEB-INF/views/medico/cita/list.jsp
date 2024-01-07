<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
	
	<jstl:if test="${_command == 'list'}">
		<acme:list>

            <acme:list-column code="medico.cita.list.label.fechaCita" path="fechaCita" width="5%"/>
            <acme:list-column code="medico.cita.list.label.centroCita" path="centroCita" width="5%"/>
            <acme:list-column code="medico.cita.list.label.tipoCita" path="tipoCita" width="10%"/>
            <acme:list-column code="medico.cita.list.label.indicacionesCita" path="indicacionesCita" width="10%"/>
            <acme:list-column code="medico.cita.list.label.resultadoCita" path="resultadoCita" width="10%"/>
            <acme:list-column code="medico.cita.list.label.paciente" path="paciente.userAccount.username" width="10%"/>
            <acme:list-column code="medico.cita.list.label.medicoOrganiza" path="medicoOrganiza.userAccount.username" width="10%"/>
            <acme:list-column code="medico.cita.list.label.medicoTrata" path="medicoTrata.userAccount.username" width="10%"/>

		</acme:list>
	</jstl:if>	
	<%--
<acme:list>

	<jstl:forEach var="cita" items="${citas}">

            <acme:list-column code="medico.historial.list.label.fechaCita" path="{cita.fechaCita} width="5%"/>
            <acme:list-column code="medico.historial.list.label.centroCita" path="${cita.centroCita}" width="5%"/>
            <acme:list-column code="medico.historial.list.label.tipoCita" path="${cita.tipoCita}" width="10%"/>
            <acme:list-column code="medico.historial.list.label.indicacionesCita" path="${cita.indicacionesCita}" width="10%"/>
            <acme:list-column code="medico.historial.list.label.resultadoCita" path="${cita.resultadoCita}" width="10%"/>
            <acme:list-column code="medico.historial.list.label.paciente" path="${cita.paciente.userAccount.username}" width="10%"/>
            <acme:list-column code="medico.historial.list.label.medicoOrganiza" path="${cita.medicoOrganiza.userAccount.username}" width="10%"/>
            <acme:list-column code="medico.historial.list.label.medicoTrata" path="${cita.medicoTrata.userAccount.username}" width="10%"/>

    </jstl:forEach>

</acme:list>
--%>

<ul>
   <jstl:forEach var="cita" items="${citas}">
       <li>Paciente: ${cita.paciente.userAccount.username} | Fecha prueba: ${cita.fechaCita} | Centro prueba: ${cita.centroCita} | Tipo prueba: ${cita.tipoCita} | Indicaciones: ${cita.indicacionesCita} | Resultado: ${cita.resultadoCita} | Medico que trata: ${cita.medicoTrata.userAccount.username}</li>
   </jstl:forEach>
</ul>
	<acme:button code="medico.cita.form.button.crearCita" action="/medico/cita/create"/> 


