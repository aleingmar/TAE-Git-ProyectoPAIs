
package acme.features.medico.paciente;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import acme.framework.components.models.Tuple;
import acme.framework.services.AbstractService;
import acme.roles.Medico;
import acme.roles.Paciente;

public class PdfMedicoPacienteService {

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

		public void generatePdf(final Paciente object) throws IOException {
			try {
				// Crear una instancia de PdfWriter
				final PdfWriter writer = new PdfWriter("Paciente.pdf");

				// Crear un PdfDocument
				final PdfDocument pdfDoc = new PdfDocument(writer);

				// Crear un Document
				final Document doc = new Document(pdfDoc);

				// Agregar contenido al documento
				doc.add(new Paragraph("Nombre de usuario: " + object.getUserAccount().getUsername()));
				doc.add(new Paragraph("Email: " + object.getUserAccount().getIdentity().getEmail()));
				doc.add(new Paragraph("Teléfono: " + object.getTelefono()));
				doc.add(new Paragraph("Fecha de nacimiento: " + object.getFechaNacimiento()));
				doc.add(new Paragraph("DNI: " + object.getDni()));

				// Cerrar el documento
				doc.close();

				System.out.println("PDF creado correctamente");
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
}
