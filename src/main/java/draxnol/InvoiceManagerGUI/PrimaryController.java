package draxnol.InvoiceManagerGUI;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PrimaryController {


    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Button btnSelectContact;

    @FXML
    private Label labelSelectedContact;

    @FXML
    private Font x11;

    @FXML
    private Color x21;

    @FXML
    private ListView<?> listViewInvoiceTable;

    @FXML
    private Button btnAddInvoice;

    @FXML
    private Button btnDeleteInvoice;

    @FXML
    private TableView<?> tableViewInvoiceTable;

    @FXML
    private TextArea textFieldBillingPayable;

    @FXML
    private TextArea textFieldBillTo;

    @FXML
    private DatePicker dateFieldDate;

    @FXML
    private TextField textFieldInvoiceNumber;

    @FXML
    private Button btnNewRow;

    @FXML
    private Button btnDeleteRow;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
	
    @FXML
    private void switchToSecondary() throws IOException {
       System.out.println("");
    }
}
