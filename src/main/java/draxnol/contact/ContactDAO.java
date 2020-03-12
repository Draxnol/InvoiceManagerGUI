package draxnol.contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				contact.setContactBillingAddress(rs.getString("contactBillingAddress"));
				contact.setContactEmailAddress(rs.getString("contactEmailAddress"));
				contact.setContactPhoneNumber(rs.getString("contactPhoneNumber"));
				contact.setContactBusinessNumber(rs.getString("contactBusinessNumber"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return contacts;
	}

	public static void insertNewContact(Contact contact) throws SQLException {
		String sql = "INSERT INTO contacts(contactInvoiceCount,contactName,contactAlias,contactBillingAddress,contactBusinessNumber,contactPhoneNumber,contactEmailAddress) VALUES("
				+ "" + contact.getContactInvoiceCount() + ",'" + contact.getContactName() + "','"
				+ contact.getContactAlias() + "','" + contact.getContactBillingAddress() + "','"
				+ contact.getContactBusinessNumber() + "','" + contact.getContactPhoneNumber() + "','"
				+ contact.getContactEmailAddress() + "')" + ";";
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

	public static void updateContact(Contact contact) {
		// TODO Use prepared statements instead.

		String sql = "UPDATE contacts " + "Set contactInvoiceCount = " + contact.getContactInvoiceCount()
				+ ", contactName = '" + contact.getContactName() + "', contactAlias = '" + contact.getContactAlias()
				+ "', contactBillingAddress = '" + contact.getContactBillingAddress() + "', contactBusinessNumber = '"
				+ contact.getContactBusinessNumber() + "', contactPhoneNumber = '" + contact.getContactPhoneNumber()
				+ "', contactEmailAddress = '" + contact.getContactEmailAddress() + "' WHERE contactID = "
				+ contact.getContactID() + ";";
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

	public static void deleteContact(Contact selectedContact) throws SQLException {
		String sql = "DELETE FROM contacts WHERE contactID ='" + selectedContact.getContactID() + "'";

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

	public static void updateContactInvoiceCount() {
	
	}

}
