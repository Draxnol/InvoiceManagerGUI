package draxnol.InvoiceManagerGUI;



import java.sql.SQLException;

import draxnol.contact.Contact;
import draxnol.contact.ContactDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class ContactManagerController {

		@FXML
	    private ListView<Contact> contactListView;
		
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
	    
	    @FXML
	    private void initialize() throws SQLException{
	    	
	    	System.out.println("Initialize method");
	    	System.out.println(ContactDAO.loadAllContactsDB());
	    	contactListView.setItems(ContactDAO.loadAllContactsDB());
	    	contactListView.setCellFactory(param -> new ListCell<Contact>() {
				@Override
				protected void updateItem(Contact contact, boolean empty) {
					super.updateItem(contact, empty);

					if (empty || contact == null || contact.getContactSummary() == null) {
						setText(null);
					} else {
						setText(contact.getContactSummary());
					}
				}
	    	
	    	});
	    }
	    
}
