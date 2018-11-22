package com.seqato.framework.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seqato.framework.utility.MessageCodes;

public abstract class BaseReturnVO {
	private int moduleId ;
	private String moduleName;
	private int messageId;
	private String message;
	private String voName;
	
	
	/**
	 * Constructor
	 */
	public BaseReturnVO(){
		
	} 

	BaseReturnVO(int moduleId,String moduleName, int messageId,String message,String voName){
		this.voName = voName;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.messageId = messageId;
		this.message = message;
	}
	
	BaseReturnVO(int moduleId,String moduleName, int messageId,String message){
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.messageId = messageId;
		this.message = message;
	}
	/**
	 * Constructor
	 * @param moduleId
	 * @param messageId
	 * @param message
	 */
	BaseReturnVO(int moduleId,int messageId,String message){
		this.moduleId = moduleId;
		this.messageId = messageId;
		this.message = message;
	}
	
	/**
	 * Constructor
	 * @param messageId
	 * @param message
	 */
	BaseReturnVO(int messageId,String message){
		this.messageId = messageId;
		this.message = message;
	}
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getVoName() {
		return voName;
	}
	public void setVoName(String voName) {
		this.voName = voName;
	}

	/**
	 * Check if MessageId has any Error Codes
	 * @return true in case messageId has any of the error codes
	 */
	protected boolean checkForError(){
		boolean retVal = false;
		if (messageId == MessageCodes.BAD_REQUEST||
				messageId == MessageCodes.INTERNAL_SERVER_ERROR ||
				messageId == MessageCodes.METHOD_NOT_SUPPORTED ||
				messageId == MessageCodes.NO_RECORDS_FOUND ||
				messageId == MessageCodes.UNAUTHORIZED_ACCESS) {
			retVal = true;
		}
		return retVal;
	}
	
	@Override
	public String toString(){
		ObjectMapper objMap = new ObjectMapper();
		String returnVal = null;
		try {
			returnVal = objMap.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return returnVal;
		
	}

}
