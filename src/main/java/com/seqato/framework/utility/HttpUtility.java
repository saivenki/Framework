package com.seqato.framework.utility;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seqato.framework.vo.BaseReturnVO;
import com.seqato.framework.vo.BaseVO;
import com.seqato.framework.vo.ReturnListVO;
import com.seqato.framework.vo.ReturnSingleVO;


public class HttpUtility {


	/**
	 * Do HTTP Get  
	 * @param urlPath
	 * @param destinationClass
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public static BaseReturnVO  get(String urlPath, Class<? extends BaseVO> destinationClass,Object... args) throws JsonProcessingException, IOException{
		
		// Call the RestTemplate to get the Json String.
		RestTemplate restTemplate = new RestTemplate();
		String returnedString = null;
		if (args != null && args.length > 0){
			returnedString = restTemplate.getForObject(urlPath, String.class,args);
		} else {
			returnedString = restTemplate.getForObject(urlPath, String.class);
		}
		// Fill the ReturnSingleVO fields from the Json String
		BaseReturnVO returnSingleVO = getReturnSingleVO(returnedString,destinationClass);
		
		return returnSingleVO;
	}
	
	public static BaseReturnVO  get(String urlPath, Class<? extends BaseVO> destinationClass, Class<? extends BaseVO[]> destinationArrClass,Object... args) throws JsonProcessingException, IOException{
		
		// Call the RestTemplate to get the Json String.
		RestTemplate restTemplate = new RestTemplate();
		String returnedString = null;
		if (args != null && args.length > 0){
			returnedString = restTemplate.getForObject(urlPath, String.class,args);
		} else {
			returnedString = restTemplate.getForObject(urlPath, String.class);
		}
		// Fill the ReturnSingleVO fields from the Json String
		BaseReturnVO returnSingleVO = getReturnVO(returnedString,destinationClass,destinationArrClass);
		
		return returnSingleVO;
	}
	
	
	
	public static ReturnListVO getAll(String urlPath, Class<? extends BaseVO[]> destinationClass,Object... args) throws JsonProcessingException, IOException{
		// Call the RestTemplate to get the Json String.
		RestTemplate restTemplate = new RestTemplate();
		
		String returnedString = null;
		if (args != null && args.length > 0){
			returnedString = restTemplate.getForObject(urlPath, String.class,args);
		} else {
			returnedString = restTemplate.getForObject(urlPath, String.class);
		}
		// Fill the ReturnArrayVO fields from the Json String
		ReturnListVO returnArrayVO = getReturnArrayVO(returnedString,destinationClass);
		return returnArrayVO;	
	}
	
	public static BaseReturnVO getAll(String urlPath, Class<? extends BaseVO> destinationClass, Class<? extends BaseVO[]> destinationArrClass,Object... args) throws JsonProcessingException, IOException{
		// Call the RestTemplate to get the Json String.
		RestTemplate restTemplate = new RestTemplate();
		
		String returnedString = null;
		if (args != null && args.length > 0){
			returnedString = restTemplate.getForObject(urlPath, String.class,args);
		} else {
			returnedString = restTemplate.getForObject(urlPath, String.class);
		}
		// Fill the ReturnArrayVO fields from the Json String
		BaseReturnVO returnVO = getReturnVO(returnedString,destinationClass,destinationArrClass); // getReturnArrayVO(returnedString,destinationClass);
		return returnVO;	
	}
	
	public static BaseReturnVO post(String urlPath, Class<? extends BaseVO> destinationClass,
			Class<? extends BaseVO[]> destinationArrClass,BaseVO baseVO,Object... args) throws JsonProcessingException, IOException{
   		    
		RestTemplate restTemplate = new RestTemplate();
		String returnedString = null;
		if (args != null && args.length > 0){
			returnedString  = restTemplate.postForObject(urlPath, baseVO,String.class,args);
		} else {
			returnedString  = restTemplate.postForObject(urlPath, baseVO,String.class);
		}
		
		BaseReturnVO returnVO = null;
		
		// Fill either with ReturnSingleVO Or ReturnArrayVO fields from the Json String
		returnVO = getReturnVO(returnedString, destinationClass, destinationArrClass);
		return returnVO;
	}
	
	private static BaseReturnVO getReturnVO(String returnedString, Class<? extends BaseVO> destinationClass, Class<? extends BaseVO[]> destinationArrClass) throws JsonProcessingException, IOException{
		BaseReturnVO returnVO = null;
		try{
			returnVO = getReturnSingleVO(returnedString,destinationClass);
		} catch (JsonMappingException e){
			//e.printStackTrace();
			returnVO = getReturnArrayVO(returnedString, destinationArrClass);
		}
		return returnVO;
	}
	
//	public static void put(String urlPath,BaseVO baseVO,Object... args){
//		RestTemplate restTemplate = new RestTemplate();
//		if (args != null && args.length > 0){
//			restTemplate.put(urlPath, baseVO,args);  
//		} else {
//			restTemplate.put(urlPath, baseVO);  
//		}
//	}
	
	public static BaseReturnVO put(String urlPath,BaseVO baseVO, Class<? extends BaseVO> destinationClass,Object... args) throws JsonProcessingException, IOException{
		RestTemplate restTemplate = new RestTemplate();
		String returnedString = null;
		HttpEntity<BaseVO> httpEntityVO = new HttpEntity<BaseVO>(baseVO);
		if (args != null && args.length > 0){
			//restTemplate.put(urlPath, baseVO,args);  
			ResponseEntity<String> response = restTemplate.exchange(urlPath,HttpMethod.PUT,httpEntityVO,String.class,args);
			returnedString = response.getBody();
		} else {
			ResponseEntity<String> response = restTemplate.exchange(urlPath,HttpMethod.PUT,httpEntityVO,String.class);
			returnedString = response.getBody();
		}
		BaseReturnVO returnSingleVO = getReturnSingleVO(returnedString,destinationClass);
		return returnSingleVO;
	}
	
	public static BaseReturnVO put(String urlPath,BaseVO baseVO, Class<? extends BaseVO> destinationClass,Class<? extends BaseVO[]> destinationArrClass, Object... args) throws JsonProcessingException, IOException{
		RestTemplate restTemplate = new RestTemplate();
		String returnedString = null;
		HttpEntity<BaseVO> httpEntityVO = new HttpEntity<BaseVO>(baseVO);
		if (args != null && args.length > 0){
			//restTemplate.put(urlPath, baseVO,args);  
			ResponseEntity<String> response = restTemplate.exchange(urlPath,HttpMethod.PUT,httpEntityVO,String.class,args);
			returnedString = response.getBody();
		} else {
			ResponseEntity<String> response = restTemplate.exchange(urlPath,HttpMethod.PUT,httpEntityVO,String.class);
			returnedString = response.getBody();
		}
		BaseReturnVO returnVO = getReturnVO(returnedString,destinationClass,destinationArrClass);
		return returnVO;
	}


	public static BaseReturnVO delete(String urlPath,Object... args) throws JsonProcessingException, IOException{
		RestTemplate restTemplate = new RestTemplate();
		String returnedString = null;
		if (args != null && args.length > 0){
			//restTemplate.put(urlPath, baseVO,args);  
			ResponseEntity<String> response = restTemplate.exchange(urlPath,HttpMethod.DELETE,null,String.class,args);
			returnedString = response.getBody();
		} else {
			ResponseEntity<String> response = restTemplate.exchange(urlPath,HttpMethod.DELETE,null,String.class);
			returnedString = response.getBody();
		}
		BaseReturnVO returnSingleVO = getReturnSingleVO(returnedString);
		return returnSingleVO;
	}
	

	/**
	 * Fill the ReturnSingleVO from the jsonString and return
	 * @param jsonString
	 * @param destinationClass
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	private static BaseReturnVO getReturnSingleVO( String  jsonString,Class<? extends BaseVO> destinationClass) throws JsonProcessingException, IOException{
		ReturnSingleVO returnSingleVO = new ReturnSingleVO();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode returnTree = mapper.readTree(jsonString);
		
		// Get the Values of ReturnSingleVO from the returned Json
		returnSingleVO.setMessage(returnTree.get("message").textValue());
		returnSingleVO.setMessageId(returnTree.get("messageId").intValue());
		returnSingleVO.setModuleId(returnTree.get("moduleId").intValue());
		returnSingleVO.setModuleName(returnTree.get("moduleName").textValue());
		returnSingleVO.setVoName(returnTree.get("voName").textValue());
//		if (returnTree.get("totalNoOfPages") != null){
//			returnSingleVO.setTotalNoOfPages(returnTree.get("totalNoOfPages").intValue());
//			returnSingleVO.setCurrentPageNumber(returnTree.get("currentPageNumber").intValue());
//		}
		fillResult(returnSingleVO, returnTree,destinationClass);
		return returnSingleVO;
	}
	
	
	private static BaseReturnVO getReturnSingleVO( String  jsonString) throws JsonProcessingException, IOException{
		ReturnSingleVO returnSingleVO = new ReturnSingleVO();
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode returnTree = mapper.readTree(jsonString);
		
		// Get the Values of ReturnSingleVO from the returned Json
		returnSingleVO.setMessage(returnTree.get("message").textValue());
		returnSingleVO.setMessageId(returnTree.get("messageId").intValue());
		returnSingleVO.setModuleId(returnTree.get("moduleId").intValue());
		returnSingleVO.setModuleName(returnTree.get("moduleName").textValue());
		returnSingleVO.setVoName(returnTree.get("voName").textValue());
//		if (returnTree.get("totalNoOfPages") != null){
//			returnSingleVO.setTotalNoOfPages(returnTree.get("totalNoOfPages").intValue());
//			returnSingleVO.setCurrentPageNumber(returnTree.get("currentPageNumber").intValue());
//		}
		return returnSingleVO;
	}
	
	
	
	private static ReturnListVO getReturnArrayVO( String  jsonString,Class<? extends BaseVO[]> destinationClass) throws JsonProcessingException, IOException{
		ReturnListVO returnArrayVO = new ReturnListVO();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnTree = mapper.readTree(jsonString);
		
		// Get the Values of ReturnSingleVO from the returned Json
		returnArrayVO.setMessage(returnTree.get("message").textValue());
		returnArrayVO.setMessageId(returnTree.get("messageId").intValue());
		returnArrayVO.setModuleId(returnTree.get("moduleId").intValue());
		returnArrayVO.setModuleName(returnTree.get("moduleName").textValue());
		returnArrayVO.setVoName(returnTree.get("voName").textValue());
		if (returnTree.get("totalNoOfPages") != null){
			returnArrayVO.setTotalNoOfPages(returnTree.get("totalNoOfPages").intValue());
			returnArrayVO.setCurrentPageNumber(returnTree.get("currentPageNumber").intValue());
		}
		fillResultArray(returnArrayVO, returnTree,destinationClass);
		return returnArrayVO;
	}
	
	
	/**
	 * 
	 * @param returnSingleVO
	 * @param returnTree
	 * @param destinationClass
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	private static ReturnSingleVO fillResult(ReturnSingleVO returnSingleVO,JsonNode returnTree,Class<? extends BaseVO> destinationClass) throws JsonParseException, JsonMappingException, IOException{
	
		ObjectMapper mapper = new ObjectMapper();
		
		// Get the actual VO from the result node of the returned Json
		JsonNode resultNode = returnTree.get("result");
		if(resultNode!=null){
			String resultJsonString = resultNode.toString();
			BaseVO baseVO = mapper.readValue(resultJsonString,destinationClass);
			System.out.println("ReturnedVO == "+baseVO);
			returnSingleVO.setData(baseVO);
		}
		return returnSingleVO;
	}
	
	private static ReturnListVO fillResultArray(ReturnListVO returnArrayVO,JsonNode returnTree,Class<? extends BaseVO[]> destinationClass) throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		// Get the actual VO from the result node of the returned Json
		System.out.println("Is result missing in "+returnTree + "\n Destination Class == "+destinationClass);
		JsonNode resultNode = returnTree.get("result");
		if(resultNode!=null){
			String resultJsonString = resultNode.toString();
			BaseVO [] baseVOArray = mapper.readValue(resultJsonString,destinationClass);
			System.out.println("ReturnedVO == "+baseVOArray);
			returnArrayVO.setData(baseVOArray);
		}
		return returnArrayVO;
	}
	
	/**
	 * 
	 * getAll method have 3 parameters
	 * 1. url for GET ALL
	 * 2. VO class ,here the output is in array format so the class name we gives as (eg. TypesVO[].class)
	 * 3. arguments or url path params  (eg."http://localhost:8080/Type/types/{id}/typeDetails" id are paramters  )
	 *  ReturnArrayVO returnArrayVO;
			try {
				returnArrayVO = HttpUtility.getAll("http://localhost:8080/Type/types", TypesVO[].class,id);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 */
	
