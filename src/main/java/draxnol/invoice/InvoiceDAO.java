package draxnol.invoice;

import java.sql.ResultSet;
import java.sql.SQLException;

import draxnol.contact.Contact;
import draxnol.database.DatabaseConnection;
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
	
	
}
