package br.com.patiolegal.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String fieldName;
    private String message;

    public BusinessException(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;

    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }

}
