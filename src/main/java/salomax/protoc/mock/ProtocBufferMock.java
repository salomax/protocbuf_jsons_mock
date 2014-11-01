package salomax.protoc.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import salomax.protoc.mock.AddressBookProtos.AddressBook;
import salomax.protoc.mock.AddressBookProtos.Person;

/**
 * Classe mock demonstrando a serialização 
 * e deserialização utilizando protocbuffer.<br/>
 * <br/>
 * Baseado no tutorial disponível em 
 * {@link https://developers.google.com/protocol-buffers/docs/javatutorial?hl=pt}.<br/>
 * 
 * @author salomao.marcos@gmail.com
 */
public class ProtocBufferMock {
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
			Logger.getLogger(ProtocBufferMock.class.getName());
	
	/**
	 * Método inicial.
	 * 
	 * @param args Argumentos de entrada.
	 */
	public static void main(String[] args) {
		
		LOGGER.info("Iniciando mock");
		
		File file = null;
		OutputStream outputStream = null;
				
		try {
			
			/*
			 * Criando arquivo temporário no qual
			 * será utilizado para o teste de serialização.
			 */
			file = File.createTempFile("addressbook_", ".protoc");
			outputStream = new FileOutputStream(file);
			
			LOGGER.log(Level.INFO, "Criado arquivo temporario {0}", 
					file.getCanonicalFile());
			
			/*
			 *  Criando os objetos já preenchidos com os dados.
			 */
			long timeBefore = System.currentTimeMillis();

			AddressBook addressBook = buildAddressBook();
			
			LOGGER.log(Level.INFO, "Objects instantiation time {0}", 
					(System.currentTimeMillis() - timeBefore));

			/*
			 * Serializando objeto.
			 */
			timeBefore = System.currentTimeMillis();
			
			addressBook.writeTo(outputStream);
			
			LOGGER.log(Level.INFO, "Serialization time {0}", 
					(System.currentTimeMillis() - timeBefore));
			
			/*
			 * Deserializando objeto.
			 */
			timeBefore = System.currentTimeMillis();
			
			addressBook = AddressBook.parseFrom(new FileInputStream(file));
			
			LOGGER.log(Level.INFO, "Deserialization time {0}", 
					(System.currentTimeMillis() - timeBefore));
			
			LOGGER.log(Level.INFO, "\n{0}", addressBook);
			
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
			if (outputStream != null) {
				// file.deleteOnExit();
			}
			
		}
		
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
	
}