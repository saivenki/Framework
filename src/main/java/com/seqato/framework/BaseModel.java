package com.seqato.framework;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.seqato.framework.vo.BaseVO;

@MappedSuperclass
public  class BaseModel implements Serializable{
  
	
	@CreatedDate
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
	private Timestamp createdDate;
    
    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Timestamp lastModifiedDate;
    	
    
	//private Long activeIndicator;

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdTimeStamp) {
		this.createdDate = createdTimeStamp;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/*public Long getActiveIndicator() {
		return activeIndicator;
	}

	public void setActiveIndicator(Long activeIndicator) {
		this.activeIndicator = activeIndicator;
	}
*/
	
	// Override this method in derived class to copy List Objects from Model to VO
	public void copyListObjects(BaseVO basevo){
		
	}
	
	// Copy the list of items in the basevoList to modelList using ModelMapper
	protected void copyList(List<? extends BaseVO> basevoList, Class modelClass){
		if (basevoList != null){
			ModelMapper modelMapper = new ModelMapper();
			
			Iterator iterator = basevoList.iterator();
			
			// Iterate through the list of VO objects and map to the Model object
			while (iterator.hasNext()){
				BaseModel model = (BaseModel)modelMapper.map(iterator.next(),modelClass);
				assignToModelList(model);
			}
		}
		return ;
	}
	
	protected void assignToModelList(BaseModel model){
		
	}
	
	/*
	//convert entity to VO
	//by setting common fields in both VO and entity
	public BaseVO convertEntityToVO(BaseModel baseModel){
		System.out.println("BaseModel.convertEntityToVO() - Not expected to run this code");
		return null;
	}
	
	//convert list of entity to List of VO
	//by setting common fields in both VO and entity
	public List<? extends BaseVO> convertEntityToVO(List <? extends BaseModel> baseModel){
		return null;
	}
	
	//convert VO to Entity
	//by setting common fields in both VO and entity
	public BaseModel convertVOToEntity(BaseVO baseVO){
		System.out.println("basevo = basemodel");
		return null;		
	}
	
	//convert list of entity to List of VO
	// by setting common fields in both VO and entity
	public List<? extends BaseModel> convertVOToEntity(List<? extends BaseVO> baseVO) {
		return null;
	}*/
}
