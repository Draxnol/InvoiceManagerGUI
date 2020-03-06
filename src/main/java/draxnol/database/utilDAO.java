package draxnol.database;

import java.sql.SQLException;

public class utilDAO {

	public static void createContactTable() {
		String sqlString = "CREATE TABLE IF NOT EXISTS contacts (\n"
				+ "		contactID integer PRIMARY KEY, \n"
				+ "		contactInvoiceCount integer NOT NULL,\n"	
				+ "		contactName text NOT NULL, \n"
				+ "		contactAlias text NOT NULL, \n"
				+ "		contactBillingAddress text NOT NULL,"
				+ "		contactBusinessNumber text,"
				+ "		contactPhoneNumber text,"
				+ "		contactEmailAddress text"
				+ ");";			
		try {
			DatabaseConnection.updateDBStructure(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void createInvoiceTable() {
		
		String sqlString = "CREATE TABLE IF NOT EXISTS invoices(\n"
				+ "invoiceID INTEGER PRIMARY KEY, \n"
				+ "contactID INTEGER not null, \n"
				+ "invoiceNumber INTEGER not null,\n"
				+ "payableAddress  TEXT not null, \n"
				+ "billingAddress  TEXT not null,\n"
				+ "invoiceDateString  TEXT not null,"
				+ "invoiceTotal INTEGER not null,"
				+ "	FOREIGN KEY(contactID)\r\n" + 
				"		REFERENCES contacts(contactID)\r\n" + 
				"		ON DELETE CASCADE\r\n" + 
				"		ON UPDATE NO ACTION);";
		try {
			DatabaseConnection.updateDBStructure(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void createInvoiceRowTable() {
		String sqlString = "CREATE TABLE IF NOT EXISTS";
}


}