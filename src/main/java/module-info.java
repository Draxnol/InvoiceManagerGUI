module draxnol.InvoiceManagerGUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires org.slf4j;
	requires java.xml.bind;
	requires docx4j.core;
	
    opens draxnol.InvoiceManagerGUI to javafx.fxml, java.xml.bind;
    exports draxnol.InvoiceManagerGUI;
    exports draxnol.contact to draxnol.InvoiceManagerGUI;
    opens draxnol.invoice to javafx.base,java.xml.bind; 

}

