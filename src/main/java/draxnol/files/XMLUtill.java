package draxnol.files;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import draxnol.invoice.Invoice;

public class XMLUtill {
	public static void marshal(Invoice invoice, File file) throws JAXBException, IOException {
		
		String invoiceFileName = "invoice# " + invoice.getInvoiceNumber();
		String filename = file.toString() + "//" + invoiceFileName + ".xml";
		
		JAXBContext context = JAXBContext.newInstance(Invoice.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(invoice, new File(filename));
	}

}