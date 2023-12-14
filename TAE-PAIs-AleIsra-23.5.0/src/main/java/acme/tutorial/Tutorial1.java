
package acme.tutorial;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class Tutorial1 {

	public static final String DEST = "results/pdfs/hello_world.pdf";


	public static void main(final String args[]) throws IOException {
		final File file = new File(Tutorial1.DEST);
		file.getParentFile().mkdirs();
		final Tutorial1 tutorial = new Tutorial1();

		// Assuming you have a HttpServletRequest object containing the form data
		final HttpServletRequest request = Tutorial1.getServletRequestWithData();

		tutorial.createPdf(Tutorial1.DEST, request);
	}

	public void createPdf(final String dest, final HttpServletRequest request) throws IOException {
		// Initialize PDF writer
		final PdfWriter writer = new PdfWriter(dest);

		// Initialize PDF document
		final PdfDocument pdf = new PdfDocument(writer);

		// Initialize document
		final Document document = new Document(pdf);

		// Add paragraphs with form data to the document
		document.add(new Paragraph("Nombre de Usuario: " + request.getParameter("userAccount.username")));
		document.add(new Paragraph("Email: " + request.getParameter("userAccount.identity.email")));
		document.add(new Paragraph("Teléfono: " + request.getParameter("telefono")));
		document.add(new Paragraph("Fecha de Nacimiento: " + request.getParameter("fechaNacimiento")));
		document.add(new Paragraph("DNI: " + request.getParameter("dni")));

		// Close document
		document.close();
	}

	private static HttpServletRequest getServletRequestWithData() {
		// Simulate getting a HttpServletRequest with form data
		// Replace this with your actual HttpServletRequest object
		// containing the form data
		// For example, you can use a framework like Spring MVC to bind form data to an object
		return null;
	}
}