	/** get method have 3 paramters
	 * 1. url for GET
	 * 2. VO class ,here the output is in array format so the class name we gives as (eg. TypesVO.class)
	 * 3. arguments or url path paramters  (eg."http://localhost:8080/Type/types/{id}/typeDetails/{id1}" id,id1 are paramters  )
	 *  ReturnSingleVO returnSingleVO;
			try {
				returnSingleVO = HttpUtility.get("http://localhost:8080/Type/types/{id}", TypesVO.class,id,id1);
				 System.out.println("From HTTP Return == "+returnSingleVO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 */
	
	/** create method have 4 parameters
	 * 1. url of POST
	 * 2. VO class ,here the output is in array format so the class name we gives as (eg. TypesVO.class)
	 * 3. VO object for passings values 
	 * 4. arguments or url params  (eg."http://localhost:8080/Type/types/{id}/typeDetails" id are paramters  )
	 * 
	 * 	try {
				TypesVO type1 = new TypesVO();
				type1.setDescription("testTypeinserted");
				type1.setCreatedBy(29l);
				BaseVO baseVO = getVO(type1,TypesVO.class);
				 ReturnSingleVO returnSingleVO = HttpUtility.create("http://localhost:8080/Type/types", TypesVO.class,baseVO,id);
				
			HttpUtility.delete("http://localhost:8080/Office/"+MasterUrls.ROOT_OFFICE_URL+MasterUrls.OFFICE_ID_URL,1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 */
	/**
	 * update method have 4 parameters
	 * 1. url of PUT
	 * 2. VO object for passings values 
	 * 3. arguments or url params  (eg."http://localhost:8080/Type/types/{id}/typeDetails/{id2}" id,id2 are paramters  )
	 * 
	 * 	try {
				 Types type2 = new Types();
					type2.setDescription("testType3updated");
					type2.setCreatedBy(28l);
					BaseVO baseVO2 = getVO(type2,TypesVO.class);	
				HttpUtility.update("http://localhost:8080/Type/types/{id}", baseVO2, 2);
				
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 */
	
	/**
	 * * delete method have 4 parameters
	 * 1. url of DELETE
	 * 2. arguments or url params  (eg."http://localhost:8080/Type/types/{id}/typeDetails/{id2}" id,id2 are paramters  )
	 * 
	 * 	try {
	 * 
	 * HttpUtility.delete("http://localhost:8080/Type/types/{id}/typeDetails/{id2}",id2);
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 */

}
