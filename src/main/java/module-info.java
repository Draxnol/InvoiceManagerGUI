module draxnol.InvoiceManagerGUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;

    opens draxnol.InvoiceManagerGUI to javafx.fxml;
    exports draxnol.InvoiceManagerGUI;
    exports draxnol.contact to draxnol.InvoiceManagerGUI;
 
}

