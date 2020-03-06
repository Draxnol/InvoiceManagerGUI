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
		String sql = "SELECT * FROM contacts WHERE contactID = "+ contactID +";";
		try {
			ResultSet rs = DatabaseConnection.dbQuery(sql);
			ObservableList<Invoice> contactList = getContactObList(rs);
			DatabaseConnection.dbDisconnect();
			rs.close();
			return contactList;
		} catch (SQLException e) {
			throw e;
		}

	}

	public static ObservableList<Invoice> getContactObList(ResultSet rs) throws SQLException {
		ObservableList<Invoice> invoices = FXCollections.observableArrayList();

		try {
			while (rs.next()) {
				Invoice invoice = new Invoice();
				invoice.setInvoiceID(rs.getInt("invoiceID"));
				invoice.setContactID(rs.getInt("contactID"));
				invoices.add(invoice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return invoices;
	}
	
	
}
