package draxnol.contact;

import java.sql.PreparedStatement;
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

	public static void updateContact(Contact contact) {
	
//		System.out.println(contact.toString());
//		String sql = "UPDATE contacts "
//				+ "SET contactInvoiceCount = ?, "
//				+ "contactName = ?, "
//				+ "contactAlias = ?, "
//				+ "contactBillingAddress= ?, "
//				+ "contactBusinessNumber = ?, "
//				+ "contactPhoneNumber = ? "
//				+ "WHERE contactID = ?; ";
	
		
		String sql = "UPDATE contacts "
				+ "Set contactInvoiceCount = " + contact.getContactInvoiceCount()
				+ ", contactName = '" + contact.getContactName()
				+ "', contactAlias = '" + contact.getContactAlias()
				+ "', contactBillingAddress = '" + contact.getContactBillingAddress()
				+ "', contactBusinessNumber = '" + contact.getContactBusinessNumber()
				+ "', contactPhoneNumber = '" + contact.getContactPhoneNumber()
				+ "', contactEmailAddress = '" + contact.getContactEmailAddress()
				+ "' WHERE contactID = " + contact.getContactID()
				+ ";" ;
		System.out.println(sql);
		try {
			DatabaseConnection.dbConnect();
			Statement stmt = DatabaseConnection.connection.createStatement();
			stmt.execute(sql);
			
			
			
//			PreparedStatement stmt = DatabaseConnection.connection.prepareStatement(sql);						
//			stmt.setInt(1, 5);
//			stmt.setString(2, contact.getContactName());
//			stmt.setString(3, contact.getContactAlias());
//			stmt.setString(4, contact.getContactBillingAddress());
//			stmt.setString(5, contact.getContactBusinessNumber());
//			stmt.setString(6, contact.getContactPhoneNumber());
//			
//			stmt.execute();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
				
	}
	
}
