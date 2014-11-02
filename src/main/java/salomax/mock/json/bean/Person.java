/**
 * 
 */
package salomax.mock.json.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salomao.marcos@gmail.com
 */
public class Person {

	private String name;
	private Integer id;
	private String email;
	private List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PhoneNumber> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}
	
}
