package com.zhidian.exception;

public class FileExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileExistException(){}
	
	public FileExistException(String message){
		super(message);
	}
	
}
