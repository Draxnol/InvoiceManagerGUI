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
		String sql = "SELECT * FROM profiles;";
		
		try {
			ResultSet rs = DatabaseConnection.dbQuery(sql);
			ObservableList<Profile> profileList = getProfileObList(rs);
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
		String sql = "INSERT INTO profiles(profileName, profileAddress, profileHeader, profileInvoiceCount) VALUES (?,?,?,?)";		
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setString(1, profile.getProfileName());
			pstmt.setString(2, profile.getProfileAddress());
			pstmt.setString(3, profile.getProfileHeader());
			pstmt.setInt(4, profile.getProfileInvoiceCount());
			pstmt.execute();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateProfile(Profile selectedProfile) {
		String sql = "UPDATE profiles set profileName = ? ,"
				+ "profileAddress = ?, "
				+ "profileHeader = ?";
		
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setString(1, selectedProfile.getProfileName());
			pstmt.setString(2, selectedProfile.getProfileAddress());
			pstmt.setString(3, selectedProfile.getProfileHeader());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void deleteProfile(Profile selectedProfile) throws SQLException{
		String sql = "DELETE FROM profiles WHERE profileID = ?";
		try {
			DatabaseConnection.dbConnect();
			PreparedStatement pstmt = DatabaseConnection.connection.prepareStatement(sql);
			pstmt.setInt(1,selectedProfile.getProfileID());
			pstmt.execute();
			DatabaseConnection.dbDisconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
