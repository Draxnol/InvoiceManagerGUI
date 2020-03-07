package draxnol.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import draxnol.contact.Contact;
import draxnol.database.DatabaseConnection;
import draxnol.invoice.Invoice.InvoiceStatus;
import draxnol.invoice.InvoiceRow.InvoiceRowStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvoiceDAO {

	public static ObservableList<Invoice> loadSelectedContactInvoices(int contactID) throws SQLException {
		System.out.println("Loading contacts....");
		String sql = "SELECT * FROM invoices WHERE contactID = "+ contactID +";";
		try {
			ResultSet rs = DatabaseConnection.dbQuery(sql);
			ObservableList<Invoice> contactList = getInvoiceObList(rs);
			DatabaseConnection.dbDisconnect();
			rs.close();
			return contactList;
		} catch (SQLException e) {
			throw e;
		}

	}

	public static ObservableList<Invoice> getInvoiceObList(ResultSet rs) throws SQLException {
		ObservableList<Invoice> invoices = FXCollections.observableArrayList();

		try {
			while (rs.next()) {
				Invoice invoice = new Invoice();
				invoice.setInvoiceStatusSaved();
				invoice.setInvoiceID(rs.getInt("invoiceID"));
				invoice.setContactID(rs.getInt("contactID"));
				invoice.setDate(rs.getString("invoiceDateString"));
				invoice.setInvoiceBillingAddress("billingAddress");
				invoice.setInvoicePayableAddress("payableAddress");
				invoice.setInvoiceTotal(rs.getDouble("invoiceTotal"));
				invoice.setInvoiceNumber(rs.getInt("invoiceNumber"));
				invoices.add(invoice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invoices;
	}

	
	public static void saveRows(ObservableList<InvoiceRow> invoiceRows, int invoiceID) {
		//TODO Use prepared statements
		
		
		int rowNumber = invoiceRows.size() + 1;
		String sql;
		System.out.println("invoiceID is " + invoiceID);
		for(InvoiceRow row : invoiceRows) {
			if(row.getInvoiceRowStatus() == InvoiceRowStatus.NOT_SAVED) {
				 sql = "INSERT INTO invoiceRows\n" + 
					"(invoiceID, rowNumber, unitInfo, unitDesc, unitCost)\n" + 
					"VALUES("+ invoiceID + "," + rowNumber + "," + "'"+row.getUnitInfo() +"','" + row.getUnitDesc()+ "'," + 0 + ");\n" + 
					"";
			}else {
				 sql = "UPDATE invoiceRows "
				 		+ " set unitInfo = '" + row.getUnitInfo()+ "',"
				 		+ " unitDesc = '" + row.getUnitDesc() + "'," 
				 		+ " unitCost = "  + row.getUnitCost() 
				 		+ " WHERE invoiceID = " + invoiceID
				 		+ ";";
			}

			System.out.println(sql);
			try {
				DatabaseConnection.dbConnect();
				Statement stmt = DatabaseConnection.connection.createStatement();
				stmt.execute(sql);			
				DatabaseConnection.dbDisconnect();
				rowNumber += 1;
				System.out.println("SQL EXECUTRED");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void updateInvoice(Invoice selectedInvoice) {
		String sql = "UPDATE invoices "
				+ "Set payableAddress = '" + selectedInvoice.getInvoicePayableAddress()
				+ "', billingAddress = '" + selectedInvoice.getInvoiceBillingAddress()
				+ "', invoiceDateString ='" + selectedInvoice.getInvoiceDateString()
				+ "' WHERE invoiceID = " + selectedInvoice.getInvoiceID()
				+ ";";
		System.out.println(sql);
		try {
			DatabaseConnection.dbConnect();
			Statement stmt = DatabaseConnection.connection.createStatement();
			stmt.execute(sql);			
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void addNewInvoice(Invoice selectedInvoice) {
		String sql = "INSERT INTO invoices\n" + 
				"(contactID, invoiceNumber, payableAddress, billingAddress, invoiceDateString, invoiceTotal)\r\n" + 
				"VALUES("
				+ "" + selectedInvoice.getContactID() 
				+"," + selectedInvoice.getInvoiceNumber()
				+ ",'" + selectedInvoice.getInvoicePayableAddress()
				+ "','" + selectedInvoice.getInvoiceBillingAddress()
				+ "','" + selectedInvoice.getInvoiceDateString()
				+ "'," + 0 
				+ ")\n" + 
				";";
		System.out.println(sql);
		try {
			DatabaseConnection.dbConnect();
			Statement stmt = DatabaseConnection.connection.createStatement();
			stmt.execute(sql);			
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteInvoice(Invoice selectedInvoice) {
		String sql = "DELETE FROM invoices WHERE invoiceID = " + selectedInvoice.getInvoiceID();
		try {
			DatabaseConnection.dbConnect();
			Statement stmt = DatabaseConnection.connection.createStatement();
			stmt.execute(sql);			
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ObservableList<InvoiceRow> loadInvoiceRows(Invoice SelectedInvoice) throws SQLException {
		String sql = "SELECT * FROM invoiceRows where invoiceID = " + SelectedInvoice.getInvoiceID();
		try {
			ResultSet rs = DatabaseConnection.dbQuery(sql);
			ObservableList<InvoiceRow> invoiceRowList = getInvoiceRowsOBList(rs);
			DatabaseConnection.dbDisconnect();
			rs.close();
			return invoiceRowList;
		} catch (SQLException e) {
			throw e;
		}
	}
	

	
	public static ObservableList<InvoiceRow> getInvoiceRowsOBList(ResultSet rs){
		ObservableList<InvoiceRow> invoiceRows = FXCollections.observableArrayList();
		try {
			while(rs.next()) {
				
				InvoiceRow invoiceRow = new InvoiceRow();
				invoiceRow.setInvoiceRowStatusSaved();
				invoiceRow.setRowID(rs.getInt("rowID"));
				invoiceRow.setInvoiceID(rs.getInt("invoiceID"));
				invoiceRow.setRowNumber(rs.getInt("rowNumber"));
				invoiceRow.setUnitInfo(rs.getString("unitInfo"));
				invoiceRow.setUnitDesc(rs.getString("unitDesc"));
				invoiceRow.setUnitCost(rs.getDouble("unitCost"));
				invoiceRows.add(invoiceRow);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		
		
		return invoiceRows;
	}
	
}
