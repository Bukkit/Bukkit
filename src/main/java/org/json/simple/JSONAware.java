package org.json.simple;

/**
 * Beans that support customized output of JSON text shall implement this interface.  
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
public interface JSONAware {
	/**
	 * @return JSON text
	 */
	String toJSONString();
}
