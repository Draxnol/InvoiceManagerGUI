package draxnol.profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import draxnol.database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfileDAO {

	public static ObservableList<Profile> loadAllProfiles() throws SQLException{
		System.out.println("Loading profiles");
		String sql = "SELECT * FROM profiles;";
		
		try {
			ResultSet rs = DatabaseConnection.dbQuery(sql);
			ObservableList<Profile> profileList = getProfileObList(rs);
			System.out.println(profileList);
			DatabaseConnection.dbDisconnect();
			rs.close();
		
			return profileList;
		} catch (SQLException e) {
			throw e;
		}
		
		
		
	}

	private static ObservableList<Profile> getProfileObList(ResultSet rs) {
		ObservableList<Profile> profiles = FXCollections.observableArrayList();
		try {
			System.out.println("read db");
			while(rs.next()) {
				Profile profile = new Profile();
				profile.setProfileID(rs.getInt("profileID"));
				profile.setProfileName(rs.getString("profileName"));
				profile.setProfileAddress(rs.getString("profileAddress"));
				profile.setProfileHeader(rs.getString("profileHeader"));
				profile.setProfileInvoiceCount(rs.getInt("profileInvoiceCount"));
				profiles.add(profile);
			}
		
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		

		return profiles;
	}
	
	public static void insertNewProfile(Profile profile) {
		String sql = "INSERT INTO profiles(profileName, profileAddress, profileHeader, profileInvoiceCount) VALUES ('"
				+profile.getProfileName() +"', '"
						+ profile.getProfileAddress()
						+ "', '"
						+ profile.getProfileHeader()
						+ "'," + profile.getProfileInvoiceCount()
						+ ");";
	
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

	public static void updateProfile(Profile selectedProfile) {
		String sql = "UPDATE profiles set profileName = ? ,"
				+ "profileAddress = ?, "
				+ "profileHeader = ?";
		DatabaseConnection.dbConnect();
		try {
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setString(1, selectedProfile.getProfileName());
			pstmt.setString(2, selectedProfile.getProfileAddress());
			pstmt.setString(3, selectedProfile.getProfileHeader());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void deleteProfile(Profile selectedProfile) throws SQLException{
		String sql = "DELETE FROM profiles WHERE profileID = " +selectedProfile.getProfileID();
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
	
}
