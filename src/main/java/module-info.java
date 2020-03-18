module draxnol.InvoiceManagerGUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	requires org.slf4j;
	requires java.xml.bind;
	
    opens draxnol.InvoiceManagerGUI to javafx.fxml, java.xml.bind;
    exports draxnol.InvoiceManagerGUI;
    exports draxnol.contact to draxnol.InvoiceManagerGUI;
    opens draxnol.invoice to javafx.base;
    opens draxnol.export to java.xml.bind;
}

