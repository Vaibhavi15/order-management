package com.vaibhavi.intuit.demo.ordermanagement.response;

import java.util.Map;

public class ValidationErrorResponse extends ErrorResponse{

		private Map<String,String> errors;
		
		public Map<String, String> getErrors() {
			return errors;
		}
		public void setErrors(Map<String, String> errors) {
			this.errors = errors;
		}

}
