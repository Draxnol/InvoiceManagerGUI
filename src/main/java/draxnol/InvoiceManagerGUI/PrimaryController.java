package draxnol.InvoiceManagerGUI;

import java.io.IOException;

import draxnol.database.utilDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
	private Button btnSave;

	@FXML
	private Button btnLoadDB;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

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
		utilDAO.createContactTable();
	}

}
