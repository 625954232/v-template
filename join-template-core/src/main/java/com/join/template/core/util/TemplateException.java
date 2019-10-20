package com.join.template.core.util;


public class TemplateException extends RuntimeException {
    public TemplateException() {
    }

    public TemplateException(String message) {
        super(message);
    }

    public TemplateException(String message, String... args) {
        super(String.format(message, args));
    }

    public TemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateException(Throwable cause) {
        super(cause);
    }

    public TemplateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
