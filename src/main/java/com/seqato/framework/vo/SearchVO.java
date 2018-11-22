package com.seqato.framework.vo;

import java.util.Map;

import com.seqato.framework.utility.VONames;

public class SearchVO extends BaseVO{

	private Map<String, String> searchCriteria;
	private String functionHandler;
	private Integer pageSize; 
	private Integer pageNumber;
	private String[] sortBy ;
	private String sortOrder;
	private String[] requiredFields;
	
	public SearchVO(){
		voName=VONames.SEARCH_VO;
	}
	
	public Map<String, String> getSearchCriteria() {
		return searchCriteria;
	}
	public void setSearchCriteria(Map<String, String> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String[] getSortBy() {
		return sortBy;
	}
	public void setSortBy(String[] sortBy) {
		this.sortBy = sortBy;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getFunctionHandler() {
		return functionHandler;
	}

	public void setFunctionHandler(String functionHandler) {
		this.functionHandler = functionHandler;
	}

	public String[] getRequiredFields() {
		return requiredFields;
	}

	public void setRequiredFields(String[] requiredFields) {
		this.requiredFields = requiredFields;
	}
	
}
