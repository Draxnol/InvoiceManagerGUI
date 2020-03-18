package draxnol.export;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class ExportInvoiceRow {
		private String rowUnit;
		private String rowDesc;
		private String rowCost;
		
		ExportInvoiceRow(String rowUnit, String rowDesc, String rowCost){
			this.rowUnit = rowUnit;
			this.rowDesc = rowDesc;
			this.rowCost = rowCost;
		}
		
		
		public void setRowUnit(String rowUnit) {
			this.rowUnit = rowUnit;
		}
		@XmlElement
		public String getRowUnit() {
			return rowUnit;
		}
		
		
		public void setRowDesc(String rowDesc) {
			this.rowDesc = rowDesc;
		}
		@XmlElement
		public String getRowDesc() {
			return rowDesc;
		}
		
		@XmlElement
		public void setRowCost(String rowCost) {
			this.rowCost = rowCost;
		}
		
		public String getRowCost() {
			return rowCost;
		}
		
}

