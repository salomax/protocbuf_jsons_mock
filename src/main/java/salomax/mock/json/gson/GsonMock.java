package salomax.mock.json.gson;

import java.io.FileReader;
import java.io.Writer;

import salomax.mock.json.AbstractJsonMock;
import salomax.mock.json.bean.AddressBook;

import com.google.gson.Gson;

/**
 * @author salomao.marcos@gmail.com
 */
public class GsonMock extends AbstractJsonMock {
	
	private Gson gson = new Gson();
	
	@Override
	public AddressBook deserialize(FileReader fileReader,
			Class<AddressBook> clazz) {
		return gson.fromJson(fileReader, clazz);
	}

	@Override
	public void serialize(AddressBook addressBook, Writer writer) {
		gson.toJson(addressBook, writer);		
	}

	public String getName() {
		return "com.google.gson.Gson";
	}

}
