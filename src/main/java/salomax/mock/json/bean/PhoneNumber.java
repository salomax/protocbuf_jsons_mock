/**
 * 
 */
package salomax.mock.json.bean;

/**
 * @author salomao.marcos@gmail.com
 */
public class PhoneNumber {
		
	private PhoneType phoneType;
	private String number;
	
	public final PhoneType getPhoneType() {
		return phoneType;
	}
	public final void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
	public final String getNumber() {
		return number;
	}
	public final void setNumber(String number) {
		this.number = number;
	}
	
}