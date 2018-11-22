package com.seqato.framework;

import org.springframework.dao.EmptyResultDataAccessException;

import com.seqato.framework.utility.MessageCodes;
import com.seqato.framework.vo.BaseReturnVO;
import com.seqato.framework.vo.BaseVO;
import com.seqato.framework.vo.ReturnListVO;
import com.seqato.framework.vo.ReturnMapVO;
import com.seqato.framework.vo.ReturnSingleVO;

public abstract class BaseController {
	
	protected static int MODULE_ID;
	protected static String MODULE_NAME;
	
	private void setBaseParams(BaseReturnVO baseReturnVO){
		baseReturnVO.setModuleId(MODULE_ID);
		baseReturnVO.setModuleName(MODULE_NAME);
	}
	
	private ReturnSingleVO handleAfterServiceCall(BaseVO baseVO){
		ReturnSingleVO returnVO = null;
		if (baseVO != null){
			returnVO = (ReturnSingleVO) baseVO.constructReturnVO();
		} else {
			returnVO = new ReturnSingleVO(MessageCodes.NO_RECORDS_FOUND,MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(returnVO);
		}
		return returnVO;
	}
	
	private ReturnListVO handleAfterServiceCall(BaseVO[] baseVO){
		ReturnListVO returnArrVO = null;
		if(baseVO != null && baseVO.length >0){
			returnArrVO = baseVO[0].constructReturnArrVO(baseVO);
		} else{
			returnArrVO = new ReturnListVO(MessageCodes.NO_RECORDS_FOUND,MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(returnArrVO);
		}
		return returnArrVO;
	}
		
	/**
	 * Get single Record
	 * @param baseService
	 * @param id
	 * @return
	 */
	public ReturnSingleVO getByMasterTableId(BaseService baseService,Long masterTableId, Long childTableId, Class<? extends BaseVO> destinationClass) {
		ReturnSingleVO returnVO = null;
		try{
			System.out.println("In");
			BaseVO baseVO = baseService.getByMasterTableId(masterTableId, childTableId, destinationClass);
			returnVO = handleAfterServiceCall(baseVO);
			System.out.println("Out");
		}catch(Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
	 	}
		return returnVO;
	}
	
	public ReturnSingleVO get(BaseService baseService,Long[] ids, Class<? extends BaseVO> destinationClass) {
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.get(ids, destinationClass);
			returnVO = handleAfterServiceCall(baseVO);
		}catch(Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
	 	}
		return returnVO;
	}
	

	/**
	 * Create Record in the DB table
	 * @param baseService
	 * @param baseVO
	 * @return
	 */
	public ReturnSingleVO createByMasterTableId(BaseService baseService, Long masterTableId , BaseVO childTableVO, Class<? extends BaseVO> destinationClass) {
		ReturnSingleVO returnVO = null;
		try{
			System.out.println("Created By == "+childTableVO.getCreatedBy());
			BaseVO baseVO = baseService.createByMasterTableId(masterTableId,  childTableVO, destinationClass);
			if(baseVO != null ){
				returnVO = baseVO.constructReturnVO();
			} else{
				returnVO = new ReturnSingleVO(MessageCodes.UNABLE_TO_CREATE_RECORD,MessageCodes.UNABLE_TO_CREATE_RECORD_MSG);
				setBaseParams(returnVO);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO;
	}	
	
	public ReturnSingleVO create(BaseService baseService, Long[] ids , BaseVO childTableVO, Class<? extends BaseVO> destinationClass) {
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.create(ids,  childTableVO, destinationClass);
			if(baseVO != null ){
				returnVO = baseVO.constructReturnVO();
			} else{
				returnVO = new ReturnSingleVO(MessageCodes.UNABLE_TO_CREATE_RECORD,MessageCodes.UNABLE_TO_CREATE_RECORD_MSG);
				setBaseParams(returnVO);
			}
			
		}catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO;
	}	
	/**
	 * Update
	 * @param baseService
	 * @param masterTableId
	 * @param childTableVO
	 * @param modelClass
	 * @param destinationClass
	 * @return
	 */
	public ReturnSingleVO updateByMasterTableId(BaseService baseService, Long masterTableId , BaseVO childTableVO, Class<? extends BaseVO> destinationClass){
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.updateByMasterTableId(masterTableId,  childTableVO, destinationClass);
			returnVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO; 
	}
	
	public ReturnSingleVO update(BaseService baseService, Long[] ids , BaseVO childTableVO, Class<? extends BaseVO> destinationClass){
	
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.update(ids,  childTableVO, destinationClass);
			returnVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO; 
	}
	
	/**
	 * Get All 
	 * @param baseService
	 * @param masterTableId
	 * @param destinationClass
	 * @return
	 */
	public ReturnListVO getAllByMasterTableId(BaseService baseService, Long masterTableId , Class<? extends BaseVO> destinationClass){
		ReturnListVO returnArrVO = null;
		try{
			BaseVO[] baseVO = baseService.getAllByMasterTableId(masterTableId, destinationClass);
			returnArrVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnArrVO = new ReturnListVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnArrVO);
		}	
		return returnArrVO;
	} 
	
	public ReturnListVO getAll(BaseService baseService, Long[] ids , Class<? extends BaseVO> destinationClass){
		ReturnListVO returnArrVO = null;
		try{
			BaseVO[] baseVO = baseService.getAll(ids, destinationClass);
			returnArrVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnArrVO = new ReturnListVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnArrVO);
		}	
		return returnArrVO;
	}
	
