package draxnol.InvoiceManagerGUI;

import java.io.IOException;
import java.sql.SQLException;

import draxnol.contact.Contact;
import draxnol.contact.ContactManager;
import draxnol.database.utilDAO;
import draxnol.invoice.Invoice;
import draxnol.invoice.InvoiceDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
	private ListView<Invoice> invoicesListView;

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
	private Button btnSave;

	@FXML
	private Button btnLoadDB;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	private void initialize() {
		labelSelectedContact.textProperty().bind(ContactManager.getInstance().contactLabel);

		labelSelectedContact.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				loadSelectedContactInvoices();
				populateGUI();

			}

		});
		invoicesListView.setCellFactory(param -> new ListCell<Invoice>() {
			@Override
			protected void updateItem(Invoice invoice, boolean empty) {
				super.updateItem(invoice, empty);

				if (empty || invoice == null || invoice.getInvoiceSummary() == null) {
					setText(null);
				} else {
					setText(invoice.getInvoiceSummary());
				}
			}

		});

		invoicesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println("Invoice Selection changed");
			Invoice currentInvoice = invoicesListView.getSelectionModel().getSelectedItem();
			try {
				dateFieldDate.getEditor().setText(currentInvoice.getInvoiceDateString());
				textFieldInvoiceNumber.setText(String.valueOf(currentInvoice.getInvoiceNumber()));
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			}
		});

	}

	private void populateGUI() {
		Contact currentContact = ContactManager.getInstance().getContact();
		textFieldBillTo.setText(currentContact.getContactBillingAddress());

	}

	private void loadSelectedContactInvoices() {
		System.out.println("Contact changed");
		int contactID = ContactManager.getInstance().getContact().getContactID();
		try {
			invoicesListView.setItems(InvoiceDAO.loadSelectedContactInvoices(contactID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public PrimaryController() {

	}

	@FXML
	private void openContactManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("contactManager.fxml"));
		Scene ContactManagerScene = new Scene(fxmlLoader.load());
		Stage contactStage = new Stage();
		contactStage.setTitle("Contact Manager");
		contactStage.setScene(ContactManagerScene);
		contactStage.show();

	}

	@FXML
	private void loadDB() {
		utilDAO.createInvoiceTable();
	}

	@FXML
	private void addNewInvoice() {
		if (ContactManager.getInstance().getContact() != null) {
			int invoiceCount = ContactManager.getInstance().getContact().getContactInvoiceCount();
			invoicesListView.getItems().add(new Invoice(invoiceCount));
			ContactManager.getInstance().getContact().incrementInvoiceCount();
		}
	}

	@FXML
	private void deleteSelectedInvoice() {
		int currentSelectionIndex = invoicesListView.getSelectionModel().getSelectedIndex();
		try {

			invoicesListView.getItems().remove(currentSelectionIndex);
			ContactManager.getInstance().getContact().decrementInvoiceCount();

		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}

	}
}
