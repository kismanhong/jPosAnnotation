package com.softtech.jpos.exception;

/**
 * @author Kisman Hong
 *
 */
public class JPosAnnotationException extends Exception {
	 /**
		 * 
		 */
		private static final long serialVersionUID = -3224929220568773428L;

	    /**
	     * error/exception code
	     */
	    private String code;

	    /**
	     * error description value/s
	     */
	    private Object[] parameterValues = null;

	    /**
	     * default constructor
	     */
	    public JPosAnnotationException() {
	    }


	    /**
	     * @param message
	     * constructor for accepting message
	     */
	    public JPosAnnotationException(String message) {
	        super(message);
	        setCode(message);
	    }

	    /**
	     * @param message
	     * @param code
	     * constructor for accepting message and code
	     */
	    public JPosAnnotationException(String message, String code) {
	        super(message);
	        setCode(code);
	    }


	    /**
	     * @param message
	     * @param code
	     * @param params
	     * constructor for accepting message, code, and params
	     */
	    public JPosAnnotationException(String message, String code, Object[] params) {
	        super(message);
	        setCode(code);
	        setParameterValues(params);
	    }

	    /**
	     * @param cause
	     */
	    public JPosAnnotationException(Throwable cause) {
	        super(cause);
	        if (cause instanceof JPosAnnotationException) {
	            setCode(((JPosAnnotationException) cause).getCode());
	            setParameterValues(((JPosAnnotationException) cause).getParameterValues());
	        }
	    }

	    /**
	     * @param message
	     * @param cause
	     */
	    public JPosAnnotationException(String message, Throwable cause) {
	        super(message, cause);
	        setCode(message);
	    }

	    /**
	     * @param code
	     * @param params
	     * @param cause
	     */
	    public JPosAnnotationException(String code, Object[] params, Throwable cause) {
	        super(cause);
	        setCode(code);
	        setParameterValues(params);
	    }

	    /**
	     * @param code
	     * @param param
	     * @param cause
	     */
	    public JPosAnnotationException(String code, Object param, Throwable cause) {
	        this(code, new Object[]{param}, cause);
	    }
	    
	    /**
	     * @param code
	     * @param params
	     */
	    public JPosAnnotationException(String code, Object[] params) {
//	        super(ErrorMessages.global.getMessage(code, params));
	        setCode(code);
	        setParameterValues(params);
	    }

	    /**
	     * @param code
	     * @param param
	     */
	    public JPosAnnotationException(String code, Object param) {
	        this(code, new Object[]{param});
	    }
	 
	    /**
	     * @return
	     */
	    public String getKismissRootMessage() {
	        Throwable cause = this;
	        while (cause.getCause() != null && cause.getCause() instanceof JPosAnnotationException) {
	            cause = cause.getCause();
	        }
	        return ((JPosAnnotationException) cause).getLocalizedMessage();
	    }

		/**
		 * @return
		 */
		public String getRootMessage() {
			Throwable cause = this;
			while (cause.getCause() != null) {
				cause = cause.getCause();
			}
			return cause.getLocalizedMessage();
		}

		/**
	     * @return
	     */
	    public String getCode() {
	        return code;
	    }


	    /**
	     * @param code
	     */
	    public void setCode(String code) {
	        this.code = code;
	    }

	    /**
	     * @return
	     */
	    public Object[] getParameterValues() {
	        return parameterValues;
	    }

	    /**
	     * @param objects
	     */
	    public void setParameterValues(Object[] objects) {
	        parameterValues = objects;
	    }
}