	/**
	 * Delete
	 * @param baseService
	 * @param masterTableId
	 * @param childTableId
	 * @param modelClass
	 * @return
	 */
	public ReturnSingleVO deleteByMasterTable(BaseService baseService, Long masterTableId , Long childTableId){
		
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.deleteByMasterTable(masterTableId ,  childTableId);
			returnVO = handleAfterServiceCall(baseVO);
		} catch (EmptyResultDataAccessException ex) {
			ex.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.UNABLE_TO_DELETE_RECORD,MessageCodes.NO_RECORDS_FOUND_MSG);
			setBaseParams(returnVO);
		}
		catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO; 
	}
	
	public ReturnSingleVO deleteByIds(BaseService baseService, Long[] ids){
		ReturnSingleVO returnVO = null;
		
		try{
			BaseVO baseVO = baseService.deleteByIds(ids);
			returnVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO; 
	}	
	
	/**
	 * Patch record
	 */
	public ReturnSingleVO patchByMasterTableId(BaseService baseService, Long masterTableId , BaseVO childTableVO, Class<? extends BaseVO> destinationClass){
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.patchByMasterTableId(masterTableId,  childTableVO, destinationClass);
			returnVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO; 
	}
	public ReturnSingleVO patch(BaseService baseService, Long[] ids , BaseVO childTableVO, Class<? extends BaseVO> destinationClass){
		
		ReturnSingleVO returnVO = null;
		try{
			BaseVO baseVO = baseService.patch(ids,  childTableVO, destinationClass);
			returnVO = handleAfterServiceCall(baseVO);
		} catch (Exception e){
			e.printStackTrace();
			returnVO = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnVO);
		}
		return returnVO; 
	}

	
	public BaseReturnVO callInternalServiceHandler(BaseService baseService,BaseVO voData, Object... args) {
		BaseReturnVO returnMap = null;
		try{
			if (args!=null && args.length>0){
				returnMap = baseService.callInternalServiceHandler(voData,args);
			} else{
				returnMap = baseService.callInternalServiceHandler(voData);
			}
		}catch(Exception e){
			e.printStackTrace();
			returnMap = new ReturnSingleVO(MessageCodes.INTERNAL_SERVER_ERROR,MessageCodes.INTERNAL_SERVER_ERROR_MSG);
			setBaseParams(returnMap);

	 	}
		return returnMap;
	}
	
	public ReturnMapVO callExternalServiceHandler(BaseService baseService,BaseVO voData, Object... args) {
		ReturnMapVO returnMap = null;
		try{	
			if (args!= null && args.length>0){
				returnMap = baseService.callExternalServiceHandler(voData,args);
			} else{
				returnMap = baseService.callExternalServiceHandler(voData);
			}
		}catch(Exception e){
			e.printStackTrace();
			
	 	}
		return returnMap;
	}
	
	public ReturnMapVO callExternalServiceHandler(BaseService baseService,BaseVO[] voData, Object... args) {
		ReturnMapVO returnMap = null;
		try{	
			if (args!= null && args.length>0){
				returnMap = baseService.callExternalServiceHandler(voData,args);
			} else {
				returnMap = baseService.callExternalServiceHandler(voData);
			}
		}catch(Exception e){
			e.printStackTrace();
	 	}
		return returnMap;
	}
	
	
}
