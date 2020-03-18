package draxnol.export;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlUtil {

	public void marshal() throws JAXBException, IOException{
		ExportInvoice invoice1 = new ExportInvoice();
		invoice1.setDate("today");
		invoice1.setInvoiceBillingAddress("123 fakeStreet");
		invoice1.setInvoiceHeader("Bob");
		invoice1.setInvoicePayableAddress("2222 street");
		invoice1.setInvoiceNumber("0001");
		invoice1.addInvoiceRow("12", "okay", "die");
		invoice1.addInvoiceRow("13","no", "live");
		
		JAXBContext context = JAXBContext.newInstance(ExportInvoice.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(invoice1, new File("./invoice.xml"));
	}

}
