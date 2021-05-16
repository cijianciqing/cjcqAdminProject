/**
 * 
 */
package com.ns.cjcq.security.domain;

/**
 * @author zhailiang
 *
 */
public enum CJUrlType {
	GET("get"),

	POST("post"),

	PUT("put"),

	DELETE("delete");

	private  String message;

	//enum对象构造器
	CJUrlType(String message){
		this.message = message;
	}

	public String getMessage(){
		return  this.message;
	}

	public static CJUrlType valueOfCode(String message) {
		for (CJUrlType status : CJUrlType.values()) {
			if (status.getMessage().equals(message)) {
				return status;
			}
		}
		throw new IllegalArgumentException(
				"CJUrlType status cannot be resolved for code: " + message);
	}


	@Override
	public String toString() {
		return message;
	}
}
