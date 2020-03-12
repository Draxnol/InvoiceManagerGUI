package draxnol.profile;


import javafx.beans.property.SimpleStringProperty;

public class Profile{
	
	int profileID;
	int profileInvoiceCount;
	SimpleStringProperty profileName;
	SimpleStringProperty profileAddress;
	SimpleStringProperty profileHeader;
	public ProfileStatus status;
	public enum ProfileStatus{
		NEW,
		CREATED
	}
	
	
	
	public Profile() {
		profileName = new SimpleStringProperty();
		profileAddress = new SimpleStringProperty();
		profileHeader = new SimpleStringProperty();
	}




	public int getProfileID() {
		return profileID;
	}




	public int getProfileInvoiceCount() {
		return profileInvoiceCount;
	}




	public void setProfileID(int profileID) {
		this.profileID = profileID;
	}




	public void setProfileInvoiceCount(int profileInvoiceCount) {
		this.profileInvoiceCount = profileInvoiceCount;
	}




	public SimpleStringProperty profileNameProperty() {
		return this.profileName;
	}
	




	public String getProfileName() {
		return this.profileNameProperty().get();
	}
	




	public void setProfileName(final String profileName) {
		this.profileNameProperty().set(profileName);
	}
	




	public SimpleStringProperty profileAddressProperty() {
		return this.profileAddress;
	}
	




	public String getProfileAddress() {
		return this.profileAddressProperty().get();
	}
	




	public void setProfileAddress(final String profileAddress) {
		this.profileAddressProperty().set(profileAddress);
	}




	public SimpleStringProperty profileHeaderProperty() {
		return this.profileHeader;
	}
	




	public String getProfileHeader() {
		return this.profileHeaderProperty().get();
	}
	




	public void setProfileHeader(final String profileHeader) {
		this.profileHeaderProperty().set(profileHeader);
	}




	public String getProfileSummary() {
		return "Name:" + getProfileName();
	}
	
	
	
	
}
