package br.com.patiolegal.exception;

public class GenerateSealReportException extends RuntimeException {

	private static final long serialVersionUID = 23L;
	
	private Exception exception;
	
	public GenerateSealReportException(Exception exception) {
		this.exception = exception;
	}
	
	@Override
	public String getMessage() {
		return exception.toString();
	}

}
