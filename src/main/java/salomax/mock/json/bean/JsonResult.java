/**
 * 
 */
package salomax.mock.json.bean;


/**
 * @author salomao.marcos@gmail.com
 *
 */
public class JsonResult {
	
	private Long objectInstatiationTime;
	private Long serializationTime;
	private Long deserializationTime;
	private String output;
	
	public Long getObjectInstatiationTime() {
		return objectInstatiationTime;
	}
	public void setObjectInstatiationTime(Long objectInstatiationTime) {
		this.objectInstatiationTime = objectInstatiationTime;
	}
	public Long getSerializationTime() {
		return serializationTime;
	}
	public void setSerializationTime(Long serializationTime) {
		this.serializationTime = serializationTime;
	}
	public Long getDeserializationTime() {
		return deserializationTime;
	}
	public void setDeserializationTime(Long deserializationTime) {
		this.deserializationTime = deserializationTime;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}	
	
}
