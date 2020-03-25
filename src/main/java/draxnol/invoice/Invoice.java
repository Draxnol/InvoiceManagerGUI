package draxnol.invoice;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
public class Invoice {
	
	
	//String Properties
	private SimpleStringProperty payableAddress;
	private SimpleStringProperty billingAddress;
	//Double Properties
	private SimpleDoubleProperty invoiceTotal;
	//Integer properties
	private SimpleIntegerProperty invoiceNumber;
	private SimpleIntegerProperty invoiceID;
	private SimpleIntegerProperty contactID;
	//Date 
	private SimpleStringProperty invoiceDateString;
	public InvoiceStatus invoiceStatus;
	
	@XmlTransient
	public ObservableList<InvoiceRow> invoiceRows = FXCollections.observableArrayList();
	@XmlTransient
	public ArrayList<AddressLine> payableAddressLines = new ArrayList<>();
	@XmlTransient
	public ArrayList<AddressLine> billingAddressLines = new ArrayList<>();
	private String profileHeader;
	private String contactName;
	
	
	public enum InvoiceStatus{
		NOT_SAVED,
		SAVED,
	}

	
	public Invoice() {
		init();
	}
	
	public Invoice(int invoiceNumber, int contactID, String date) {
		init();
		this.invoiceStatus = InvoiceStatus.NOT_SAVED;
		this.invoiceNumber.set(invoiceNumber);
		this.contactID.set(contactID);
		this.setDate(date);

	}
	
	private void init() {
		this.invoiceID = new SimpleIntegerProperty();
		this.contactID = new SimpleIntegerProperty();
		this.invoiceNumber = new SimpleIntegerProperty();
		this.payableAddress = new SimpleStringProperty();
		this.billingAddress = new SimpleStringProperty();
		this.invoiceTotal = new SimpleDoubleProperty();
		this.invoiceDateString = new SimpleStringProperty();
		
	
	} 
	
	/*Date stuff*/
	//TODO Proper date handling, SQLite requires using yyyy/mm/dd
	public void setDate(String date) {
		this.invoiceDateString.set(date);
		
	}

	@XmlElement
	public String getContactName() {
		return contactName;
	}
	
	@XmlElement
	public String getProfileHeader() {
		return profileHeader;
	}
	
	@XmlElement
	public String getInvoiceDateString() {
		return invoiceDateString.get();
	}

	/*Invoice id*/
	
	public void setInvoiceID(int id) {
		this.invoiceID.set(id);
	}
	@XmlElement
	public int getInvoiceID() {
		return this.invoiceID.get();
	}
	
	public SimpleIntegerProperty invoiceIDProperty() {
		return invoiceID;
	}
	
	
	//Contact id
	public void setContactID(int id) {
		contactID.set(id);
	}
	@XmlElement
	public int getContactID() {
		return contactID.get();
	}
	
	public SimpleIntegerProperty contactIDProperty() {
		return contactID;
	}
	
	
	/*Invoice number*/

	public void setInvoiceNumber(int number) {
		invoiceNumber.set(number);
	}
	@XmlElement
	public int getInvoiceNumber() {
		return invoiceNumber.get();
	}
	
	public SimpleIntegerProperty invoiceNumberProperty() {
		return invoiceNumber;
	}
	

	/* payableAddress */
	public void setInvoicePayableAddress(String address) {
		payableAddress.set(address);
		generateAddressLines(getInvoicePayableAddressAsList(), payableAddressLines);
		
	}
	
	public String getInvoicePayableAddress() {
		return payableAddress.get();
	}
	
	public void generateAddressLines(String[] splitAddress, ArrayList<AddressLine> addressLines) {
		int count = 0;
		
		if (addressLines.isEmpty()) {
			for (String i : splitAddress) {
				addressLines.add(new AddressLine(i, count));
				count += 1;
			}
			
		}else {
			System.out.println("address already set");
		}
	} 
	
	public String[] getInvoicePayableAddressAsList() {
		return payableAddress.get().split("\n");
	}
	
	public String[] getInvoiceBillingAdddressAsList() {
		return billingAddress.get().split("\n");
	}
	
	@XmlElementWrapper(name="PayableAddressLines")
	@XmlElement(name="Line")
	public ArrayList<AddressLine> getInvoicePayableAddressLines() {
		return payableAddressLines;
	
	}
	
	public SimpleStringProperty payableAddressProperty() {
		return payableAddress;
	}
	
	/* Billing address */
	public void setInvoiceBillingAddress(String address) {
		billingAddress.set(address);
		generateAddressLines(getInvoiceBillingAdddressAsList(), billingAddressLines );
		
	}

	public String getInvoiceBillingAddress() {
		return billingAddress.get();
	}
	
	public SimpleStringProperty billingAddressProperty() {
		return billingAddress;
	}
	
	@XmlElementWrapper(name="BillingAddressLines")
	@XmlElement(name="Line")
	public ArrayList<AddressLine> getInvoiceBillingAddressLines(){
		return billingAddressLines;
	}
	/*invoice total*/
	
	public void setInvoiceTotal(Double total) {
		invoiceTotal.set(total);
	}
	@XmlElement
	public double getInvoiceTotal() {
		return invoiceTotal.get();
	}
	
	public SimpleDoubleProperty invoiceTotalProperty() {
		return invoiceTotal;
	}
	
	public String getInvoiceSummary() {
		String retString = "Invoice number: " + invoiceNumber.get() + " Date: " + invoiceDateString.get();
		return retString;
	}
	
	public InvoiceStatus getInvoiceStatus() {
		return this.invoiceStatus;
	}
	
	
	/*Invoice Row*/
	
	@XmlElementWrapper(name="InvoiceRows")
	@XmlElement(name="row")
	public ObservableList<InvoiceRow> getInvoiceRows(){
		return invoiceRows;
	}
	
	public void addInvoiceRow() {
		
	}
	
	public void setInvoiceRow(ObservableList<InvoiceRow> invoiceRows) {
		this.invoiceRows = invoiceRows;
	}
		
	
	/*Status*/
	public void setInvoiceStatusSaved() {
		this.invoiceStatus = InvoiceStatus.SAVED;
		
	}

	public void setProfileHeader(String header) {
		this.profileHeader = header;
		
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
		
	}

	
	
	
}