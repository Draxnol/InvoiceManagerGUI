package draxnol.InvoiceManagerGUI;

import java.sql.SQLException;

import draxnol.contact.Contact;
import draxnol.contact.ContactDAO;
import draxnol.contact.Contact.ContactStatus;
import draxnol.profile.Profile;
import draxnol.profile.Profile.ProfileStatus;
import draxnol.profile.ProfileDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ProfileManagerController {

	@FXML
	private ListView<Profile> listViewProfile;

	@FXML
	private TextField textFieldProfileName;

	@FXML
	private TextField textFieldProfileHeader;

	@FXML
	private TextArea textFieldProfileAddress;

	@FXML
	private Button btnSave;

	@FXML
	private Button btnSelect;

	@FXML
	private Button btnNew;

	@FXML
	private Button btnDelete;

	public void initialize() throws SQLException {
		System.out.println("Initialize method");
		System.out.println(ProfileDAO.loadAllProfiles());

		listViewProfile.setItems(ProfileDAO.loadAllProfiles());

		listViewProfile.setCellFactory(param -> new ListCell<Profile>() {
			@Override
			protected void updateItem(Profile profile, boolean empty) {
				super.updateItem(profile, empty);

				if (empty || profile == null || profile.getProfileSummary() == null) {
					setText(null);
				} else {
					setText(profile.getProfileSummary());
				}
			}
		});

		listViewProfile.getSelectionModel().selectedItemProperty().addListener((obs, oldval, newval) -> {
			System.out.println("Profile changed");
			try {

				Profile selectedProfile = getSelectedProfile();
				System.out.println(selectedProfile.getProfileHeader());
				textFieldProfileAddress.setText(selectedProfile.getProfileAddress());
				textFieldProfileHeader.setText(selectedProfile.getProfileHeader());
				textFieldProfileName.setText(selectedProfile.getProfileName());
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e);
			} catch (NullPointerException e) {

			}

		});

	}

	@FXML
	private void newProfile() {
		Profile newProfile = new Profile();
		newProfile.status = ProfileStatus.NEW;
		listViewProfile.getItems().add(newProfile);

	}

	@FXML
	private void saveProfile() throws SQLException {
		try {
			Profile selectedProfile = getSelectedProfile();
			selectedProfile.setProfileAddress(textFieldProfileAddress.getText());
			selectedProfile.setProfileHeader(textFieldProfileHeader.getText());
			selectedProfile.setProfileName(textFieldProfileName.getText());

			if (selectedProfile.status == ProfileStatus.NEW) {
				selectedProfile.status = ProfileStatus.CREATED;
				ProfileDAO.insertNewProfile(selectedProfile);
			} else {
				ProfileDAO.updateProfile(selectedProfile);
			}

			try {
				listViewProfile.setItems(ProfileDAO.loadAllProfiles());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			showNoProfileSelectedAlert();
		}

	}

	@FXML
	private void selectProfile() {
		try {
			InvoiceManagerHelper.getInstance().setProfile(getSelectedProfile());
			InvoiceManagerHelper.getInstance().updateProfileLabel();
			//InvoiceManagerHelper.getInstance().setContact(null);
			Stage stage = (Stage) btnSelect.getScene().getWindow();
			stage.close();
		} catch (NullPointerException e) {
			showNoProfileSelectedAlert();
		}

	}

	@FXML
	private void deleteProfile() {

		try {
			ProfileDAO.deleteProfile(getSelectedProfile());
			listViewProfile.getItems().remove(getSelectedProfile());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			showNoProfileSelectedAlert();
		}
	}

	private Profile getSelectedProfile() {
		try {
			int selectedIndex = listViewProfile.getSelectionModel().getSelectedIndex();
			Profile selectedProfile = listViewProfile.getItems().get(selectedIndex);
			return selectedProfile;
		} catch (IndexOutOfBoundsException e) {

			return null;
		}

	}

	private void showNoProfileSelectedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("No profile selected");
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {

			}

		});
	}
}
