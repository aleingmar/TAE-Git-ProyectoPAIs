/*
 * MedicoPacienteController.java
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

import java.net.MalformedURLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.io.IOException;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import acme.framework.controllers.AbstractController;
import acme.roles.Medico;
import acme.roles.Paciente;

@Controller
public class MedicoPacienteController extends AbstractController<Medico, Paciente> {

	// Internal state ---------------------------------------------------------
	//para para que Spring se encargue de crear una instancia de la clase del servicio e inyectarlas 
	//en las variables definidas.

	@Autowired
	protected MedicoPacienteListService	listService;

	@Autowired
	protected MedicoPacienteShowService	showService;

	@Autowired
	protected MedicoPacienteRepository	repository;

	//	// Método para manejar la solicitud de generación de PDF
	//	@RequestMapping(params = "generatePdfBtn", method = RequestMethod.POST)
	//	public ModelAndView generatePdf(@ModelAttribute("paciente") final Paciente paciente) {
	//		return this.showService.generatePdf(paciente);
	//	}

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
	}

	@GetMapping("/generatePdf/{id}")
	@ResponseBody
	public ResponseEntity<byte[]> generatePdf(@PathVariable final int id) throws IOException {
		// Obtener el paciente de la base de datos
		final Paciente paciente = this.repository.findOnePacienteById(id);

		// Crear el documento PDF
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PdfWriter writer = new PdfWriter(baos);
		final PdfDocument pdf = new PdfDocument(writer);
		pdf.getDocumentInfo().setTitle("Información sobre " + paciente.getUserAccount().getUsername());  // Establecer el título del documento
		final Document document = new Document(pdf);

		final String imagePath = "imagenes/logoUs.png"; // Reemplaza con la ruta correcta de tu imagen
		final String imagePath2 = "imagenes/perfil.png"; // Reemplaza con la ruta correcta de tu imagen
		try {
			final Image image = new Image(ImageDataFactory.create(imagePath));
			final Image image2 = new Image(ImageDataFactory.create(imagePath2));

			// Ajustar el tamaño de la imagen
			image.scaleToFit(50, 50); // Puedes ajustar los valores según sea necesario
			image2.scaleToFit(80, 80);

			// Posicionar la imagen en la esquina superior izquierda
			image.setFixedPosition(20, PageSize.A4.getHeight() - 60); // Ajusta las coordenadas según sea necesario

			document.add(image);

			document.add(new Paragraph("Información del Paciente: " + paciente.getUserAccount().getUsername()).setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));

			document.add(new Paragraph("\n"));

			// Creo un paragraph para centrar la imagen
			final Paragraph imageParagraph = new Paragraph().setTextAlignment(TextAlignment.CENTER);
			imageParagraph.add(image2);

			document.add(imageParagraph);

			// Agregar espacio entre el DNI y la tabla
			document.add(new Paragraph("\n"));

			document.add(new Paragraph("Nombre de usuario: " + paciente.getUserAccount().getUsername()));
			document.add(new Paragraph("Email: " + paciente.getUserAccount().getIdentity().getEmail()));
			document.add(new Paragraph("Teléfono: " + paciente.getTelefono()));
			document.add(new Paragraph("Fecha de Nacimiento: " + paciente.getFechaNacimiento()));
			document.add(new Paragraph("DNI: " + paciente.getDni()));

			// Agregar espacio entre el DNI y la tabla
			document.add(new Paragraph("\n"));

			// Crear una tabla para organizar la información
			final Table table = new Table(5); // 2 columnas

			table.setWidth(UnitValue.createPercentValue(100));

			// Agregar filas a la tabla con etiquetas y valores
			table.addCell("Nombre de usuario").setTextAlignment(TextAlignment.CENTER);
			table.addCell("Email").setTextAlignment(TextAlignment.CENTER);
			table.addCell("Teléfono").setTextAlignment(TextAlignment.CENTER);
			table.addCell("Fecha de Nacimiento").setTextAlignment(TextAlignment.CENTER);
			table.addCell("DNI").setTextAlignment(TextAlignment.CENTER);
			table.addCell(paciente.getUserAccount().getUsername()).setTextAlignment(TextAlignment.CENTER);
			table.addCell(paciente.getUserAccount().getIdentity().getEmail()).setTextAlignment(TextAlignment.CENTER);
			table.addCell(paciente.getTelefono()).setTextAlignment(TextAlignment.CENTER);
			table.addCell(paciente.getFechaNacimiento().toString()).setTextAlignment(TextAlignment.CENTER);
			table.addCell(paciente.getDni()).setTextAlignment(TextAlignment.CENTER);

			// Agregar la tabla al documento
			document.add(table);

			// Cerrar el documento
			document.close();
		} catch (final MalformedURLException e) {
			e.printStackTrace();
		}
		// Configurar la respuesta para devolver el PDF
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.builder("inline").filename("paciente.pdf").build());

		// Devolver el PDF como un array de bytes
		return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
	}

}
