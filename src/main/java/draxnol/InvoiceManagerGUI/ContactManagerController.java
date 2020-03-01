package draxnol.InvoiceManagerGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContactManagerController {

		@FXML
	    private ListView<?> contactTableView;

	    @FXML
	    private Button btnNew;

	    @FXML
	    private Button btnDelete;

	    @FXML
	    private TextField textFieldContact;

	    @FXML
	    private TextField textFieldAlias;

	    @FXML
	    private TextField textFieldCount;

	    @FXML
	    private TextArea textAreaBillingAddress;

	    @FXML
	    private TextField textFieldPhoneNumber;

	    @FXML
	    private TextField textFieldEmail;

	    @FXML
	    private Button btnSave;
	
}
