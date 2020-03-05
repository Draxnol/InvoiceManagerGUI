package draxnol.contact;

import javafx.beans.property.SimpleStringProperty;

public class ContactManager {

private final static ContactManager instance = new ContactManager();
	public SimpleStringProperty contactLabel = new SimpleStringProperty("No contact selected");
	
	public static ContactManager getInstance() {
		return instance;
	}
	
	private Contact contact;
	
	public Contact getContact() {
		return contact;
	}
	
	public void setContact(Contact selectedContact) {
		contact = selectedContact;
		
	}
	
	public void updateLabel() {
		contactLabel.set(contact.getContactAlias());
	}
	
	
}
