package salomax.mock.json;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import salomax.mock.Mock;
import salomax.mock.json.bean.AddressBook;
import salomax.mock.json.bean.JsonResult;
import salomax.mock.json.bean.Person;
import salomax.mock.json.bean.PhoneNumber;
import salomax.mock.json.bean.PhoneType;

/**
 * @author salomao.marcos@gmail.com
 */
public abstract class AbstractJsonMock implements Mock {
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
			Logger.getLogger(AbstractJsonMock.class.getName());
	
	public abstract AddressBook deserialize(
			FileReader fileReader, Class<AddressBook> clazz) throws Exception;

	public abstract void serialize(
			AddressBook addressBook, Writer writer) throws Exception;
	
	public JsonResult mock() {
		
		File file = null;
		Writer writer = null;
		
		JsonResult jsonResult = new JsonResult();
		
		try {
			
			/*
			 *  Criando os objetos já preenchidos com os dados.
			 */
			long timeBefore = System.currentTimeMillis();
			
			AddressBook addressBook = newAddressBook();
			
			jsonResult.setObjectInstatiationTime(
					System.currentTimeMillis() - timeBefore);
			
			file = File.createTempFile("addressbook_", ".json");
			writer = new FileWriter(file);
			
			/*
			 * Serializando objeto.
			 */
			timeBefore = System.currentTimeMillis();
			
			serialize(addressBook, writer);
			
			try {

				writer.flush();
				
			} catch(IOException e) {
				LOGGER.log(Level.FINE, e.getMessage());
			}
			
			jsonResult.setSerializationTime(
					System.currentTimeMillis() - timeBefore);
			
			/*
			 * Deserializando objeto.
			 */
			timeBefore = System.currentTimeMillis();
			
			addressBook = deserialize(new FileReader(file), AddressBook.class);
			
			jsonResult.setDeserializationTime(
					System.currentTimeMillis() - timeBefore);
			
			writer = new StringWriter();
			
			serialize(addressBook, writer);
			
			jsonResult.setOutput(writer.toString());
			
		} catch (Exception e) {
			
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			
		} finally {
			
			// Fechar stream
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
				}
			}

			// remover arquivo temporario
			if (file != null) {
				file.deleteOnExit();
			}
			
		}
		
		return jsonResult;
		
	}

	private AddressBook newAddressBook() {
		AddressBook addressBook = new AddressBook();
		
		Person person = new Person();
		
		person.setId(1);
		person.setName("Salomax");
		person.setEmail("salomao.marcos@gmail.com");
		
		PhoneNumber phoneNumber = new PhoneNumber();
		
		phoneNumber.setNumber("+99 99 99999999");
		phoneNumber.setPhoneType(PhoneType.MOBILE);
		
		person.getPhones().add(phoneNumber);
		
		addressBook.getPersons().add(person);
		return addressBook;
	}

}
