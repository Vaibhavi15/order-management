package com.vaibhavi.intuit.demo.ordermanagement.response;

import java.util.Map;

public class ValidationErrorResponse {

		private int status;
		private Map<String,String> errors;
		private long timeStamp;
		
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Map<String, String> getErrors() {
			return errors;
		}
		public void setErrors(Map<String, String> errors) {
			this.errors = errors;
		}
		public long getTimeStamp() {
			return timeStamp;
		}
		public void setTimeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
		}	

}
