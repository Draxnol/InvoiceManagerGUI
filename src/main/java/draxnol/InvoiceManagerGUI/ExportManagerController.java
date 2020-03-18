package draxnol.InvoiceManagerGUI;

import java.io.File;
import java.io.IOException;

import draxnol.export.ExportInvoice;
import draxnol.invoice.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExportManagerController {
	private DirectoryChooser directoryChooser;
	private Stage exportStage;
	private PrimaryController primeController;
	private ObservableList<Invoice> invoiceObList;
	@FXML
    private ListView<Invoice> exportListView = new ListView<>();

    @FXML
    private Button btnDirSelect;

    @FXML
    private TextField textAreaDirectory;

    @FXML
    private Button btnExport;

    @FXML
    private void exportSelectedInvoices() {
    	exportListView.getItems().add(new Invoice());
    }

    @FXML
    private void openDirBrowser() {
    	directoryChooser = new DirectoryChooser();
    	File  selectedDirectory = directoryChooser.showDialog(btnDirSelect.getScene().getWindow());
    	textAreaDirectory.setText(selectedDirectory.toString());
    }
    
    @FXML
    private void initialize() {  	
		exportListView.setCellFactory(param -> new ListCell<Invoice>() {
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
		
		exportListView.getItems().add(new Invoice());
	 	
    }
    
    public ExportManagerController() {}
    
    

	public void initData(ObservableList<Invoice> items) {
		System.out.println(items);
		exportListView.getItems().addAll(items);
	}

}


