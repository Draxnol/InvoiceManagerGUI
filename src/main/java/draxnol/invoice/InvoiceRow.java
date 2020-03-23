package draxnol.invoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class InvoiceRow {

	private int invoiceID;
	private int rowID;
	private int rowNumber;
	private InvoiceRowStatus invoiceRowStatus;
	
	enum InvoiceRowStatus{
		SAVED,
		NOT_SAVED
	}
	
	private SimpleStringProperty unitInfo;
	private SimpleStringProperty unitDesc;
	private SimpleDoubleProperty unitCost;
	
	
	public InvoiceRow() {
		this.unitInfo = new SimpleStringProperty();
		this.unitDesc = new SimpleStringProperty();
		this.unitCost = new SimpleDoubleProperty();
		
	}

	public InvoiceRow(String unitInfo, String unitDesc, int unitCost, int invoiceID)
	{
		this.invoiceID = invoiceID;
		this.unitInfo = new SimpleStringProperty(unitInfo);
		this.unitDesc = new SimpleStringProperty(unitDesc);
		this.unitCost = new SimpleDoubleProperty(unitCost);
		this.invoiceRowStatus = InvoiceRowStatus.NOT_SAVED;
	}	
	@XmlElement
	public int getInvoiceID() {
		return invoiceID;
	}

	@XmlAttribute
	public int getRowID() {
		return rowID;
	}
	@XmlElement
	public int getRowNumber() {
		return rowNumber;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public void setRowID(int rowID) {
		this.rowID = rowID;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public void setUnitInfo(SimpleStringProperty unitInfo) {
		this.unitInfo = unitInfo;
	}

	public void setUnitDesc(SimpleStringProperty unitDesc) {
		this.unitDesc = unitDesc;
	}

	public void setUnitCost(SimpleDoubleProperty unitCost) {
		this.unitCost = unitCost;
	}

	public SimpleStringProperty unitInfoProperty() {
		return this.unitInfo;
	}
	
	@XmlElement
	public String getUnitInfo() {
		return this.unitInfoProperty().get();
	}
	

	public void setUnitInfo(String unitInfo) {
		this.unitInfoProperty().set(unitInfo);
	}
	

	public SimpleStringProperty unitDescProperty() {
		return this.unitDesc;
	}
	
	@XmlElement
	public String getUnitDesc() {
		return this.unitDescProperty().get();
	}
	

	public void setUnitDesc(String unitDesc) {
		this.unitDescProperty().set(unitDesc);
	}
	

	public SimpleDoubleProperty unitCostProperty() {
		return this.unitCost;
	}
	
	@XmlElement
	public double getUnitCost() {
		return this.unitCostProperty().get();
	}
	

	public void setUnitCost(double unitCost) {
		this.unitCostProperty().set(unitCost);
	}
	
	public void setUnitCost(String unitCost) {
		double temp = Double.valueOf(unitCost);
		this.unitCostProperty().set(temp);
	}
	
	public void setInvoiceRowStatusSaved() {
		this.invoiceRowStatus = InvoiceRowStatus.SAVED;
	}
	
	public void setInvoiceRowStatusUnsaved() {
		this.invoiceRowStatus = InvoiceRowStatus.NOT_SAVED;
	}
	
	public InvoiceRowStatus getInvoiceRowStatus() {
		return this.invoiceRowStatus;
	}

}
