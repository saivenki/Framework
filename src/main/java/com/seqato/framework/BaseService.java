package com.seqato.framework;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.seqato.framework.utility.MessageCodes;
import com.seqato.framework.vo.BaseReturnVO;
import com.seqato.framework.vo.BaseVO;
import com.seqato.framework.vo.ReturnListVO;
import com.seqato.framework.vo.ReturnMapVO;
import com.seqato.framework.vo.ReturnSingleVO;
import com.seqato.framework.vo.SearchVO;

public abstract class BaseService {

	protected static int MODULE_ID;
	protected static String MODULE_NAME;

	private void setBaseParams(BaseVO baseVO) {
		baseVO.setModuleId(MODULE_ID);
		baseVO.setModuleName(MODULE_NAME);
		baseVO.setVoName();
	}

	private void setBaseParams(BaseReturnVO baseReturnVO) {
		baseReturnVO.setModuleId(MODULE_ID);
		baseReturnVO.setModuleName(MODULE_NAME);
	}

	/**
	 * Call CrudDao function to fetch the record with value id from DB
	 * 
	 * @param crudDao
	 * @param id
	 * @param destination
	 * @return
	 */
	public BaseVO getByMasterTableId(Long masterTableId, Long childTableId, Class<? extends BaseVO> resultVOClass) {

		BaseVO baseVO = null;

		BaseModel baseModel = (BaseModel) this.getByMasterTableId(masterTableId, childTableId);
		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG);
			setChildListObjects(baseVO);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		baseVO = getObjectsWithIds(baseVO);

