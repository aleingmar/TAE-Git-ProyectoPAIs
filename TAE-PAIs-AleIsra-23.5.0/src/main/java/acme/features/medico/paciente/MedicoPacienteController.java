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
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

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
		final Document document = new Document(pdf);

		// Agregar contenido al PDF (puedes personalizar esto según tus necesidades)
		document.add(new Paragraph("Información del Paciente"));
		document.add(new Paragraph("Nombre de usuario: " + paciente.getUserAccount().getUsername()));
		document.add(new Paragraph("Email: " + paciente.getUserAccount().getIdentity().getEmail()));
		document.add(new Paragraph("Teléfono: " + paciente.getTelefono()));
		document.add(new Paragraph("Fecha de Nacimiento: " + paciente.getFechaNacimiento()));
		document.add(new Paragraph("DNI: " + paciente.getDni()));

		// Cerrar el documento
		document.close();

		// Configurar la respuesta para devolver el PDF
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.builder("inline").filename("paciente.pdf").build());

		// Devolver el PDF como un array de bytes
		return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
	}

}
