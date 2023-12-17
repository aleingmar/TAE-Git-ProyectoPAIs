/*
 * AdministratorMedicoShowService.java
 *
 * Copyright (C) 2012-2023 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.medico.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;
import acme.roles.Paciente;

@Service
public class MedicoPacienteShowService extends AbstractService<Medico, Paciente> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected MedicoPacienteRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void check() {
		boolean status;

		status = super.getRequest().hasData("id", int.class);

		super.getResponse().setChecked(status);
	}

	@Override
	public void authorise() {
		int id;
		Paciente Paciente;
		id = super.getRequest().getData("id", int.class);
		Paciente = this.repository.findOnePacienteById(id);

		super.getResponse().setAuthorised(true);
	}

	@Override
	public void load() {
		Paciente object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOnePacienteById(id);

		super.getBuffer().setData(object);
	}

	@Override
	public void unbind(final Paciente object) {
		assert object != null;

		Tuple tuple;

		tuple = super.unbind(object, "userAccount.username", "userAccount.identity.email", "telefono", "fechaNacimiento", "dni");

		super.getResponse().setData(tuple);

	}

	//	public void generatePdf(final Paciente paciente) throws FileNotFoundException {
	//		final Document document = new Document();
	//		try {
	//			PdfWriter.getInstance(document, new FileOutputStream("Paciente_" + paciente.getId() + ".pdf"));
	//			document.open();
	//			document.add(new Paragraph("Nombre del paciente: " + paciente.getUserAccount().getUsername()));
	//			document.add(new Paragraph("Email del paciente: " + paciente.getUserAccount().getIdentity().getEmail()));
	//			document.add(new Paragraph("Teléfono del paciente: " + paciente.getTelefono()));
	//			document.add(new Paragraph("Fecha de nacimiento del paciente: " + paciente.getFechaNacimiento()));
	//			document.add(new Paragraph("DNI del paciente: " + paciente.getDni()));
	//			document.close();
	//		} catch (DocumentException | IOException e) {
	//			e.printStackTrace();
	//		}
	//	}
}
