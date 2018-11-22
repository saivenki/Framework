package com.seqato.framework.vo;

public class ReturnSingleVO  extends BaseReturnVO{
	private BaseVO data;

	public BaseVO getData() {
		return data;
	}
	public void setData(BaseVO data) {
		this.data = data;
	}
	
	public ReturnSingleVO(){
		
	}
	
	public ReturnSingleVO(BaseVO voObject, int moduleId,String moduleName,int messageId, String message,String voName){
		super( moduleId, moduleName,messageId, message,voName);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObject);
		}
	}
	
	public ReturnSingleVO(BaseVO voObject, int moduleId,String moduleName,int messageId, String message){
		super( moduleId, moduleName,messageId, message);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObject);
		}
	}
	public ReturnSingleVO(BaseVO voObject, int moduleId,int messageId, String message){
		super( moduleId, messageId, message);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObject);
		}
	} 
	
	public ReturnSingleVO( int moduleId,int messageId, String message){
		super( moduleId, messageId, message);
		this.setData(null);
	}
	
	public ReturnSingleVO(int messageId, String message){
		super( messageId, message);
		this.setData(null);
	}
	
	public ReturnSingleVO(int messageId){
		super( messageId,null);
		this.setData(null);
	}
}
