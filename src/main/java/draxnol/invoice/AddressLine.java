
package draxnol.invoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class AddressLine {
	private String lineContent;
	private int addressLineID;
	
	public AddressLine(String i, int count) {
		this.lineContent = i;
		this.addressLineID = count;
	}

	public void setContent(String content) {
		this.lineContent = content;
	}
	
	@XmlElement
	public String getLineContent(){
		return lineContent;
	}
	
	public void setAddressLineID(int num) {
		this.addressLineID = num;
	}
	@XmlAttribute
	public int getAddressLineID() {
		return addressLineID;
	}


}