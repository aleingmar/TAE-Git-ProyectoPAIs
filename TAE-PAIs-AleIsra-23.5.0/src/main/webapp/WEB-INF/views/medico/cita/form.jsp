<%--
Es necesario que se pueda crear una valoracion de ESA CITA EN CONCRETO
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>




<acme:form>
	<acme:input-textbox code="medico.historial.form.label.fechaCita" path="fechaCita" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.centroCita" path="centroCita" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.tipoCita" path="tipoCita" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.indicacionesCita" path="indicacionesCita" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.resultadoCita" path="resultadoCita" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.paciente" path="paciente.userAccount.username" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.medicoOrganiza" path="medicoOrganiza.userAccount.username" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.medicoTrata" path="medicoTrata.userAccount.username" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.motivoIngreso" path="ingreso.motivoIngreso" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.fechaValoracion" path="ingreso.fechaValoracion" readonly="true"/>
	<acme:input-textbox code="medico.historial.form.label.resultadoValoracion" path="ingreso.resultadoValoracion" readonly="true"/>
	
	<acme:button code="medico.historial.form.button.crearValoracion" action=""/> 
	
	<jstl:if test="${medico.tipoMedico eq AH}">
        <acme:button code="medico.historial.form.button.crearAlta" action=""/> 
    </jstl:if>
	

</acme:form>


