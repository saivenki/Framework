package com.seqato.framework;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.seqato.framework.BaseModel;

@SuppressWarnings("hiding")
public interface BaseDao<BaseModel,X> extends PagingAndSortingRepository<BaseModel, Long >{
}
