/**
 * 
 */
package salomax.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import salomax.mock.json.bean.JsonResult;
import salomax.mock.json.gson.GsonMock;
import salomax.mock.json.jackson.JacksonMock;
import salomax.mock.protoc.mock.ProtocBufferMock;

/**
 * @author salomao.marcos@gmail.com
 */
public class JsonMockCompare {
	
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = 
			Logger.getLogger(JsonMockCompare.class.getName());
	
	public static void main(String[] args) {
		
		List<Mock> jsonMocks = new ArrayList<Mock>();
		
		jsonMocks.add(new JacksonMock());
		jsonMocks.add(new GsonMock());
		jsonMocks.add(new ProtocBufferMock());
		
		for (Mock jsonMock : jsonMocks) {

			LOGGER.log(Level.INFO, "Mock {0}", jsonMock.getName());
			
			JsonResult jsonResult = jsonMock.mock();

			LOGGER.log(Level.INFO, "Objects instantiation time {0}", jsonResult.getObjectInstatiationTime());
			LOGGER.log(Level.INFO, "Serialization time {0}", jsonResult.getSerializationTime());
			LOGGER.log(Level.INFO, "Deserialization time {0}", jsonResult.getDeserializationTime());
			LOGGER.log(Level.INFO, "Output {0}", jsonResult.getOutput());
			
		}
		
		
	}
	
}
