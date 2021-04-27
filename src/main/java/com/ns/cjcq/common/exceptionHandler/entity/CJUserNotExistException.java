/**
 * 
 */
package com.ns.cjcq.common.exceptionHandler.entity;

/**
 * @author zhailiang
 *
 */
public class CJUserNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112780192479692859L;
	
	private String id;
	
	public CJUserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
