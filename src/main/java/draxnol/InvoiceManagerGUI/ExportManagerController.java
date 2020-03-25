package draxnol.InvoiceManagerGUI;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import draxnol.files.DocxUtill;
import draxnol.files.XMLUtill;
import draxnol.invoice.Invoice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ExportManagerController {
	private DirectoryChooser directoryChooser;
	private Stage exportStage;
	private PrimaryController primeController;
	private ObservableList<Invoice> invoiceObList;
	private File selectedDirectory;
	private File savePath;
	@FXML
    private ListView<Invoice> exportListView = new ListView<>();

    @FXML
    private Button btnDirSelect;

    @FXML
    private TextField textAreaDirectory;

    @FXML
    private Button btnExport;

    @FXML
    private RadioButton radioBtnXml;
    
    @FXML
    private RadioButton radioBtnDocx;

    @FXML
    private ToggleGroup exportOptionGroup;
    
    @FXML
    private void exportSelectedInvoices() {
    	if(!checkIfDirExists(savePath) && savePath != null)
    		createDir(savePath);	
    	
    	if(savePath != null) {
    		if(radioBtnXml.isSelected()) {
    			exportToXmlFiles();
    		}
    		if(radioBtnDocx.isSelected()) {
    			exportTodocxFiles();
    		}
    		
    	
    	}
    
    
    }

    private void createDir(File dir) {
    	dir.mkdirs();
    }
    
    private Boolean checkIfDirExists(File dir) {
    	if(dir.exists()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    private void exportToXmlFiles() {
		ObservableList<Invoice> exportList = exportListView.getSelectionModel().getSelectedItems();
		for(Invoice invoice : exportList) {
			try {
				setInvoiceInformation(invoice);
				XMLUtill.marshal(invoice, savePath);
			} catch (JAXBException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    	
    }
    private void exportTodocxFiles() {
    	ObservableList<Invoice> exportList = exportListView.getSelectionModel().getSelectedItems();
    	for(Invoice invoice : exportList) {
    		setInvoiceInformation(invoice);
    		DocxUtill.exportTodocx(invoice, new File("C:\\Users\\robert\\Documents\\temp1.docx"), savePath);
    	}
    
    
    
    }
    
    private void setInvoiceInformation(Invoice invoice) {
    	invoice.setProfileHeader(InvoiceManagerHelper.getInstance().getProfile().getProfileHeader());
		invoice.setContactName(InvoiceManagerHelper.getInstance().getContact().getContactName());
		invoice.setInvoiceBillingAddress(InvoiceManagerHelper.getInstance().getContact().getContactBillingAddress());
		invoice.setInvoicePayableAddress(InvoiceManagerHelper.getInstance().getProfile().getProfileAddress());
    }
    
    @FXML
    private void openDirBrowser() {
    	directoryChooser = new DirectoryChooser();
    	selectedDirectory = directoryChooser.showDialog(btnDirSelect.getScene().getWindow());
    	String actualDirectory = selectedDirectory.toString() + "\\" + InvoiceManagerHelper.getInstance().getProfile().getProfileName();
    	actualDirectory = actualDirectory + "\\" + InvoiceManagerHelper.getInstance().getContact().getContactAlias() + "\\";
    	textAreaDirectory.setText(actualDirectory);
    	savePath = new File(actualDirectory); 
    	
    
    
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
