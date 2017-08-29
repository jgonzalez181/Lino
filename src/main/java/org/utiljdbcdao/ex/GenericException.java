package org.utiljdbcdao.ex;

import java.sql.Timestamp;

abstract class GenericException extends RuntimeException {

	private static final long serialVersionUID = 6937809516512058437L;
	protected String layer = "unknown";
	protected Long layerCode = 0L;
	protected String title = "";
	protected String message = "";
	protected Timestamp time;

	@SuppressWarnings("rawtypes")
	protected Class throwerClass;
	protected Exception thirdException;
	private String firstTrace = "unknown";

	public String getTitle() {
		return title;
	}

	public String getFirstTrace() {
		return firstTrace;
	}

	public void setFirstTrace(String firstTrace) {
		this.firstTrace = firstTrace;
	}

	@SuppressWarnings("rawtypes")
	public void setFirstTrace(Exception e, Class T) {
		for (StackTraceElement st : e.getStackTrace()) {
			if (T.getName().equals(st.getClassName())) {
				this.firstTrace = "(" + st.getClassName() + "."
						+ st.getMethodName() + ")" + " (" + st.getFileName()
						+ " " + "Linea: " + st.getLineNumber() + ") "
						+ e.getMessage();
				break;
			}
		}

	}

	@SuppressWarnings("rawtypes")
	public GenericException(Class throwerClass) {
		super();
		this.throwerClass = throwerClass;
		time = new Timestamp(System.currentTimeMillis());

	}

	@SuppressWarnings("rawtypes")
	public GenericException(Class throwerClass, Exception thirdException) {
		super();
		this.throwerClass = throwerClass;
		this.thirdException = thirdException;
		time = new Timestamp(System.currentTimeMillis());

		if (thirdException instanceof GenericException) {

			this.setThirdException(((GenericException) thirdException)
					.getThirdException());

			this.setFirstTrace(((GenericException) thirdException)
					.getFirstTrace());
		}
	}

	public Exception getThirdException() {
		return thirdException;
	}

	public void setThirdException(Exception thirdException) {
		this.thirdException = thirdException;
	}

	public Long getCode() {
		Long code = 0L;

		for (char c : this.getClass().getName().toCharArray()) {
			code += (int) c;
		}

		return code + layerCode;
	}

	public String getMessage() {
		return "[" + getCode() + "] " + title + ": " + message;
	}

	public String getTechnicalMessage() {

		return "\n\n\n[" + time + " Start Exception:" + getCode() + " "
				+ this.getClass().getCanonicalName() + "]"
				+ getTechnicalMessageTmp() + "\n[" + time + " End Exception:"
				+ getCode() + " " + this.getClass().getCanonicalName()
				+ "]\n\n\n";
	}

	private String getTechnicalMessageTmp() {
		if (this.getFirstTrace() == null) {
			firstTrace = "unknown";
		} else if (this.getFirstTrace() != null
				&& this.getFirstTrace().trim().length() == 0) {
			firstTrace = "unknown";
		}

		if (this.thirdException == null
				&& this.getFirstTrace().equalsIgnoreCase("unknown") == false) {
			return "\n[" + getCode() + "] = " + "Subject: " + title + "\n["
					+ getCode() + "] = " + "Message: " + message + "\n["
					+ getCode() + "] = " + "Thrower Class: " + throwerClass
					+ "\n[" + getCode() + "] = " + "Layer: " + layer + "\n["
					+ getCode() + "] = " + "First Trace: "
					+ this.getFirstTrace();
		} else if (this.thirdException != null
				&& this.getFirstTrace().equalsIgnoreCase("unknown") == true) {
			return "\n[" + getCode() + "] = " + "Subject: " + title + "\n["
					+ getCode() + "] = " + "Message: " + message + "\n["
					+ getCode() + "] = " + "Thrower Class: " + throwerClass
					+ "\n[" + getCode() + "] = " + "Layer: " + layer + "\n["
					+ getCode() + "] = " + "Cause: " + this.thirdException;
		}

		return "\n[" + getCode() + "] = " + "Subject: " + title + "\n["
				+ getCode() + "] = " + "Message: " + message + "\n["
				+ getCode() + "] = " + "Thrower Class: " + throwerClass + "\n["
				+ getCode() + "] = " + "Layer: " + layer + "\n[" + getCode()
				+ "] = " + "First Trace: " + this.getFirstTrace() + "\n["
				+ getCode() + "] = " + "Cause: " + this.thirdException;

	}

	public String toString() {
		String stackTrace = "";
		for (StackTraceElement st : getStackTrace()) {
			stackTrace += "\n" + st;
		}

		if (thirdException != null) {
			String stackTraceThirdException = "";
			for (StackTraceElement st : thirdException.getStackTrace()) {
				stackTraceThirdException += "\n" + st;
			}
			return "\n\n\n[" + time + " Start Exception:" + getCode() + " "
					+ this.getClass().getCanonicalName() + "]"
					+ getTechnicalMessageTmp() + "\n[" + getCode() + "] = "
					+ "Stack Trace:\n" + stackTrace + "\n\n[" + getCode()
					+ "] = " + "Stack Trace Third Exception:\n"
					+ stackTraceThirdException + "\n\n[" + time
					+ " End Exception:" + getCode() + " "
					+ this.getClass().getCanonicalName() + "]\n\n\n";
		} else {
			return "\n\n\n[" + time + " Start Exception:" + getCode() + " "
					+ this.getClass().getCanonicalName() + "]"
					+ getTechnicalMessageTmp() + "\n[" + getCode() + "] = "
					+ "Stack Trace: " + stackTrace + "\n\n[" + time
					+ " End Exception:" + getCode() + " "
					+ this.getClass().getCanonicalName() + "]\n\n\n";
		}
	}

}
