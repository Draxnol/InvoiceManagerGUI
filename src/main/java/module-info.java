module draxnol.InvoiceManagerGUI {
    requires javafx.controls;
    requires javafx.fxml;

    opens draxnol.InvoiceManagerGUI to javafx.fxml;
    exports draxnol.InvoiceManagerGUI;
}