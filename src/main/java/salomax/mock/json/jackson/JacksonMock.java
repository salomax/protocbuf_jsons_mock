package salomax.mock.json.jackson;

import java.io.FileReader;
import java.io.Writer;

import org.codehaus.jackson.map.ObjectMapper;

import salomax.mock.json.AbstractJsonMock;
import salomax.mock.json.bean.AddressBook;

/**
 * @author salomao.marcos@gmail.com
 */
public class JacksonMock extends AbstractJsonMock {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public AddressBook deserialize(FileReader fileReader,
			Class<AddressBook> clazz) throws Exception {
		return mapper.readValue(fileReader, clazz);
	}

	@Override
	public void serialize(AddressBook addressBook, Writer writer) throws Exception {
		mapper.writeValue(writer, addressBook);		
	}

	public String getName() {
		return "org.codehaus.jackson";
	}

}
