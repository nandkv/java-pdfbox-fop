package com.example.pdfbox;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.xml.transform.StringSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.ClassPathResource;

public class ApacheFOP {
	public static void main(String[] args) throws Exception {
		FileOutputStream outputStream = new FileOutputStream("/users/nand/desktop/test.pdf");
		File templateFile = new ClassPathResource("xhtml-to-xslfo.xsl").getFile();
		//File sourceFile = new ClassPathResource("test.html").getFile();

		final Source xmlSource = new StringSource("<html><body><h1>test</h1><h2>nand</h2><p>testing</p></body></html>");
		final Source sourceTemplate = new StreamSource(templateFile);

		// version 2.1 of getting factory
		FopFactoryBuilder builder = new FopFactoryBuilder(new URI("http://google.com"));
		builder.setSourceResolution(96);
		FopFactory fopFactory = builder.build();

		final FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		Fop fop;
		try {
			fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);

			final TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(sourceTemplate);

			final Result result = new SAXResult(fop.getDefaultHandler());
			transformer.transform(xmlSource, result);

			outputStream.flush();

		} catch (final Exception exp) {
			throw new RuntimeException("Error creating PDF", exp);
		}
	}
}
