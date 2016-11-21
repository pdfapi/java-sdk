package io.pdfapi.sdk;

import java.util.Map;

/**
 * Created by tobre on 09/11/2016.
 */
public class PdfApiRequest {

    private String method;
    private String endpoint;
    private Map<String, Object> body;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public void setBody(Map<String, Object> body) {
        this.body = body;
    }
}
