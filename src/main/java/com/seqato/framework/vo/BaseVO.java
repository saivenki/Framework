package com.seqato.framework.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.seqato.framework.utility.JsonTimestampSerializer; 

public class BaseVO {
	
	@JsonIgnore
	private int moduleId;
	
	@JsonIgnore
	private String moduleName;
	
	@JsonIgnore
	private int messageId;
	
	@JsonIgnore
	private String message;
	/** Name of the VO **/
	protected String voName;
	
	protected Long activeIndicator; 
	
	protected Long createdBy;
	@JsonSerialize(using=JsonTimestampSerializer.class)
	protected Timestamp createdTimeStamp;
	@JsonSerialize(using=JsonTimestampSerializer.class)
	protected Timestamp updatedTimeStamp;
	
	public void setVoName(){
		
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
	public Long getActiveIndicator() {
		return activeIndicator;
	}
	public void setActiveIndicator(Long activeIndicator) {
		this.activeIndicator = activeIndicator;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getCreatedTimeStamp() {
		return createdTimeStamp;
	}
	public void setCreatedTimeStamp(Timestamp createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}
	public Timestamp getUpdatedTimeStamp() {
		return updatedTimeStamp;
	}
	public void setUpdatedTimeStamp(Timestamp updatedTimeStamp) {
		this.updatedTimeStamp = updatedTimeStamp;
	}


	/**
	 * Create ReturnTypesVO from the VO Object
	 * @return
	 */
	public ReturnSingleVO constructReturnVO(){
		ReturnSingleVO returnTypesVO = new ReturnSingleVO(this,moduleId,moduleName,messageId,message,voName);
		return returnTypesVO;
	}
	
	/**
	 * Create ReturnTypesArrVO from the array of VO Object
	 * @return
	 */
	public ReturnListVO constructReturnArrVO(BaseVO[] baseVOArr){
		ReturnListVO returnTypesVO = new ReturnListVO(baseVOArr,moduleId,moduleName,messageId,message,voName);
		return returnTypesVO;
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
