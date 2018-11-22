package com.seqato.framework.vo;

public class ReturnListVO extends BaseReturnVO{
	
	private int totalNoOfPages;
	private int totalNoOfRecords;
	private int pageSize;
	private int currentPageNumber;
	private BaseVO[] data;

	public ReturnListVO(){	
		
	}
	
	public BaseVO[] getData() {
		return data;
	}

	public void setData(BaseVO[] data) {
		this.data = data;
	}
	
	public int getTotalNoOfPages() {
		return totalNoOfPages;
	}

	public void setTotalNoOfPages(int totalNoOfPages) {
		this.totalNoOfPages = totalNoOfPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setTotalNoOfPages();
	}

	public int getTotalNoOfRecords() {
		return totalNoOfRecords;
	}

	public void setTotalNoOfRecords(int totalNoOfRecords) {
		this.totalNoOfRecords = totalNoOfRecords;
		setTotalNoOfPages();
	}
	
	public void setTotalNoOfPages() {
		if (pageSize != 0 && totalNoOfRecords != 0) {
			// GET total no. of pages
			double totalNoOfPages = ((double)totalNoOfRecords) / pageSize;
			totalNoOfPages = Math.ceil(totalNoOfPages);
			setTotalNoOfPages((int) totalNoOfPages);
		}
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}
	
	

	public ReturnListVO(BaseVO[] voObjects, int moduleId,String moduleName,int messageId, String message,String voName){
		super( moduleId,moduleName, messageId,message,voName);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObjects);
		} 
	}
	public ReturnListVO(BaseVO[] voObjects, int moduleId,String moduleName,int messageId, String message){
		super( moduleId,moduleName, messageId,message);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObjects);
		} 
	}
	
	public ReturnListVO(BaseVO[] voObjects, int moduleId,int messageId, String message){
		super( moduleId, messageId, message);
		if (checkForError() == true){
			this.setData(null);
		} else {
			this.setData(voObjects);
		} 
	}
	
	public ReturnListVO( int moduleId,int messageId, String message){
		super( moduleId, messageId, message);
		this.setData(null);
	}

	public ReturnListVO(int messageId, String message){
		super( messageId, message);
		this.setData(null);
	}
	
	public ReturnListVO(int messageId){
		super( messageId, null);
		this.setData(null);
	}
	
	public void setPageNumbers(SearchVO searchVO, int totalNoOfRecords) {
		Integer pageSize = searchVO.getPageSize();
		Integer currentPageNumber = searchVO.getPageNumber();
		
		if (pageSize != null && currentPageNumber != null) {
			setTotalNoOfRecords(totalNoOfRecords);
			setPageSize(pageSize);
			setCurrentPageNumber(currentPageNumber);
		}
	}
}
