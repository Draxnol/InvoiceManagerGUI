package draxnol.InvoiceManagerGUI;

import java.sql.SQLException;

import draxnol.contact.Contact;
import draxnol.contact.Contact.ContactStatus;
import draxnol.contact.ContactDAO;
import draxnol.invoice.InvoiceDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContactManagerController {

	@FXML
	private ListView<Contact> contactListView;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnSelect;

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
		System.out.println("Current profile is " + InvoiceManagerHelper.getInstance().getProfile());
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
			System.out.println("Contact Selection Changed.");
			try {
				int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
				Contact selectedContact = contactListView.getItems().get(selectedIndex);
				textFieldContactName.setText(selectedContact.getContactName());
				textFieldAlias.setText(selectedContact.getContactAlias());
				textFieldCount.setText(String.valueOf(selectedContact.getContactInvoiceCount()));
				textFieldPhoneNumber.setText(selectedContact.getContactPhoneNumber());
				textFieldEmail.setText(selectedContact.getContactEmailAddress());
				textFieldContactBusinessNumber.setText(selectedContact.getContactBusinessNumber());
				textAreaBillingAddress.setText(selectedContact.getContactBillingAddress());

			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			}

		});

	}

	@FXML
	private void newContact() {
		Contact newContact = new Contact();
		newContact.status = ContactStatus.NEW;
		contactListView.getItems().add(newContact);

	}

	@FXML
	private void selectContact() {
		
		
		try {
			InvoiceManagerHelper.getInstance().setContact(getSelectedContact());
			InvoiceManagerHelper.getInstance().updateContactLabel();
			Stage stage = (Stage) btnSelect.getScene().getWindow();
			stage.close();
		} catch (NullPointerException e) {
			noContactSelectedAlert();
			
		}
	}

	@FXML
	private void deleteContact() {

		try {
			ContactDAO.deleteContact(getSelectedContact());
			InvoiceDAO.deleteInvoiceByContactID(getSelectedContact());
			contactListView.getItems().remove(getSelectedContact());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}catch(NullPointerException e) {
			noContactSelectedAlert();
		}
	}

	@FXML
	private void saveContact() {
		// TODO Input validation
		try {
			Contact selectedContact = getSelectedContact();
			selectedContact.setProfileID(InvoiceManagerHelper.getInstance().getProfile().getProfileID());
			selectedContact.setContactAlias(textFieldAlias.getText());
			selectedContact.setContactName(textFieldContactName.getText());
			selectedContact.setContactInvoiceCount(Integer.valueOf(textFieldCount.getText()));
			selectedContact.setContactPhoneNumber(textFieldPhoneNumber.getText());
			selectedContact.setContactEmailAddress(textFieldEmail.getText());
			selectedContact.setContactBusinessNumber(textFieldContactBusinessNumber.getText());
			selectedContact.setContactBillingAddress(textAreaBillingAddress.getText());
			if (selectedContact.status == ContactStatus.NEW) {

				try {
					selectedContact.status = ContactStatus.CREATED;
					ContactDAO.insertNewContact(selectedContact);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				ContactDAO.updateContact(selectedContact);
			}

			System.out.println("Updating db");

			try {
				contactListView.setItems(ContactDAO.loadAllContactsDB());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NullPointerException e) {
			noContactSelectedAlert();
		}
	}
	private Contact getSelectedContact() {
		try {
			int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
			Contact selectedContact = contactListView.getItems().get(selectedIndex);
			return selectedContact;
		} catch (Exception e) {
			return null;
		}
	}
	
	private void noContactSelectedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("No contact selected"); 
		alert.showAndWait()
	      .filter(response -> response == ButtonType.OK);
	 
	}
	
	
	

}
