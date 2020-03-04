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
	private TextField textFieldContactName;

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
	private TextField textFieldContactBusinessNumber;

	@FXML
	private Button btnSave;

	@FXML
	private void initialize() throws SQLException {

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
		contactListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println("Something changed.");
			try {
				int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
				Contact selectedContact = contactListView.getItems().get(selectedIndex);
				textFieldContactName.setText(selectedContact.getContactName());
				textFieldAlias.setText(selectedContact.getContactAlias());
				textFieldCount.setText(String.valueOf(selectedContact.getContactID()));
				textFieldPhoneNumber.setText(selectedContact.getContactPhoneNumber());
				textFieldEmail.setText(selectedContact.getContactEmailAddress());
				textFieldContactBusinessNumber.setText(selectedContact.getContactBusinessNumber());
				textAreaBillingAddress.setText(selectedContact.getContactBillingAddress());
			
			
			}catch(IndexOutOfBoundsException e){
				System.out.println(e);
			}
			
			

		});

	}

	@FXML
	private void newContact() {
		contactListView.getItems().add(new Contact("new contact"));
	}

	@FXML
	private void saveContact() {
		int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
		Contact selectedContact = contactListView.getItems().get(selectedIndex);
		selectedContact.setContactAlias(textFieldAlias.getText());
		selectedContact.setContactName(textFieldContactName.getText());
		selectedContact.setContactInvoiceCount(5);
		selectedContact.setContactPhoneNumber(textFieldPhoneNumber.getText());
		selectedContact.setContactEmailAddress(textFieldEmail.getText());
		selectedContact.setContactBusinessNumber(textFieldContactBusinessNumber.getText());
		selectedContact.setContactBillingAddress(textAreaBillingAddress.getText());
		System.out.println("Updating db");
		ContactDAO.updateContact(selectedContact);
		try {
			contactListView.setItems(ContactDAO.loadAllContactsDB());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
