package draxnol.InvoiceManagerGUI;

import java.io.IOException;
import java.sql.SQLException;

import draxnol.contact.Contact;
import draxnol.contact.ContactDAO;
import draxnol.database.utilDAO;
import draxnol.invoice.Invoice;
import draxnol.invoice.InvoiceDAO;
import draxnol.invoice.InvoiceRow;
import draxnol.invoice.Invoice.InvoiceStatus;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

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
	private TableView<InvoiceRow> tableViewInvoiceTable;

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
	private Button btnUserProfile;
	
	@FXML
	private Button btnLoadDB;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	private void initialize() {
		/* Invoice table view */
		tableViewInvoiceTable.setEditable(true);
		TableColumn<InvoiceRow, String> unitInfoCol = new TableColumn<>("unit");

		unitInfoCol.setCellValueFactory(cellData -> cellData.getValue().unitInfoProperty());

		unitInfoCol.setCellFactory(TextFieldTableCell.<InvoiceRow>forTableColumn());
		unitInfoCol.setOnEditCommit((CellEditEvent<InvoiceRow, String> t) -> {
			((InvoiceRow) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUnitInfo(t.getNewValue());

		});

		TableColumn<InvoiceRow, String> unitDescCol = new TableColumn<>("desc");

		unitDescCol.setCellValueFactory(cellData -> cellData.getValue().unitDescProperty());

		unitDescCol.setCellFactory(TextFieldTableCell.<InvoiceRow>forTableColumn());
		unitDescCol.setOnEditCommit((CellEditEvent<InvoiceRow, String> t) -> {
			((InvoiceRow) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUnitDesc(t.getNewValue());

		});

		TableColumn<InvoiceRow, Double> unitCostCol = new TableColumn<>("cost");

		unitCostCol.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
		unitCostCol.setCellValueFactory(cellData -> cellData.getValue().unitCostProperty().asObject());
		unitCostCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		unitCostCol.setOnEditCommit((CellEditEvent<InvoiceRow, Double> t) -> {
			((InvoiceRow) t.getTableView().getItems().get(t.getTablePosition().getRow())).setUnitCost(t.getNewValue());

		});
		tableViewInvoiceTable.getColumns().addAll(unitInfoCol, unitDescCol, unitCostCol);

		/* Selected contact label */
		labelSelectedContact.textProperty().bind(InvoiceManagerHelper.getInstance().contactLabel);

		/* Action fires when contact is changed */
		labelSelectedContact.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				loadSelectedContactInvoices();
				populateGUI();

			}

		});
		/* invoice list view */
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

		// This is where invoice selection happens.
		invoicesListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			System.out.println("Invoice Selection changed");
			Invoice currentInvoice = invoicesListView.getSelectionModel().getSelectedItem();
			try {
				dateFieldDate.getEditor().setText(currentInvoice.getInvoiceDateString());
				textFieldInvoiceNumber.setText(String.valueOf(currentInvoice.getInvoiceNumber()));
				currentInvoice.setInvoiceRow(InvoiceDAO.loadInvoiceRows(currentInvoice));
				System.out.println(currentInvoice.getInvoiceRows());
				tableViewInvoiceTable.setItems(currentInvoice.getInvoiceRows());
			} catch (IndexOutOfBoundsException | SQLException e) {
				System.out.println(e);
			}
		});

	}

	/* Support functions */
	private void populateGUI() {
		Contact currentContact = InvoiceManagerHelper.getInstance().getContact();
		textFieldBillTo.setText(currentContact.getContactBillingAddress());

	}

	private void loadSelectedContactInvoices() {
		System.out.println("Contact changed");
		int contactID = InvoiceManagerHelper.getInstance().getContact().getContactID();
		try {
			invoicesListView.setItems(InvoiceDAO.loadSelectedContactInvoices(contactID));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* Contact Manager */
	@FXML
	private void openContactManager() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("contactManager.fxml"));
		Scene ContactManagerScene = new Scene(fxmlLoader.load());
		Stage contactStage = new Stage();
		contactStage.setTitle("Contact Manager");
		contactStage.setScene(ContactManagerScene);
		contactStage.show();

	}

	/* Debug button */
	@FXML
	private void loadDB() {
		utilDAO.createInvoiceTable();
	}

	/* Invoice related buttons */
	@FXML
	private void addNewInvoice() {
		if (InvoiceManagerHelper.getInstance().getContact() != null) {
			int invoiceCount = InvoiceManagerHelper.getInstance().getContact().getContactInvoiceCount();
			invoicesListView.getItems()
					.add(new Invoice(invoiceCount, InvoiceManagerHelper.getInstance().getContact().getContactID()));
			InvoiceManagerHelper.getInstance().getContact().incrementInvoiceCount();
		}
	}

	@FXML
	private void deleteSelectedInvoice() {
		int currentSelectionIndex = invoicesListView.getSelectionModel().getSelectedIndex();
		try {

			invoicesListView.getItems().remove(currentSelectionIndex);
			InvoiceManagerHelper.getInstance().getContact().decrementInvoiceCount();

		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}

	}

	@FXML
	private void saveSelectedInvoice() {
		int currentSelectionIndex = invoicesListView.getSelectionModel().getSelectedIndex();
		try {
			Invoice selectedInvoice = invoicesListView.getItems().get(currentSelectionIndex);
			selectedInvoice.setDate(dateFieldDate.getEditor().getText());
			selectedInvoice.setInvoiceBillingAddress(textFieldBillTo.getText());
			selectedInvoice.setInvoicePayableAddress(textFieldBillingPayable.getText());

			if (selectedInvoice.getInvoiceStatus().equals(InvoiceStatus.SAVED)) {
				System.out.println("Invoice Status is saved");
				InvoiceDAO.updateInvoice(selectedInvoice);
			} else if (selectedInvoice.getInvoiceStatus().equals(InvoiceStatus.NOT_SAVED)) {
				System.out.println("Invoice Status is not saved");
				InvoiceDAO.addNewInvoice(selectedInvoice);
				ContactDAO.updateContactInvoiceCount();
			}

			InvoiceDAO.saveRows(selectedInvoice.getInvoiceRows(), selectedInvoice.getInvoiceID());

		} catch (IndexOutOfBoundsException e) {
			System.out.println(e);
		}

	}
	/* table row buttons */

	@FXML
	private void addNewRow() {
		if (InvoiceManagerHelper.getInstance().getContact() != null)
			tableViewInvoiceTable.getItems().add(new InvoiceRow("", "", 0));

	}

	@FXML
	private void deleteSelectedRow() {
		if (InvoiceManagerHelper.getInstance().getContact() != null) {
			int selectedIndex = tableViewInvoiceTable.getSelectionModel().getSelectedIndex();

			try {
				int rowID = tableViewInvoiceTable.getItems().get(selectedIndex).getRowID();
				tableViewInvoiceTable.getItems().remove(selectedIndex);
				InvoiceDAO.deleteRow(rowID);
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e + "throw a selection error at somepoint");
			}
		}
	}
	
	@FXML
	private void userProfile() {

		
	}
}
