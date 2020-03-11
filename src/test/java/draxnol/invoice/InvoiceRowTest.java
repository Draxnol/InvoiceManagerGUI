package draxnol.invoice;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class InvoiceRowTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetInvoiceID() {
		InvoiceRow instance = new InvoiceRow();
		instance.setInvoiceID(5);
		assertEquals(5, instance.getInvoiceID());
	}

	@Test
	public void testGetRowID() {
		InvoiceRow instance = new InvoiceRow();
		instance.setRowID(1);
		assertEquals(1, instance.getRowID());
	}

	@Test
	public void testGetRowNumber() {
		InvoiceRow instance = new InvoiceRow();
		instance.setRowNumber(12);
		assertEquals(12, instance.getRowNumber());
	}

	@Test
	public void testSetUnitInfoSimpleStringProperty() {
		InvoiceRow instance = new InvoiceRow();
		instance.unitInfoProperty().set("unit 1232");
		assertEquals("unit 1232", instance.unitInfoProperty().get());
		
	}

	@Test
	public void testSetUnitDescSimpleStringProperty() {
		InvoiceRow instance = new InvoiceRow();
		instance.unitDescProperty().set("built a wall");
		assertEquals("built a wall", instance.unitDescProperty().get());
	}

	@Test
	public void testSetUnitCostSimpleDoubleProperty() {
		InvoiceRow instance = new InvoiceRow();
		instance.unitCostProperty().set(45.43);
		assertEquals(45.43, instance.unitCostProperty().get(),0.1 );
	}

	@Test
	public void testUnitInfoProperty() {
		InvoiceRow instance = new InvoiceRow();
		assertTrue(instance.unitInfoProperty() instanceof SimpleStringProperty);
		
	}

	@Test
	public void testGetUnitInfo() {
		InvoiceRow instance = new InvoiceRow();
		instance.setUnitInfo("123");
		assertEquals("123",instance.getUnitInfo());
		
	}


	@Test
	public void testUnitDescProperty() {
		InvoiceRow instance = new InvoiceRow();
		assertTrue(instance.unitDescProperty() instanceof SimpleStringProperty);
		
	}

	@Test
	public void testGetUnitDesc() {
		InvoiceRow instance = new InvoiceRow();
		instance.setUnitDesc("built a wall");
		assertEquals("built a wall", instance.getUnitDesc());
	}


	@Test
	public void testUnitCostProperty() {
		InvoiceRow instance = new InvoiceRow();
		assertTrue(instance.unitCostProperty() instanceof SimpleDoubleProperty);
		
	}

	@Test
	public void testGetUnitCost() {
		InvoiceRow instance = new InvoiceRow();
		instance.setUnitCost(12.55);
		assertEquals(12.55, instance.getUnitCost(),0.1);
	}



}
