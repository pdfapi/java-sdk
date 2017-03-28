package io.pdfapi.sdk.exception;

import io.pdfapi.sdk.response.ApiErrorResponse;

public class BadRequestException extends PdfApiException {
    private final String field;

    public BadRequestException(ApiErrorResponse response) {
        super(response.error);
        this.field = response.field;
    }

    public String getField() {
        return field;
    }
}
