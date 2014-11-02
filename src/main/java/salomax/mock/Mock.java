/**
 * 
 */
package salomax.mock;

import salomax.mock.json.bean.JsonResult;

/**
 * @author salomao.marcos@gmail.com
 */
public interface Mock {
	
	/**
	 * @return Nome do mock.
	 */
	public String getName();
	
	/**
	 * Método contendo o código de teste/validação/simulação.
	 */
	public JsonResult mock();

}
