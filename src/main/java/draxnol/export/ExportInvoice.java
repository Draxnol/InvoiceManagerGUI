package draxnol.export;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;


@XmlRootElement
public class ExportInvoice {
	
	
	/*invoice pogo*/
	private String date;
	private String invoiceNumber;
	private String invoiceBillingAddress;
	private String invoicePayableAddress;
	private String invoiceHeader;
	private ArrayList<ExportInvoiceRow> invoiceTable = new ArrayList<>();
	

	public void setDate(String date) {
		this.date = date;
	}
	@XmlElement
	public String getDate() {
		return date;
	}
	
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	@XmlElement
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	

	public void setInvoiceBillingAddress(String invoiceBillingAddress) {
		this.invoiceBillingAddress = invoiceBillingAddress;
	}
	@XmlElement
	public String getInvoiceBillingAddress() {
		return invoiceBillingAddress;
	}
	

	public void setInvoicePayableAddress(String invoicePayableAddress) {
		this.invoicePayableAddress = invoicePayableAddress;
	}
	@XmlElement
	public String getInvoicePayableAddress() {
		return invoicePayableAddress;
	}


	public void setInvoiceHeader(String invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}
	@XmlElement
	public String getInvoiceHeader() {
		return invoiceHeader;
	}
	
	public void addInvoiceRow(String unit, String desc, String cost) {
		invoiceTable.add(new ExportInvoiceRow(unit, desc, cost));
	}
	
	@XmlElementWrapper(name="Invoice Rows")
	@XmlElement(name="row")
	public List<ExportInvoiceRow> getInvoiceRows(){
		return invoiceTable;
	}

}
