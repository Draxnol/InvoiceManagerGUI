package draxnol.invoice;

import java.sql.PreparedStatement;
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
		String sql = "SELECT * FROM invoices WHERE contactID = ?";
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, contactID);
			ResultSet rs = pstmt.executeQuery();
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
		int rowNumber = invoiceRows.size() + 1;
		String sql;
		System.out.println("invoiceID is " + invoiceID);
		
		
		for(InvoiceRow row : invoiceRows) {
			try {
				DatabaseConnection.dbConnect();
				PreparedStatement pstmt;
				if(row.getInvoiceRowStatus() == InvoiceRowStatus.NOT_SAVED) {
					 sql = "INSERT INTO invoiceRows\n" + 
						"(invoiceID, rowNumber, unitInfo, unitDesc, unitCost)\n" + 
						"VALUES(?,?,?,?,?)"; 
					
					pstmt = DatabaseConnection.connection.prepareStatement(sql);
					pstmt.setInt(1, invoiceID);
					pstmt.setInt(2, rowNumber);
					pstmt.setString(3, row.getUnitInfo());
					pstmt.setString(4, row.getUnitDesc());
					pstmt.setDouble(5, row.getUnitCost());
						
				
				}else {
					sql = "UPDATE invoiceRows "
					 		+ " set unitInfo = ?, unitDesc = ?, unitCost = ? WHERE invoiceID = ?";
					pstmt = DatabaseConnection.connection.prepareStatement(sql);
					pstmt.setString(1, row.getUnitInfo());
					pstmt.setString(2, row.getUnitDesc());
					pstmt.setDouble(3, row.getUnitCost());
					pstmt.setInt(4, invoiceID);
				}
				pstmt.execute();			
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
				+ "Set payableAddress = ?, billingAddress = ?," 
				+ "invoiceDateString = ? WHERE invoiceID = ?";
				
		System.out.println(sql);
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setString(1, selectedInvoice.getInvoicePayableAddress());
			pstmt.setString(2, selectedInvoice.getInvoiceBillingAddress());
			pstmt.setString(3, selectedInvoice.getInvoiceDateString());
			pstmt.setInt(4, selectedInvoice.getInvoiceID());
			pstmt.execute();			
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static int addNewInvoice(Invoice selectedInvoice) {
		String sql = "INSERT INTO invoices\n" + 
				"(contactID, invoiceNumber, payableAddress, billingAddress, invoiceDateString, invoiceTotal)\r\n"
				+ "VALUES(?,?,?,?,?,?)" ; 
		String sql2  = "SELECT last_insert_rowid()";
		System.out.println(sql);
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1,selectedInvoice.getContactID());
			pstmt.setInt(2, selectedInvoice.getInvoiceNumber());
			pstmt.setString(3, selectedInvoice.getInvoicePayableAddress());
			pstmt.setString(4, selectedInvoice.getInvoiceBillingAddress());
			System.out.println(selectedInvoice.getInvoiceDateString());
			pstmt.setString(5, selectedInvoice.getInvoiceDateString());
			pstmt.setDouble(6, selectedInvoice.getInvoiceTotal());
			pstmt.execute();
			pstmt = DatabaseConnection.connection.prepareStatement(sql2);
			int invoiceID = pstmt.getGeneratedKeys().getInt(1);
			System.out.println(invoiceID);
			return invoiceID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void deleteInvoice(Invoice selectedInvoice) {
		String sql = "DELETE FROM invoices WHERE invoiceID = ?";
		String sqlRow = "DELETE FROM invoiceRows where invoiceID = ?";
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1,selectedInvoice.getInvoiceID());
			pstmt.execute();			
			pstmt = DatabaseConnection.connection.prepareStatement(sqlRow);
			pstmt.setInt(1,selectedInvoice.getInvoiceID());
			pstmt.execute();	
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ObservableList<InvoiceRow> loadInvoiceRows(int invoiceID) throws SQLException {
		String sql = "SELECT * FROM invoiceRows where invoiceID = ?";
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, invoiceID);
			ResultSet rs = pstmt.executeQuery();;
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

	public static void deleteRow(int rowID) {
		String sql = "DELETE FROM invoiceRows WHERE rowID = ?";
		
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, rowID);			
			pstmt.execute();			
			
			
			
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteInvoiceByContactID(Contact selectedContact) {
		String sql = "DELETE FROM invoices WHERE contactID = ?";
		
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, selectedContact.getContactID());			
			pstmt.execute();			
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
