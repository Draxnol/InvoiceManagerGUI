package draxnol.contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import draxnol.InvoiceManagerGUI.InvoiceManagerHelper;
import draxnol.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactDAO {
	public static ObservableList<Contact> loadAllContactsDB() throws SQLException {
		String sql = "SELECT * FROM contacts where profileID = ?";
		try {
			
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1,InvoiceManagerHelper.getInstance().getProfile().getProfileID());
			ResultSet rs = pstmt.executeQuery();
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
		String sql = "INSERT INTO contacts("
				+ "profileID, contactInvoiceCount,contactName,contactAlias,"
				+ "contactBillingAddress,contactBusinessNumber,contactPhoneNumber"
				+ ",contactEmailAddress)"
				+ " VALUES(?,?,?,?,?,?,?,?)";
		try {		
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, contact.getProfileID());
			pstmt.setInt(2, contact.getContactInvoiceCount());
			pstmt.setString(3, contact.getContactName());
			pstmt.setString(4, contact.getContactAlias());
			pstmt.setString(5,contact.getContactBillingAddress());
			pstmt.setString(6, contact.getContactBusinessNumber());
			pstmt.setString(7, contact.getContactPhoneNumber());
			pstmt.setString(8, contact.getContactEmailAddress());
			pstmt.execute();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateContact(Contact contact) {
		String sql = "UPDATE contacts SET  contactInvoiceCount= ?, contactName= ? , contactAlias= ?, contactBillingAddress= ?, contactBusinessNumber= ? , contactPhoneNumber= ?,"
				+ " contactEmailAddress= ? WHERE contactID= ?";
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, contact.getContactInvoiceCount());
			pstmt.setString(2, contact.getContactName());
			pstmt.setString(3, contact.getContactAlias());
			pstmt.setString(4, contact.getContactBillingAddress());
			pstmt.setString(5, contact.getContactBusinessNumber());
			pstmt.setString(6, contact.getContactPhoneNumber());
			pstmt.setString(7, contact.getContactEmailAddress());
			pstmt.setInt(8, contact.getContactID());
			pstmt.execute();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteContact(Contact selectedContact) throws SQLException {
		String sql = "DELETE FROM contacts WHERE contactID = ?";
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

	public static void updateContactInvoiceCount(Contact contact) {
		String sql = "UPDATE contacts SET contactInvoiceCount= ?";
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1, contact.getContactInvoiceCount());
			pstmt.execute();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
	}

}
