/*
 * This file is part of the iText (R) project.
 * Copyright (c) 1998-2023 Apryse Group NV
 * Authors: Apryse Software.
 * 
 * For more information, please contact iText Software at this address:
 * sales@itextpdf.com
 */

package acme.tutorial;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 * Simple Hello World example.
 */
public class Tutorial1 {

	public static final String DEST = "results/chapter01/hello_world.pdf";


	public static void main(final String args[]) throws IOException {
		final File file = new File(Tutorial1.DEST);
		file.getParentFile().mkdirs();
		new Tutorial1().createPdf(Tutorial1.DEST);
	}

	public void createPdf(final String dest) throws IOException {

		//Initialize PDF writer
		final PdfWriter writer = new PdfWriter(dest);

		//Initialize PDF document
		final PdfDocument pdf = new PdfDocument(writer);

		// Initialize document
		final Document document = new Document(pdf);

		//Add paragraph to the document
		document.add(new Paragraph("Hello World!"));

		//Close document
		document.close();
	}
}
