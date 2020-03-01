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
				+ "		contactPhoneNumber text"
				+ ");";			
		try {
			DatabaseConnection.updateDBStructure(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void createInvoiceTable() {
		
		String sqlString = "CREATE TABLE IF NOT EXISTS(\n"
				+ "invoiceID integer PRIMARY KEY, \n"
				+ "invoice																																																														qqqqqqqqqq34SCFV CCF CXFSX6XC UYXCV "
				+ ""
				+ ""
				+ "";
		
	}

	public static void createInvoiceRowTable() {
		String sqlString = "CREATE TABLE IF NOT EXISTS";
}


}