package draxnol.profile;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testSetProfileHeaderProperty() {
		Profile instance = new Profile();
		instance.profileHeaderProperty().set("hi");
		assertEquals("hi", instance.profileHeaderProperty().get());
	}
	
	@Test
	public void testSetProfileHeader() {
		Profile instance = new Profile();
		instance.setProfileHeader("hello");
		assertEquals("hello", instance.getProfileHeader());
	}
	
	@Test
	public void testGetProfileID() {
		Profile instance = new Profile();
		instance.setProfileID(1);
		assertEquals(1, instance.getProfileID());
	}

	@Test
	public void testGetProfileInvoiceCount() {
		Profile instance = new Profile();
		instance.setProfileInvoiceCount(25);
		assertEquals(25, instance.getProfileInvoiceCount());
	}

	@Test
	public void testProfileNameProperty() {
		Profile instance = new Profile();
		instance.profileNameProperty().set("hello");
		assertEquals("hello", instance.profileNameProperty().get());
	}

	@Test
	public void testGetProfileName() {
		Profile instance = new Profile();
		instance.setProfileName("bob");
		assertEquals("bob", instance.getProfileName());
	}

	@Test
	public void testProfileAddressProperty() {
		Profile instance = new Profile();
		instance.profileAddress.set("home");
		assertEquals("home", instance.profileAddressProperty().get());
	}

	@Test
	public void testGetProfileAddress() {
		Profile instance = new Profile();
		instance.setProfileAddress("home");
		assertEquals("home", instance.getProfileAddress());
	}

}
