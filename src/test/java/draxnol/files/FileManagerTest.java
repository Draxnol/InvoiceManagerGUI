package draxnol.files;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import draxnol.InvoiceManagerGUI.App;
import draxnol.files.FileManager;

public class FileManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void checkIfConfigExitsTest() {
		FileManager instance = new FileManager();
		assertFalse(instance.checkConfigFileExists());
	}
	@Test
	public void checkIfDbExistsTest() {
		FileManager instance =new FileManager();
		assertFalse(instance.checkDBfileExists());
		
	}
	
	@Test
	public void createDBFileTest() {
		FileManager instance = new FileManager();
		instance.createDBFile();
		assertTrue(instance.checkDBfileExists());
	}
	
	
	@Test
	public void getDatabaseNameTest() {
		FileManager instance = new FileManager();
		assertEquals("invoiceDatabase.db", instance.getDatabaseName());
	}
	@Test
	public void getConfigFileNameTest() {
		FileManager instance = new FileManager();
		assertEquals("config.txt", instance.getConfigFileName());
	}
}
