package draxnol.contact;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ContactTest {

	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testSetContactID() {
		Contact instance = new Contact();
		instance.setContactID(5);
		assertEquals(5, instance.getContactID());
	}
	
	@Test
	public void testContactIDProperty() {
		Contact instance = new Contact();
		instance.setContactID(5);
		assertTrue(instance.contactIDProperty() instanceof SimpleIntegerProperty);
		assertEquals(5, instance.contactIDProperty().get());
	
	}

	@Test
	public void testSetContactInvoiceCount() {
		Contact instance = new Contact();
		instance.setContactInvoiceCount(5);
		assertEquals(5, instance.getContactInvoiceCount());
	}
	
	@Test
	public void testSetContactInvoiceCountNegative() {
		Contact instance = new Contact();
		instance.setContactInvoiceCount(-5);
		assertEquals(0, instance.getContactInvoiceCount());
	}
	
	@Test
	public void testContactInvoiceCountProperty() {
		Contact instance = new Contact();
		instance.setContactInvoiceCount(5);
		assertTrue(instance.contactInvoiceCountProperty() instanceof SimpleIntegerProperty);
		assertEquals(5, instance.contactInvoiceCountProperty().get());
	}

	@Test
	public void testContactInvoiceCountIncrement() {
		Contact instance = new Contact();
		instance.setContactInvoiceCount(5);
		instance.incrementInvoiceCount();
		assertEquals(6, instance.getContactInvoiceCount());
	
	}
	
	@Test
	public void testContactInvoiceCountDecrement() {
		Contact instance = new Contact();
		instance.setContactInvoiceCount(5);
		instance.decrementInvoiceCount();
		assertEquals(4, instance.getContactInvoiceCount());
	}
	
	@Test
	public void testContactInvoiceCountDecrementAtZero() {
		Contact instance = new Contact();
		instance.setContactInvoiceCount(0);
		instance.decrementInvoiceCount();
		assertEquals(0, instance.getContactInvoiceCount());
	
	
	}

	@Test
	public void testSetContactName() {
		Contact instance = new Contact();
		instance.setContactName("Bob");
		assertEquals("Bob", instance.getContactName());
	
	}
	
	@Test
	public void testContactNameProperty() {
		Contact instance = new Contact();
		instance.setContactName("Bob");
		assertEquals(instance.contactNameProperty().get(), "Bob");
		assertTrue(instance.contactNameProperty() instanceof SimpleStringProperty);
	}

	@Test
	public void testSetContactAlias() {
		Contact instance = new Contact();
		instance.setContactAlias("Bobs cafe");
		assertEquals("Bobs cafe", instance.getContactAlias());
	
	}
	
	@Test
	public void testContactAliasProperty() {
		Contact instance = new Contact();
		instance.setContactAlias("Bobs cafe");
		assertEquals(instance.contactAliasProperty().get(), "Bobs cafe");
		assertTrue(instance.contactAliasProperty() instanceof SimpleStringProperty);
	}

	@Test
	public void testSetContactBillingAddress() {
		Contact instance = new Contact();
		instance.setContactBillingAddress("123 fake street.");
		assertEquals("123 fake street.", instance.getContactBillingAddress());
	
	}
	
	@Test
	public void testContactBillingAddressProperty() {
		Contact instance = new Contact();
		instance.setContactBillingAddress("123 fake street.");
		assertEquals(instance.contactBillingAddressProperty().get(), "123 fake street.");
		assertTrue(instance.contactBillingAddressProperty() instanceof SimpleStringProperty);
	}
	
	@Test
	public void testSetContactPhoneNumber() {
		Contact instance = new Contact();
		instance.setContactPhoneNumber("4199921231");
		assertEquals("4199921231", instance.getContactPhoneNumber());
	
	}
	
	@Test
	public void testContactPhoneNumberProperty() {
		Contact instance = new Contact();
		instance.setContactPhoneNumber("4199921231");
		assertEquals(instance.contactPhoneNumberProperty().get(), "4199921231");
		assertTrue(instance.contactPhoneNumberProperty() instanceof SimpleStringProperty);
	}
	
	@Test
	public void testSetContactBusinessNumber() {
		Contact instance = new Contact();
		instance.setContactBusinessNumber("1234567");
		assertEquals("1234567", instance.getContactBusinessNumber());
	
	}
	
	@Test
	public void testContactBusinessNumberProperty() {
		Contact instance = new Contact();
		instance.setContactBusinessNumber("1234567");
		assertEquals(instance.contactBusinessNumberProperty().get(), "1234567");
		assertTrue(instance.contactBusinessNumberProperty() instanceof SimpleStringProperty);
	}
	
	@Test
	public void testSetContactEmailAddress() {
		Contact instance = new Contact();
		instance.setContactEmailAddress("fake@fake.com");
		assertEquals("fake@fake.com", instance.getContactEmailAddress());
	
	}
	
	@Test
	public void testContactEmailAddressProperty() {
		Contact instance = new Contact();
		instance.setContactEmailAddress("fake@fake.com");
		assertEquals(instance.contactEmailAddress().get(), "fake@fake.com");
		assertTrue(instance.contactEmailAddress() instanceof SimpleStringProperty);
	}


}
