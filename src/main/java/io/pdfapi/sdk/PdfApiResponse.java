package io.pdfapi.sdk;

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

    InputStream getContents() throws IOException {
        return response.getEntity().getContent();
    }
}
