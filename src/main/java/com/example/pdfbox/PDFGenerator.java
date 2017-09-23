package com.example.pdfbox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFGenerator {

	public static void main(String[] args) throws IOException {

		// byte[] pdf = generatePDF();
		// System.out.println(pdf);

		// Creating PDF document object
		PDDocument document = new PDDocument();

		// Saving the document
		PDPage page = new PDPage();
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		contentStream.beginText();
		contentStream.newLineAtOffset(25, 500);
		contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentStream.showText("Hello generated PDF using pdfbox");
		contentStream.endText();
		contentStream.close();
		document.addPage(page);
		document.save("/users/nand/desktop/my_doc.pdf");
		System.out.println("PDF created");
		document.close();
	}

	public static byte[] generatePDF() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PDDocument document = new PDDocument();
		InputStream stream = new ByteArrayInputStream("<html><body><p1>test</p1></body></html>".getBytes());
		PDStream pdstream = new PDStream(document, stream);
		document.save(baos);
		return pdstream.toByteArray();
	}
}
