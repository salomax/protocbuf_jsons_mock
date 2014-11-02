package salomax.mock.protoc.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import salomax.mock.Mock;
import salomax.mock.json.bean.JsonResult;
import salomax.mock.protoc.mock.AddressBookProtos.AddressBook;
import salomax.mock.protoc.mock.AddressBookProtos.Person;

/**
 * Classe mock demonstrando a serialização 
 * e deserialização utilizando protocbuffer.<br/>
 * <br/>
 * Baseado no tutorial disponível em 
 * {@link https://developers.google.com/protocol-buffers/docs/javatutorial?hl=pt}.<br/>
 * 
 * @author salomao.marcos@gmail.com
 */
public class ProtocBufferMock implements Mock {
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
			Logger.getLogger(ProtocBufferMock.class.getName());
	
	/**
	 * Método contendo o código de teste/validação/simulação.
	 */
	public JsonResult mock() {
		
		LOGGER.log(Level.INFO, "Mock ProtocolBuffer");
		
		File file = null;
		OutputStream outputStream = null;
		
		JsonResult jsonResult = new JsonResult();
				
		try {
			
			/*
			 * Criando arquivo temporário no qual
			 * será utilizado para o teste de serialização.
			 */
			file = File.createTempFile("addressbook_", ".protoc");
			outputStream = new FileOutputStream(file);
			
			/*
			 *  Criando os objetos já preenchidos com os dados.
			 */
			long timeBefore = System.currentTimeMillis();

			AddressBook addressBook = buildAddressBook();
			
			jsonResult.setObjectInstatiationTime(
					System.currentTimeMillis() - timeBefore);

			/*
			 * Serializando objeto.
			 */
			timeBefore = System.currentTimeMillis();
			
			addressBook.writeTo(outputStream);
			
			jsonResult.setSerializationTime(
					System.currentTimeMillis() - timeBefore);
			
			/*
			 * Deserializando objeto.
			 */
			timeBefore = System.currentTimeMillis();
			
			addressBook = AddressBook.parseFrom(new FileInputStream(file));
			
			jsonResult.setDeserializationTime(
					System.currentTimeMillis() - timeBefore);
			
			jsonResult.setOutput(addressBook.toString());
			
		} catch (FileNotFoundException e) {
			
			LOGGER.severe(e.getLocalizedMessage());
			
		} catch (IOException e) {
			
			LOGGER.log(Level.SEVERE, e.getLocalizedMessage(), e);
			
		} finally {

			// Fechar stream
			if (outputStream != null) {
				try {
					outputStream.close();
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

	/**
	 * Build o objeto AddressBook.
	 * 
	 * @return objeto AddressBook.
	 */
	private static AddressBook buildAddressBook() {
		
		AddressBook.Builder addressBookBuilder = AddressBook.newBuilder();
		
		Person.Builder person = Person.newBuilder();
		
		person.setId(1)
			   .setName("Salomax")
			   .setEmail("salomao.marcos@gmail.com");
		
		Person.PhoneNumber.Builder phoneNumber = 
				Person.PhoneNumber.newBuilder().setNumber(
						"+99 99 99999999");
		
		phoneNumber.setType(Person.PhoneType.MOBILE);
		
		person.addPhone(phoneNumber);

		addressBookBuilder.addPerson(person);
		
		return addressBookBuilder.build();
	}

	public String getName() {
		return "com.google.protobuf";
	}
	
}