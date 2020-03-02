package draxnol.contact;

import java.sql.ResultSet;
import java.sql.SQLException;

import draxnol.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactDAO {
	public static ObservableList<Contact> loadAllContactsDB() throws SQLException {
		System.out.println("Loading contacts....");
		String sql = "SELECT * FROM contacts;";
		try {
			ResultSet rs = DatabaseConnection.dbQuery(sql);
			ObservableList<Contact> contactList = getContactObList(rs);
			DatabaseConnection.dbDisconnect();
			rs.close();
			return contactList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}

	}

	public static ObservableList<Contact> getContactObList(ResultSet rs) throws SQLException {
		ObservableList<Contact> contacts = FXCollections.observableArrayList();

		try {
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setContactID(rs.getInt("contactID"));
				contact.setContactAlias(rs.getString("contactAlias"));
				contact.setContactName(rs.getString("contactName"));
				contact.setContactInvoiceCount(rs.getInt("contactInvoiceCount"));
				contact.setContactBillingAddress("contactBillingAddress");
				contacts.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contacts;
	}

	public static void insertNewContact(
			int contactInvoiceCount,
			String contactName,
			String contactAlias,
			String contactBillingAddress) {
		String sql = "INSERT INTO contacts(\n"
				+ "contactInvoiceCount"
				+ "contactName"
				+ "contactAlias"
				+ "contactBillingAddress"
				+ ")VALUES("
				+ "" + contactName +""
				+ "" + contactInvoiceCount +""
				+ "" + contactAlias +""
				+ "" + contactBillingAddress +""	
				+ ");" ;
	}
	
}
