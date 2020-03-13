package draxnol.InvoiceManagerGUI;

import draxnol.contact.Contact;
import draxnol.profile.Profile;
import javafx.beans.property.SimpleStringProperty;

public class InvoiceManagerHelper {

private final static InvoiceManagerHelper instance = new InvoiceManagerHelper();
	public SimpleStringProperty contactLabel = new SimpleStringProperty("No contact selected");
	public SimpleStringProperty profileLabel = new SimpleStringProperty("No profile Selected");
	
	public static InvoiceManagerHelper getInstance() {
		return instance;
	}
	
	
	private Profile profile;
	private Contact contact;
	boolean profileChanged;
	public Contact getContact() {
		return contact;
	}
	public Profile getProfile() {
		return profile;
	}
	
	
	public void setContact(Contact selectedContact) {
		contact = selectedContact;
		
	}
	
	public void updateContactLabel() {
		contactLabel.set(contact.getContactAlias());
	}
	
	public void updateProfileLabel() {
		profileLabel.set(profile.getProfileName());
	}
	public void resetContactLabel() {
		contactLabel.set("No contact selected");
		profileChanged = true;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
