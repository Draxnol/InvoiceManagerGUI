package draxnol.invoice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

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
	


	
	public Invoice() {
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
	
	public String getInvoiceDateString() {
		return invoiceDateString.get();
	}

	/*Invoice id*/
	
	public void setInvoiceID(int id) {
		this.invoiceID.set(id);
	}
	
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
	public double getInvoiceTotal() {
		return invoiceTotal.get();
	}
	public SimpleDoubleProperty invoiceTotalProperty() {
		return invoiceTotal;
	}
	
	
}

