/**
 * 
 */
package salomax.mock.json.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salomao.marcos@gmail.com
 */
public class AddressBook {
	
	private List<Person> persons = new ArrayList<Person>();

	public final List<Person> getPersons() {
		return persons;
	}

	public final void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
