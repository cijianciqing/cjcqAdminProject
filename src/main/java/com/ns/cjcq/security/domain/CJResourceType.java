/**
 * 
 */
package com.ns.cjcq.security.domain;

/**
 * @author zhailiang
 *
 */
public enum CJResourceType {
	DIR("directory"),
	
	MENU("menu"),
	
	BUTTON("button");

	private  String message;

	//enum对象构造器
	CJResourceType(String message){
		this.message = message;
	}

	public String getMessage(){
		return  this.message;
	}

	public static CJResourceType valueOfCode(String message) {
		for (CJResourceType status : CJResourceType.values()) {
			if (status.getMessage().equals(message)) {
				return status;
			}
		}
		throw new IllegalArgumentException(
				"CJResourceType status cannot be resolved for code: " + message);
	}


	@Override
	public String toString() {
		return message;
	}
}
