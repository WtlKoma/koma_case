package com.wtl.koma.exception;
/**
 * <p>Description: 参数缺失</p>
 * @author WTL
 * @date 2019年5月10日
 */
public class MissingParametersException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3678095937165138748L;
	
	public MissingParametersException() {
        super();
    }
	
	public MissingParametersException(String message) {
        super(message);
    }

}

