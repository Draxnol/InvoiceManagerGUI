package draxnol.InvoiceManagerGUI;

import java.io.IOException;
import java.lang.ProcessHandle.Info;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
	private Button btnSelectProfile;

	@FXML
	private Button btnExport;
	
	@FXML
	private Label labelSelectedProfile;

	@FXML
	private Button btnLoadDB;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	private static final String DATEPATTERN = "yyyy-MM-dd";

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
		unitCostCol.setOnEditCommit((CellEditEvent<InvoiceRow, Double> t) -> ((InvoiceRow) t.getTableView().getItems()
				.get(t.getTablePosition().getRow())).setUnitCost(t.getNewValue()));
		tableViewInvoiceTable.getColumns().addAll(unitInfoCol, unitDescCol, unitCostCol);

		/* Selected contact label */
		labelSelectedContact.textProperty().bind(InvoiceManagerHelper.getInstance().contactLabel);

		/* Action fires when contact is changed */
		labelSelectedContact.textProperty().addListener((ob, ov, nv) -> {
			clearGUI();
			loadSelectedContactInvoices();
			populateGUI();
			
		});

		labelSelectedProfile.textProperty().bind(InvoiceManagerHelper.getInstance().profileLabel);
		labelSelectedProfile.textProperty().addListener((ob, ov, nv) -> {
			
			
			textFieldBillingPayable.setText(InvoiceManagerHelper.getInstance().getProfile().getProfileAddress());
			clearGUI();

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
			
			Invoice currentInvoice = invoicesListView.getSelectionModel().getSelectedItem();
			try {
				dateFieldDate.getEditor().setText(currentInvoice.getInvoiceDateString());
				textFieldInvoiceNumber.setText(String.valueOf(currentInvoice.getInvoiceNumber()));
				currentInvoice.setInvoiceRow(InvoiceDAO.loadInvoiceRows(currentInvoice));
				System.out.println("Current invoice = "+ currentInvoice.getInvoiceRows());
				tableViewInvoiceTable.setItems(currentInvoice.getInvoiceRows());
			} catch (IndexOutOfBoundsException | SQLException | NullPointerException e) {
				
				//TODO EXECPTION HANDLING
				System.out.println(e);
			}
		});

		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATEPATTERN);

			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return dateFormatter.format(date);
				} else {
					return "";
				}
			}

			@Override
			public LocalDate fromString(String string) {
				if (string != null && !string.isEmpty()) {
					return LocalDate.parse(string, dateFormatter);
				} else {
					return null;
				}
			}
		};

		dateFieldDate.setConverter(converter);
		dateFieldDate.setPromptText(DATEPATTERN.toLowerCase());
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
			
		}

	}

	public void clearGUI() {
		invoicesListView.setItems(null);
		tableViewInvoiceTable.setItems(null);
		textFieldBillTo.setText("");
		dateFieldDate.getEditor().setText("");
		textFieldInvoiceNumber.setText("");
	}

	/* Contact Manager */
	@FXML
	private void openContactManager() throws IOException {
		if (InvoiceManagerHelper.getInstance().getProfile() != null) {

			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("contactManager.fxml"));
			Scene ContactManagerScene = new Scene(fxmlLoader.load());
			Stage contactStage = new Stage();
			contactStage.setTitle("Contact Manager");
			contactStage.setScene(ContactManagerScene);
			contactStage.show();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Please select a profile");
			alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> formatSystem());
		}
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
			System.out.println("invoice count = " + invoiceCount);
			System.out.println("listview= " + invoicesListView);
			
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

			if (dateFieldDate.getEditor().getText() == null) {
				selectedInvoice.setDate(java.time.LocalDate.now().toString());
			}
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
	
	
	
	
	@FXML
	private void openExportManager() {
		if(InvoiceManagerHelper.getInstance().getContact()!=null) {
			System.out.println(invoicesListView.getItems());
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ExportManager.fxml"));	
			Stage stage = new Stage();
			try {
				stage.setScene(new Scene(fxmlLoader.load()));
				ExportManagerController exportManager = fxmlLoader.<ExportManagerController>getController();
				exportManager.initData(invoicesListView.getItems());
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
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

	private Object formatSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Profile Manager */
	@FXML
	private void openProfileManager() {

		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("profileManager.fxml"));
		Scene profileScene;
		try {
			profileScene = new Scene(fxmlLoader.load());
			Stage profileStage = new Stage();
			profileStage.setTitle("Profile Manager");
			profileStage.setScene(profileScene);
			profileStage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
