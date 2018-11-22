package com.seqato.framework.vo;

import java.util.HashMap;

public class ReturnMapVO  extends BaseReturnVO{

	private HashMap<String, BaseReturnVO> data;

	public ReturnMapVO(){		
	}
	public HashMap<String, BaseReturnVO> getData() {
		return data;
	}

	public void setData(HashMap<String, BaseReturnVO> data) {
		this.data = data;
	}
	
	public ReturnMapVO(HashMap<String, BaseReturnVO> voObject, int moduleId,String moduleName,int messageId, String message,String voName){
		super( moduleId, moduleName,messageId, message,voName);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObject);
		}
	}
	
	public ReturnMapVO(HashMap<String, BaseReturnVO> voObject, int moduleId,String moduleName,int messageId, String message){
		super( moduleId, moduleName,messageId, message);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObject);
		}
	}
	public ReturnMapVO(HashMap<String, BaseReturnVO> voObject, int moduleId,int messageId, String message){
		super( moduleId, messageId, message);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObject);
		}
	} 
	
	public ReturnMapVO( int moduleId,int messageId, String message){
		super( moduleId, messageId, message);
		this.setData(null);
	}
	
	public ReturnMapVO(int messageId, String message){
		super( messageId, message);
		this.setData(null);
	}
	
	public ReturnMapVO(int messageId){
		super( messageId,null);
		this.setData(null);
	}

}
