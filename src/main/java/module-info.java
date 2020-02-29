module draxnol.InvoiceManagerGUI {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens draxnol.InvoiceManagerGUI to javafx.fxml;
    exports draxnol.InvoiceManagerGUI;
}