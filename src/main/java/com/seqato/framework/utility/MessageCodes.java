package com.seqato.framework.utility;

public class MessageCodes {
	public static final int SUCCESS = 200;
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED_ACCESS = 401;
	public static final int METHOD_NOT_SUPPORTED = 405;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int NO_RECORDS_FOUND = 501;
	public static final int UNABLE_TO_CREATE_RECORD = 502;
	public static final int UNABLE_TO_UPDATE_RECORD = 503;
	public static final int UNABLE_TO_DELETE_RECORD = 504;

	
	public static final String DATA_INTEGRITY_VIOLATION_MSG = "Cannot create the record because of data integrity violation.";
	public static final String NO_RECORDS_FOUND_MSG = "No Records Found.";
	public static final String GET_SUCCESS_MSG ="Successfully got the required data.";
	public static final String DELETE_SUCCESS_MSG ="Successfully deleted the required data.";
	public static final String POST_SUCCESS_MSG ="Successfully created the record.";
	public static final String UPDATE_SUCCESS_MSG ="Successfully updated the data.";
	public static final String INTERNAL_SERVER_ERROR_MSG ="Something unexpected happened. Internal Server Error.";
	public static final String UNABLE_TO_CREATE_RECORD_MSG ="Unable to create record.";
	public static final String UNABLE_TO_DELETE_RECORD_MSG ="Unable to delete record.";
} 