package io.pdfapi.sdk.exception;

/**
 * Created by tobre on 09/11/2016.
 */
public class PdfApiException extends Exception {
    public PdfApiException() {
    }

    public PdfApiException(Throwable cause) {
        super(cause);
    }

    public PdfApiException(String message) {
        super(message);
    }
}
