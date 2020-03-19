package draxnol.InvoiceManagerGUI;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import draxnol.files.XMLUtill;
import draxnol.invoice.Invoice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExportManagerController {
	private DirectoryChooser directoryChooser;
	private Stage exportStage;
	private PrimaryController primeController;
	private ObservableList<Invoice> invoiceObList;
	private File selectedDirectory;
	
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
    	if(selectedDirectory != null) {
    		ObservableList<Invoice> exportList = exportListView.getSelectionModel().getSelectedItems();
    		for(Invoice invoice : exportList) {
    			try {
					XMLUtill.marshal(invoice, selectedDirectory);
				} catch (JAXBException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    	
    	}
    
    
    }

    @FXML
    private void openDirBrowser() {
    	directoryChooser = new DirectoryChooser();
    	selectedDirectory = directoryChooser.showDialog(btnDirSelect.getScene().getWindow());
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
			 	
		exportListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
    
    }
    
    public ExportManagerController() {}
    
    

	public void initData(ObservableList<Invoice> items) {
		exportListView.getItems().addAll(items);
	}

}


