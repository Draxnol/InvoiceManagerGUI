package draxnol.files;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import draxnol.invoice.Invoice;

public class DocxUtill {
	
	public static void exportTodocx(Invoice invoice, File templateFile, File outputDirectory) {
		
		try {
			
			String outString = outputDirectory.toString() + "// " + "invoice# " + invoice.getInvoiceNumber() + ".docx";
			
			
			WordprocessingMLPackage wordMLPackage = Docx4J.load(templateFile);
			Docx4J.bind(wordMLPackage, marshal(invoice), Docx4J.FLAG_BIND_INSERT_XML & Docx4J.FLAG_BIND_BIND_XML );
			Docx4J.save(wordMLPackage, new File(outString), Docx4J.FLAG_NONE);
		
		} catch (Docx4JException | JAXBException | IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
	
	public static String marshal(Invoice invoice) throws JAXBException, IOException {
		java.io.StringWriter sw = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(Invoice.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(invoice, sw);
		return sw.toString();
	}


}
