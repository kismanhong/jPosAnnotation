package com.softtech.jpos.exception;

/**
 * @author Kisman Hong
 *
 */
public class JPosAnnotationRuntimeException extends JPosAnnotationException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 151997398226701577L;

		/**
		 * default constructor
		 */
		public JPosAnnotationRuntimeException() {
			super();
		}

		/**
		 * @param message
		 * accepting message parameter
		 */
		public JPosAnnotationRuntimeException(String message) {
			super(message);
		}

		/**
		 * @param cause
		 */
		public JPosAnnotationRuntimeException(Throwable cause) {
			super(cause);
		}

		/**
		 * @param message
		 * @param cause
		 */
		public JPosAnnotationRuntimeException(String message, Throwable cause) {
			super(message, cause);
		}

		/**
		 * @param code
		 * @param params
		 * @param cause
		 */
		public JPosAnnotationRuntimeException(String code, Object[] params,
				Throwable cause) {
			super(code, params, cause);
		}

		/**
		 * @param code
		 * @param param
		 * @param cause
		 */
		public JPosAnnotationRuntimeException(String code, Object param, Throwable cause) {
			super(code, param, cause);
		}

		/**
		 * @param code
		 * @param params
		 */
		public JPosAnnotationRuntimeException(String code, Object[] params) {
			super(code, params);
		}

		/**
		 * @param code
		 * @param param
		 */
		public JPosAnnotationRuntimeException(String code, Object param) {
			super(code, param);
		}
	
}
