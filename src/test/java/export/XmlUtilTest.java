package export;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import draxnol.export.XmlUtil;

public class XmlUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateXml() {
		XmlUtil instance = new XmlUtil();
		File fileTest = new File("./invoice.xml");
		try {
			instance.marshal();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(fileTest.exists());
	
	}

}
