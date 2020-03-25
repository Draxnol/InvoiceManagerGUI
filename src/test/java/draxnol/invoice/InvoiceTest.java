package draxnol.invoice;



import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import draxnol.contact.Contact;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class InvoiceTest {
	
	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testSetInvoiceDate() {
		Invoice instance = new Invoice();
		instance.setDate("3/5/2020");
		assertEquals("3/5/2020", instance.getInvoiceDateString());
	}
	@Test
	public void testGetInvoiceDateObject() {
		//TODO add real time handling
		Invoice instance = new Invoice();
		instance.setDate("3/5/2020");
		//System.out.println(instance.getInvoiceDate());
	}
	
	@Test
	public void testSetInvoiceID() {
		Invoice instance = new Invoice();
		instance.setInvoiceID(5);
		assertEquals(5, instance.getInvoiceID());
		
	}
	@Test
	public void testGetInvoiceIDProperty() {
		Invoice instance = new Invoice();
		instance.invoiceIDProperty().set(5);
		assertEquals(5,instance.invoiceIDProperty().get()); 
		assertTrue(instance.invoiceIDProperty() instanceof SimpleIntegerProperty);;
	
	}

	
	@Test
	public void testGetContactID() {
		Invoice instance = new Invoice();
		assertEquals(0, instance.getContactID());
	}

	@Test
	public void testSetContactID() {
		Invoice instance = new Invoice();
		instance.setContactID(5);
		assertEquals(5, instance.getContactID());
	}
	@Test
	public void	testContactIDProperty() {
		Invoice instance = new Invoice();
		instance.contactIDProperty().set(0);
		assertEquals(0,instance.invoiceIDProperty().get()); 
		assertTrue(instance.contactIDProperty() instanceof SimpleIntegerProperty);
		}
	
	@Test
	public void testSetInvoiceNumber() {
		Invoice instance = new Invoice();
		instance.setInvoiceNumber(1);
		assertEquals(1, instance.getInvoiceNumber());
		
	}
	
	@Test
	public void testInvoiceNumberProperty() {
		Invoice instance = new Invoice();
		instance.invoiceNumberProperty().set(0);
		assertTrue(instance.invoiceNumberProperty() instanceof SimpleIntegerProperty);
		assertEquals(0,instance.invoiceNumberProperty().get());
		
	}
	
	@Test
	public void testSetPayableAddress() {
		Invoice instance = new Invoice();
		instance.setInvoicePayableAddress("fake street");
		assertEquals("fake street", instance.getInvoicePayableAddress());
	}
	
	@Test
	public void testPayableAddressProperty() {
		Invoice instance = new Invoice();
		instance.payableAddressProperty().set("fake street");
		assertTrue(instance.payableAddressProperty() instanceof SimpleStringProperty);
		assertEquals("fake street", instance.payableAddressProperty().get());
	}
	
	@Test
	public void testSetBillingAddress() {
		Invoice instance = new Invoice();
		instance.setInvoiceBillingAddress("fake street");
		assertEquals("fake street", instance.getInvoiceBillingAddress());
		
	}
	
	@Test
	public void testBillingAddressProperty() {
		Invoice instance = new Invoice();
		instance.billingAddressProperty().set("fake street");
		assertTrue(instance.billingAddressProperty() instanceof SimpleStringProperty);
		assertEquals("fake street", instance.billingAddressProperty().get());
		
	}
	
	@Test
	public void testSetInvoiceTotal() {
		Invoice instance = new Invoice();
		instance.setInvoiceTotal(5.0);
		assertEquals(5.0 ,instance.getInvoiceTotal(), 0.1);
		
	}
	@Test
	public void testInvoiceTotalProperty() {
		Invoice instance = new Invoice();
		instance.invoiceTotalProperty().set(5.0);
		assertTrue(instance.invoiceTotalProperty() instanceof SimpleDoubleProperty);
		assertEquals(5.0, instance.invoiceTotalProperty().get(), 0.1);
	}

	@Test
	public void testCalcInvoiceTotal() {
		Invoice instance = new Invoice();
		instance.getInvoiceRows().add(new InvoiceRow("","",500,1));
		instance.getInvoiceRows().add(new InvoiceRow("","",250,1));
		assertEquals(750.0, instance.calcInvoiceTotal(), 0.1);
	}
	
}
