<%--
- menu.jsp
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

<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://www.the-acme-framework.org/"%>


<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		
		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.medicos" action="/administrator/medico/list"/>
			<acme:menu-suboption code="master.menu.administrator.pacientes" action="/administrator/paciente/list"/>
			<acme:menu-suboption code="master.menu.administrator.citas" action="/administrator/cita/list"/>
			<acme:menu-suboption code="master.menu.administrator.ingresos" action="/administrator/ingreso/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.crearPaciente" action="/administrator/paciente/create"/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrativo" access="hasRole('Administrativo')">
			<acme:menu-suboption code="master.menu.administrator.medicos" action="/administrativo/medico/list"/>
			<acme:menu-suboption code="master.menu.administrator.pacientes" action="/administrativo/paciente/list"/>
			<acme:menu-suboption code="master.menu.administrator.citas" action="/administrativo/cita/list"/>
			<acme:menu-suboption code="master.menu.administrator.ingresos" action="/administrativo/ingreso/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.administrativo.creacionCuentas" access="hasRole('Administrativo')">
			<acme:menu-suboption code="master.menu.sign-up" action="/administrativo/user-account/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.crearPaciente" action="/administrativo/paciente/create"/>
			<acme:menu-suboption code="master.menu.user-account.crearMedico" action="/administrativo/medico/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.paciente" access="hasRole('Paciente')">
			<acme:menu-suboption code="master.menu.paciente.my-citas" action="/paciente/cita/list-mine"/>
			<acme:menu-suboption code="master.menu.paciente.my-altas" action="/paciente/ingreso/list-mine-altas"/>
			<acme:menu-suboption code="master.menu.paciente.my-ingresos" action="/paciente/ingreso/list-mine-ingresos"/>
			<acme:menu-suboption code="master.menu.paciente.my-diagnosticos" action="/paciente/diagnostico/list-mine-diagnosticos"/>
			<acme:menu-suboption code="master.menu.paciente.my-resultados" action="/paciente/ingreso/list-mine-resultados"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.medico" access="hasRole('Medico')">

			<acme:menu-suboption code="master.menu.medico.pacientes" action="/medico/paciente/list"/>
			<acme:menu-suboption code="master.menu.medico.medicos" action="/medico/medico/list"/>
			<acme:menu-suboption code="master.menu.medico.pruebas" action="/medico/cita/list"/>
			<%--
			<acme:menu-suboption code="master.menu.paciente.my-ingresos" action="/medico/ingreso/list-mine-ingresos"/>
			--%>
			<acme:menu-suboption code="master.menu.medico.altas" action="/medico/ingreso/list-altas-inicial"/>
			<acme:menu-suboption code="master.menu.medico.diagnosticos" action="/medico/diagnostico/list"/>
			<%--
			<acme:menu-suboption code="master.menu.medico.resultados" action="/medico/ingreso/list-resultado"/>
			<acme:menu-suboption code="master.menu.medico.crearResultado" action="/medico/ingreso/create"/>
			
			<acme:menu-suboption code="master.menu.medico.mimismo" action="/medico/medico/list-a-mi-mismo"/>
			
			<jstl:out value="${currentMedico.tipoMedico}"></jstl:out>
			<jstl:if test="${currentMedico.tipoMedico == 'AH'}">
  			<acme:menu-suboption code="master.menu.medico.historiales" action="/medico/cita/list"/>
  			--%>
    		
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

