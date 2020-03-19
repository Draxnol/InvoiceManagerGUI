package draxnol.invoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
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
	private Date invoiceDate;
	private SimpleDateFormat dateFormat;
	private SimpleStringProperty invoiceDateString;
	public InvoiceStatus invoiceStatus;
	
	@XmlTransient
	public ObservableList<InvoiceRow> invoiceRows = FXCollections.observableArrayList();
	
	
	public enum InvoiceStatus{
		NOT_SAVED,
		SAVED,
	}

	
	public Invoice() {
		init();
	}
	
	public Invoice(int invoiceNumber, int contactID) {
		init();
		this.invoiceStatus = InvoiceStatus.NOT_SAVED;
		this.invoiceNumber.set(invoiceNumber);
		this.contactID.set(contactID);
	}
	
	private void init() {
		this.dateFormat = new SimpleDateFormat();
		this.invoiceID = new SimpleIntegerProperty();
		this.contactID = new SimpleIntegerProperty();
		this.invoiceNumber = new SimpleIntegerProperty();
		this.invoiceDate = new Date();
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

	public Date getInvoiceDate() {
		try {
			Date returnDate = dateFormat.parse(this.invoiceDateString.get());
			return returnDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
	}
	@XmlElement
	public String getInvoicePayableAddress() {
		return payableAddress.get();
	}
	
	public SimpleStringProperty payableAddressProperty() {
		return payableAddress;
	}
	
	/* Billing address */
	public void setInvoiceBillingAddress(String address) {
		billingAddress.set(address);
	}
	@XmlElement
	public String getInvoiceBillingAddress() {
		return billingAddress.get();
	}
	
	public SimpleStringProperty billingAddressProperty() {
		return billingAddress;
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

}