		return baseVO;
	}
	
	/**
	 * Override this method and do additional Get of objects from Type Details
	 * 
	 * @param baseVO
	 * @return
	 */
	protected BaseVO getObjectsWithIds(BaseVO baseVO){
		return baseVO;
	}
	
	// Overwite this function in the Service to set the Child list Objects
	protected void setChildListObjects(BaseVO baseVO){
		
	}

	protected BaseModel getByMasterTableId(Long masterTableId, Long childTableId) {
		return null;
	}

	public BaseVO get(Long[] ids, Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;
		BaseModel baseModel = (BaseModel) this.get(ids);
		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		baseVO = getObjectsWithIds(baseVO);
		return baseVO;
	}

	protected BaseModel get(Long[] ids) {
		return null;
	}

	/**
	 * Create
	 * 
	 * @param masterTableVO
	 * @param childTableVO
	 * @param destinationClass
	 * @return
	 */
	public BaseVO createByMasterTableId(Long masterTableId, BaseVO childTableVO, Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;

		// Convert the childTableVO to Entity
		BaseModel childModel = getEntityForInsert(childTableVO);

		BaseModel baseModel = (BaseModel) this.createByMasterTableId(masterTableId, childModel);
		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.POST_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected BaseModel createByMasterTableId(Long masterTableId, BaseModel childModel) {
		return null;
	}

	public BaseVO create(Long[] ids, BaseVO childTableVO, Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;

		// Convert the childTableVO to Entity
		BaseModel childModel = getEntity(childTableVO);
		Timestamp ts = getCurrentTimeStamp();
		childModel.setCreatedDate(ts);
		childModel.setLastModifiedDate(ts);
	//	childModel.setActiveIndicator(1L);
		BaseModel baseModel = (BaseModel) this.create(ids, childModel);
		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.POST_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected BaseModel create(Long[] ids, BaseModel childModel) {
		return null;
	}

	public Timestamp getCurrentTimeStamp() {
		Date date = new Date();
		return new Timestamp(date.getTime());
	}

	/**
	 * update
	 * 
	 * @param masterTableVO
	 * @param childTableVO
	 * @param destinationClass
	 * @return
	 */
	public BaseVO updateByMasterTableId(Long masterTableId, BaseVO childTableVO,
			Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;

		// Convert the childTableVO to Entity
		BaseModel childModel = getEntityForUpdate(childTableVO);
		
		BaseModel baseModel = this.updateByMasterTableId(masterTableId, childModel);

		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.UPDATE_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected BaseModel updateByMasterTableId(Long masterTableId, BaseModel childModel) {
		System.out.println("Not expected to run this code updateByMasterTableId() from BaseService"); 
		return null;
	}

	public BaseVO update(Long[] ids, BaseVO childTableVO,
			Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;

		// Convert the childTableVO to Entity
		BaseModel childModel = getEntity(childTableVO);
		Timestamp ts = getCurrentTimeStamp();
		childModel.setLastModifiedDate(ts);
	//	childModel.setActiveIndicator(1L);
		BaseModel baseModel = this.update(ids, childModel);
		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.UPDATE_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected BaseModel update(Long[] ids, BaseModel childModel) {
		return null;
	}

	/**
	 * update
	 * 
	 * @param masterTableVO
	 * @param childTableVO
	 * @param destinationClass
	 * @return
	 */
	public BaseVO patchByMasterTableId(Long masterTableId, BaseVO childTableVO,
			Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;

		// Convert the childTableVO to Entity
		BaseModel childModel = getEntityForUpdate(childTableVO);
		
		BaseModel baseModel = this.patchByMasterTableId(masterTableId, childModel);

		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.UPDATE_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected BaseModel patchByMasterTableId(Long masterTableId, BaseModel childModel) {
		System.out.println("Not expected to run this code updateByMasterTableId() from BaseService"); 
		return null;
	}

	public BaseVO patch(Long[] ids, BaseVO childTableVO,
			Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;

		// Convert the childTableVO to Entity
		BaseModel childModel = getEntity(childTableVO);
		Timestamp ts = getCurrentTimeStamp();
		childModel.setLastModifiedDate(ts);
	//	childModel.setActiveIndicator(1L);
		BaseModel baseModel = this.patch(ids, childModel);
		if (baseModel != null) {
			baseVO = (BaseVO) getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.UPDATE_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected BaseModel patch(Long[] ids, BaseModel childModel) {
		return null;
	}

	/**
	 * Delete
	 * 
	 * @param masterTableId
	 * @param childTableId
	 * @param modelClass
	 * @return
	 */
	public BaseVO deleteByMasterTable(Long masterTableId, Long childTableId) {
		BaseVO baseVO = null;
		int retValue = deleteByMasterTableId(masterTableId, childTableId);
		if (retValue > 0) {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.SUCCESS);
			baseVO.setMessage(MessageCodes.DELETE_SUCCESS_MSG);
			setBaseParams(baseVO);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected int deleteByMasterTableId(Long masterTableId, Long childTableId) {
		return 0;
	}

	public BaseVO deleteByIds(Long[] ids) {
		BaseVO baseVO = null;
		int retValue = delete(ids);
		if (retValue > 0) {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.SUCCESS);
			baseVO.setMessage(MessageCodes.DELETE_SUCCESS_MSG);
			setBaseParams(baseVO);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return baseVO;
	}

	protected int delete(Long[] ids) {
		return 0;
	}
	
	/**
	 * Override this method and do additional Get of objects from Type Details
	 * 
	 * @param baseVO
	 * @return
	 */
	protected BaseVO[] getObjectsWithIds(BaseVO[] baseVO){
		return baseVO;
	}

	/**
	 * Get All
	 * 
	 * @param masterTableId
	 * @param resultVOClass
	 * @return
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public BaseVO[] getAllByMasterTableId(Long masterTableId, Class<? extends BaseVO> resultVOClass)
			throws JsonProcessingException, IOException {
		BaseVO[] baseVOArr = null;
		// Convert the childTableVO to Entity
		// BaseModel childModel = getEntity(childTableVO,modelClass);
		List list = (List) this.getAllByMasterTableId(masterTableId);
		int len = list.size();
		if (len > 0) {
			Iterator it = list.iterator();
			BaseModel baseModel;
			baseVOArr = new BaseVO[len];
			int i = 0;
			while (it.hasNext()) {
				baseModel = (BaseModel) it.next();
				baseVOArr[i] = getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG);
				i++;
			}
		}
		baseVOArr = getObjectsWithIds(baseVOArr);
		return baseVOArr;
	}
	
	// Convert Entity List to VO List
	protected List getVOList(List<? extends BaseModel> listOfModelObjs,Class<? extends BaseVO> resultVOClass){
		
		System.out.println("1 Inside getVOList");

		List<BaseVO> baseVOList = null;
	
		int len = listOfModelObjs.size();
		if (len > 0) {
			Iterator it = listOfModelObjs.iterator();
			BaseModel baseModel;
			baseVOList = new ArrayList<BaseVO>();
			while (it.hasNext()) {
				baseModel = (BaseModel) it.next();
				baseVOList.add(getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG));
			}
		}
		System.out.println("2 Inside getVOList");

		return baseVOList;
	}

	protected List getAllByMasterTableId(Long ids) throws JsonProcessingException, IOException {
		return null;
	}

	public BaseVO[] getAll(Long[] ids, Class<? extends BaseVO> resultVOClass) {
		BaseVO[] baseVOArr = null;
		// Convert the childTableVO to Entity
		// BaseModel childModel = getEntity(childTableVO,modelClass);
		List list = (List) this.getAll(ids);
		int len = list.size();
		if (len > 0) {
			Iterator it = list.iterator();
			BaseModel baseModel;
			baseVOArr = new BaseVO[len];
			int i = 0;
			while (it.hasNext()) {
				baseModel = (BaseModel) it.next();
				baseVOArr[i] = getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG);
				i++;
			}
		}
		baseVOArr = getObjectsWithIds(baseVOArr);
		return baseVOArr;
	}

	protected List getAll(Long[] ids) {
		return null;
	}
	
	// Override this method in all the Services 
	protected BaseVO convertEntityToVO(BaseModel baseModel) {
		System.out.println("Not expected to run this code. All Service classes need to implement this method");
		return null;
	}

	// Convert the Entity(Model) Object to VO Object
	protected BaseVO getVO(BaseModel baseModel, Class<? extends BaseVO> destination) {
		
		BaseVO baseVO = null;
		if (baseModel != null) {
			Date dt = new Date();
			long startTime = dt.getTime();
			//System.out.println("Details of Base Model == "+baseModel.toString());
			baseVO = convertEntityToVO(baseModel);
			System.out.println("baseVO "+baseVO);
			//baseVO = (BaseVO) modelMapper.map(baseModel, destination);
			dt = new Date();
			long endTime = dt.getTime();
			System.out.println("Time for Converting to "+destination.getName() +" VO == "+(endTime - startTime));

		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
		}
		setBaseParams(baseVO);
		return baseVO;
	}

	// Convert the Entity(Model) Object to VO Object
	protected BaseVO getVO(BaseModel baseModel, Class<? extends BaseVO> destination, int messageId) {
		BaseVO baseVO = getVO(baseModel, destination);
		baseVO.setMessageId(messageId);

		return baseVO;
	}

	// Convert the Entity(Model) Object to VO Object
	protected BaseVO getVO(BaseModel baseModel, Class<? extends BaseVO> destination, int messageId, String message) {
		BaseVO baseVO = null;
		if (baseModel != null) {
			baseVO = getVO(baseModel, destination, messageId);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		baseVO.setMessage(message);

		return baseVO;
	}

	public BaseReturnVO getBaseVO(BaseModel baseModel, Class<? extends BaseVO> resultVOClass) {
		BaseVO baseVO = null;
		if (baseModel != null) {
			baseVO = getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG);
		} else {
			baseVO = new BaseVO();
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(baseVO);
		}
		return handleAfterServiceCall(baseVO);
	}
	
	// Override this method in all the Services 
	protected BaseModel convertVOToEntity(BaseVO baseVO) {
		System.out.println("Not expected to run this code. All Service classes need to implement convertVOToEntity() method");
		return null;
	}
	
	protected BaseModel getEntityInstance() {
		System.out.println("Not expected to run this code. All Service classes need to implement getEntityInstance() method");
		return null;
	}

	// Convert VO Object to Entity(Model) Object
	public BaseModel getEntity(BaseVO baseVO) {
		
		BaseModel model = getEntityInstance();
	
		Date dt = new Date();
		long startTime = dt.getTime();
		model = convertVOToEntity(baseVO);
		System.out.println("model conversion "+model);
		dt = new Date();
		long endTime = dt.getTime();
		System.out.println("Time for Converting "+model.getClass().getName() + " VO to Entity  == "+(endTime - startTime));

		return model;
	}
	
	// Convert VO Object to Entity(Model) Object for Insert Purpose
	public BaseModel getEntityForInsert(BaseVO baseVO) {
		BaseModel model = getEntity(baseVO);
		setCommonFieldsForInsert(model);
		return model;
	}
	
	// Convert VO Object to Entity(Model) Object for Update Purpose
	public BaseModel getEntityForUpdate(BaseVO baseVO) {
		BaseModel model = getEntity(baseVO);
		setCommonFieldsForUpdate(model);
		return model;
	}

	// Convert Array of VO Objects to Entity(Model) Object 
	public ArrayList getEntity(BaseVO[] baseVO, Class<? extends BaseModel> destination) {
		
		ArrayList baseModelList = new ArrayList();

		int index = 0;
		int len = baseVO.length;
		while (index < len) {
			BaseModel model = getEntity(baseVO[index]);
			baseModelList.add(model);
			index++;
		}
		return baseModelList;
	}
	
	public ArrayList getEntity(List<? extends BaseVO> baseVOList, Class<? extends BaseModel> destination) {
		
		ArrayList baseModelList = new ArrayList();

		Iterator<? extends BaseVO> it = baseVOList.iterator();
		while (it.hasNext()) {
			BaseModel model = getEntity(it.next());
			
			baseModelList.add(model);
		}
		return baseModelList;
	}

	// Override this method in derived class to copy List Objects
	protected void copyListObjects(BaseVO basevo, BaseModel model) {

	}

	/*
	 * // Copy the list of items in the basevoList to modelList using
	 * ModelMapper protected List copyList(List<? extends BaseVO> basevoList,
	 * Class modelClass){ ModelMapper modelMapper = new ModelMapper(); Iterator
	 * iterator = basevoList.iterator(); List modelList = new ArrayList();
	 * 
	 * // Iterate through the list of VO objects and map to the Model object
	 * while (iterator.hasNext()){ BaseModel model =
	 * (BaseModel)modelMapper.map(iterator.next(),modelClass);
	 * modelList.add(model); } return modelList; }
	 */

	public BaseReturnVO callInternalServiceHandler(BaseVO voData, Object... args) throws JsonProcessingException, IOException {
		BaseReturnVO returnVO = null;
		BaseModel baseModel = null;
		if (voData != null) {
			baseModel = (BaseModel) this.getEntity(voData);
		}
		if (args != null && args.length > 0) {
			returnVO = this.internalServiceHandler(baseModel, args);
		} else {
			returnVO = this.internalServiceHandler(baseModel);
		}
		return returnVO;
	}

	public ReturnMapVO callExternalServiceHandler(BaseVO voData, Object... args) {
		ReturnMapVO returnMap = null;
		if (args != null && args.length > 0) {
			returnMap = this.externalServiceHandler(voData, args);
		} else {
			returnMap = this.externalServiceHandler(voData);
		}
		return returnMap;
	}

	public ReturnMapVO callExternalServiceHandler(BaseVO[] voData, Object... args)
			throws JsonProcessingException, IOException {
		ReturnMapVO returnMap = null;
		if (args != null && args.length > 0) {
			returnMap = this.externalServiceHandler(voData, args);
		} else {
			returnMap = this.externalServiceHandler(voData);
		}
		return returnMap;
	}

	public BaseReturnVO callServiceHandlerWithReturnVO(BaseVO voData, Object... args) throws JsonProcessingException, IOException {
		BaseReturnVO returnVO = null;
		BaseModel baseModel = null;
		if (voData != null) {
			baseModel = (BaseModel) this.getEntity(voData);
		}
		if (args != null && args.length > 0) {
			returnVO = this.internalServiceHandler(baseModel, args);
		} else {
			returnVO = this.internalServiceHandler(baseModel);
		}
		return returnVO;
	}

	protected BaseReturnVO internalServiceHandler(BaseModel baseModel, Object... args) throws JsonProcessingException, IOException {
		System.out.println("Not Expected to run");
		return null;
	}

	protected ReturnMapVO externalServiceHandler(BaseVO voData, Object... args) {
		System.out.println("Not Expected to run");
		return null;
	}

	protected ReturnMapVO externalServiceHandler(BaseVO[] voData, Object... args)
			throws JsonProcessingException, IOException {
		System.out.println("Not Expected to run");
		return null;
	}

	/**
	 * 
	 * @param entityList
	 * @param resultVOClass
	 * @return
	 */
	public BaseReturnVO getBaseVOArray(List<? extends BaseModel> entityList, Class<? extends BaseVO> resultVOClass) {
		BaseVO[] baseVOArr = null;
		if (entityList != null) {
			int len = entityList.size();
			if (len > 0) {
				Iterator it = entityList.iterator();
				BaseModel baseModel;
				baseVOArr = new BaseVO[len];
				int i = 0;
				while (it.hasNext()) {
					baseModel = (BaseModel) it.next();
					baseVOArr[i] = getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG);
					i++;
				}
			}
		}

		return handleAfterServiceCall(baseVOArr);
	}
	
	protected List<? extends BaseVO> getBaseVOList(List<? extends BaseModel> entityList, Class<? extends BaseVO> resultVOClass){
		List<BaseVO> baseVoList = new ArrayList<BaseVO>();
		if (entityList != null) {
			int len = entityList.size();
			if (len > 0) {
				Iterator it = entityList.iterator();
				BaseModel baseModel;
				while (it.hasNext()) {
					baseModel = (BaseModel) it.next();
					baseVoList.add(getVO(baseModel, resultVOClass, MessageCodes.SUCCESS, MessageCodes.GET_SUCCESS_MSG));
				}
			}
		}
		
		return baseVoList;
		
	}

	/**
	 * 
	 * @param VOList
	 * @return
	 */
	public BaseReturnVO getBaseVOArray(List<? extends BaseVO> VOList) {
		BaseVO[] baseVOArr = null;

		int len = VOList.size();
		if (len > 0) {
			Iterator it = VOList.iterator();

			baseVOArr = new BaseVO[len];
			int i = 0;
			while (it.hasNext()) {

				baseVOArr[i] = (BaseVO) it.next();
				baseVOArr[i].setMessageId(MessageCodes.SUCCESS);
				baseVOArr[i].setMessage(MessageCodes.GET_SUCCESS_MSG);

				i++;
			}
		}
		return handleAfterServiceCall(baseVOArr);
	}
	
	public BaseReturnVO getBaseVOArray(BaseVO[] baseVOArr) {
	
		return handleAfterServiceCall(baseVOArr);
	}

	/**
	 * this is needed when we dont have a model class from service and then get
	 * it from VO
	 * 
	 * @param baseVO
	 * @return
	 */
	public BaseReturnVO getBaseVO(BaseVO baseVO) {

		if (baseVO != null) {

			baseVO.setMessageId(MessageCodes.SUCCESS);
			baseVO.setMessage(MessageCodes.GET_SUCCESS_MSG);
		} else {
			baseVO.setMessageId(MessageCodes.NO_RECORDS_FOUND);
			baseVO.setMessage(MessageCodes.NO_RECORDS_FOUND_MSG);
		}
		return handleAfterServiceCall(baseVO);
	}

	private ReturnSingleVO handleAfterServiceCall(BaseVO baseVO) {
		ReturnSingleVO returnVO = null;
		if (baseVO != null) {
			returnVO = (ReturnSingleVO) baseVO.constructReturnVO();
		} else {
			returnVO = new ReturnSingleVO(MessageCodes.NO_RECORDS_FOUND, MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(returnVO);
		}
		return returnVO;
	}

	private ReturnListVO handleAfterServiceCall(BaseVO[] baseVO) {
		ReturnListVO returnArrVO = null;
		if (baseVO != null && baseVO.length > 0) {
			returnArrVO = baseVO[0].constructReturnArrVO(baseVO);
		} else {
			returnArrVO = new ReturnListVO(MessageCodes.NO_RECORDS_FOUND, MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(returnArrVO);
		}
		return returnArrVO;
	}

	protected Pageable getPagableObject(SearchVO searchVO, Sort sort) {

		Pageable pageable = null;
		Integer pageNumber = searchVO.getPageNumber();
		Integer pageSize = searchVO.getPageSize();

		// Set PageNumber to 0 if it is not set
		if (pageNumber == null || pageNumber < 0) {
			pageNumber = 0;
		}

		// Pagination Required or not
		if (pageSize != null && pageSize > 0) {
			if (sort != null) {
				pageable = new PageRequest(pageNumber, pageSize, sort);
			} else {
				pageable = new PageRequest(pageNumber, pageSize);
			}
		}

		return pageable;
	}

	private Sort getSortObject(SearchVO searchVO) {
		Sort sort = null;
		if (searchVO != null) {
			String[] sortBy = searchVO.getSortBy();
			String sortOrder = searchVO.getSortOrder();
			// Set SortOrder to ASC if not set
			if (sortOrder == null || (!sortOrder.equalsIgnoreCase("ASC") && !sortOrder.equalsIgnoreCase("DESC"))) {
				sortOrder = "ASC";
			}
			// Sorting Required or not
			if (sortBy != null && sortBy.length > 0) {
				sort = new Sort(Sort.Direction.fromString(sortOrder), sortBy);
			}
		}

		return sort;
	}

	protected Sort getSortObject(SearchVO searchVO, String[] defaultSortBy, String defaultSortOrder) {
		Sort sort = null;
		if (searchVO != null) {
			String[] sortBy = searchVO.getSortBy();
			String sortOrder = searchVO.getSortOrder();
			if (sortOrder == null || (!sortOrder.equalsIgnoreCase("ASC") && !sortOrder.equalsIgnoreCase("DESC"))) {
				if (defaultSortOrder != null
						&& (sortOrder.equalsIgnoreCase("ASC") || sortOrder.equalsIgnoreCase("DESC"))) {
					searchVO.setSortOrder(defaultSortOrder);
				} else {
					searchVO.setSortOrder("ASC");
				}
			}
			if (sortBy == null || sortBy.length == 0) {
				if (defaultSortBy != null && defaultSortBy.length > 0) {
					searchVO.setSortBy(defaultSortBy);
				} else {
					String[] sortArr = { "id" };
					searchVO.setSortBy(sortArr);
				}
			}
		}
		return getSortObject(searchVO);
	}

	protected SearchVO createSearchVO(Object... args) {
		SearchVO searchVO = null;
		
		if (args != null && args.length >= 5) {
			searchVO = new SearchVO();
			if (args[0] != null) {
				searchVO.setPageSize((Integer) args[1]);
			}
			if (args[1] != null) {
				searchVO.setPageNumber((Integer) args[2]);
			}
			if (args[3] != null) {
				List<String> sortByList = ((List<String>) args[3]);
				String[] sortBy = (String[]) sortByList.toArray(new String[sortByList.size()]);
				searchVO.setSortBy(sortBy);
			}
			if (args[4] != null) {
				searchVO.setSortOrder((String) args[4]);
			}
		}
		return searchVO;
	}

	public BaseModel setCommonFields(BaseModel baseModel) {
		Timestamp ts = getCurrentTimeStamp();
		baseModel.setCreatedDate(ts);
		baseModel.setLastModifiedDate(ts);
	//	baseModel.setActiveIndicator(1L);
		return baseModel;

	}
	
	public BaseVO setCommonFieldsForInsertVO(BaseVO baseVO) {
		Timestamp ts = getCurrentTimeStamp();
		baseVO.setCreatedTimeStamp(ts);
		baseVO.setUpdatedTimeStamp(ts);
		baseVO.setActiveIndicator(1L);
		return baseVO;

	}
	
	public BaseModel setCommonFieldsForInsert(BaseModel baseModel) {
		Timestamp ts = getCurrentTimeStamp();
		baseModel.setCreatedDate(ts);
		baseModel.setLastModifiedDate(ts);
	//	baseModel.setActiveIndicator(1L);
		return baseModel;

	}

	public BaseVO setCommonFieldsForUpdateVO(BaseVO baseVO) {
		Timestamp ts = getCurrentTimeStamp();
		baseVO.setUpdatedTimeStamp(ts);
		baseVO.setActiveIndicator(1L);
		return baseVO;
	}
	
	public BaseModel setCommonFieldsForUpdate(BaseModel baseModel) {
		Timestamp ts = getCurrentTimeStamp();
		baseModel.setLastModifiedDate(ts);
	//	baseModel.setActiveIndicator(1L);
		return baseModel;
	}

	public ArrayList<? extends BaseModel> setCommonFields(ArrayList<? extends BaseModel> baseModel) {
		Timestamp ts = getCurrentTimeStamp();

		Iterator<? extends BaseModel> it = baseModel.iterator();
		while (it.hasNext()) {
			BaseModel baModel = it.next();
			baModel.setCreatedDate(ts);
			baModel.setLastModifiedDate(ts);
	//		baModel.setActiveIndicator(1L);
		}
		return baseModel;

	}
	
	// Get the first part of the String.
	// Example - if the input is applicants.customer.personalInfo
	// Return String = applicants
	protected String getFirstPart(String inputString){
		String returnString = null;
		
		if (inputString != null) {
			// Tokenize the inputString with delimitter as .
			StringTokenizer tokenizer = new StringTokenizer(inputString,".");
			returnString = tokenizer.nextToken();
		}
		return returnString;
	}
	
	
	// Truncate the first part of the String and return
	// Example - if the input is applicants.customer.personalInfo
	// Return String = customer.personalInfo
	protected String getTruncatedString(String inputString){
		String returnString = null;
		
		if (inputString != null) {
			
			int i = inputString.indexOf('.');
			if (i > 0){
				returnString = inputString.substring(i+1);
			}
		}
		return returnString;
	}
	
	public Map<String, List<String>> getRequiredFields(String[] requiredFields){
	
		Map<String, List<String>> requiredFieldMap = new HashMap<String,List<String>>();
		System.out.println("Base Service");
		List<String> secondPartList = null;
		int index = 0;
		int len = requiredFields.length;
		while (index < len) {
			String field = requiredFields[index];
			String firstPart = getFirstPart(field);
			String truncatedPart = getTruncatedString(field);
			
			//If requiredField does not have the firstPart as key,
			// Create a new List and add the truncatedPat to the list and add to the requiredFieldMap
			if(!requiredFieldMap.containsKey(firstPart)){
				secondPartList = new ArrayList<String>();
				secondPartList.add(truncatedPart);	
				requiredFieldMap.put(firstPart, secondPartList);
			}else{
				// Add the truncatedPart to the list
				List tempList = requiredFieldMap.get(firstPart);
				tempList.add(truncatedPart);
				requiredFieldMap.put(firstPart, tempList);
			}
			index++;
		}	
		return requiredFieldMap;
		
	}
	
	
}
