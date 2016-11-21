package io.pdfapi.sdk;

import io.pdfapi.sdk.exception.PdfApiClientException;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tobre on 09/11/2016.
 */
public class PdfApiResponse {

    private HttpResponse response;

    PdfApiResponse(HttpResponse response) {
        this.response = response;
    }

    public InputStream getContents() {
        try {
            return response.getEntity().getContent();
        } catch (IOException e) {
            throw new PdfApiClientException(e);
        }
    }
}
