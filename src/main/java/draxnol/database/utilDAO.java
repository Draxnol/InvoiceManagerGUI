package draxnol.database;

import java.sql.SQLException;

public class utilDAO {

	public static void createContactTable() {
		String sqlString = "CREATE TABLE IF NOT EXISTS contacts (\n"
				+ "profileID integer NOT NULL, \n"
				+ "contactID integer PRIMARY KEY, \n"
				+ "		contactInvoiceCount integer NOT NULL,\n"	
				+ "		contactName text NOT NULL, \n"
				+ "		contactAlias text NOT NULL, \n"
				+ "		contactBillingAddress text NOT NULL,"
				+ "		contactBusinessNumber text,"
				+ "		contactPhoneNumber text,"
				+ "		contactEmailAddress text,"
				+ "     FOREIGN KEY(profileID)\n"
				+ "		REFERENCES profiles(profileID)\n"
				+ "		ON DELETE CASECADE\n"
				+ "		ON UPDATE ACTION)"
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
				+ "invoiceTotal REAL not null,"
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
		String sqlString = "CREATE TABLE IF NOT EXISTS invoiceRows(\n"
				+ "rowID INTEGER PRIMARY KEY, \n"
				+ "invoiceID INTEGER NOT NULL, \n"
				+ "rowNumber INTEGER NOT NULL, \n"
				+ "unitInfo TEXT NOT NULL, \n"
				+ "unitDesc TEXT NOT NULL, \n"
				+ "unitCost REAL NOT NULL,"
				+ "FOREIGN KEY(invoiceID)\r\n"
				+ "		REFERENCES invoices(invoiceID)\r\n"
				+ "		ON DELETE CASCADE"
				+ "		ON UPDATE NO ACTION);";
		try {
			DatabaseConnection.updateDBStructure(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	public static void createProfileTable() {
		String sqlString = "CREATE TABLE IF NOT EXISTS profiles(\n"
				+ "profileID INTEGER PRIMARY KEY, \n"
				+ "profileName TEXT NOT NULL,"
				+ "profileHeader TEXT NOT NULL,"
				+ "profileAddress TEXT NOT NULL,"
				+ "profileInvoiceCount INTEGER NOT NULL"
				+ ");";
		
		try {
			DatabaseConnection.updateDBStructure(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}