package draxnol.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	public static Connection connection = null;

	public static void dbConnect() {

		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/draxnol/database/testdb.db");

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			System.err.println(e.getMessage());

		}

	}

	public static void dbDisconnect() throws SQLException {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static ResultSet dbQuery(String statement) throws SQLException {
		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			dbConnect();
			System.out.println("Query Statement = " + statement + ".");
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(statement);

		} catch (SQLException e) {
			throw e;
		}
		return resultSet;
	}

	public static void updateDB(PreparedStatement statement) throws SQLException {

		try {
			dbConnect();
			statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			dbDisconnect();

		}
	}

	public static void updateDBStructure(String statement) throws SQLException {
		PreparedStatement stmt = null;
		try {
			dbConnect();
			stmt = connection.prepareStatement(statement);
			stmt.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			dbDisconnect();

		}
	}
}
