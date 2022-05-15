package io.pdfapi.sdk;

import com.google.gson.Gson;
import io.pdfapi.sdk.exception.*;
import io.pdfapi.sdk.response.ApiErrorResponse;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by tobre on 09/11/2016.
 */
public class PdfApiClient {

    private static final String API_VERSION = "1";

    private final CloseableHttpClient httpClientHandler = HttpClients.createDefault();

    private final String baseUrl;

    PdfApiClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    PdfApiResponse send(PdfApiRequest request) throws PdfApiException {
        HttpPost httpPost = new HttpPost(baseUrl + "/v" + API_VERSION + request.getEndpoint());
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("User-Agent", "pdfapi.io Java SDK " + PdfApi.VERSION);

        httpPost.setEntity(new StringEntity(new Gson().toJson(request.getBody()), Charset.defaultCharset()));

        HttpResponse response;
        try {
            response = httpClientHandler.execute(httpPost);
        } catch (IOException e) {
            throw new PdfApiException(e);
        }

        handleBadRequest(response);

        if (isInternalError(response)) throw new InternalServerException();
        if (isServiceUnavailable(response)) throw new ServiceUnavailableException(); // todo: retry

        return new PdfApiResponse(response);
    }

    private void handleBadRequest(HttpResponse response) throws PdfApiException {
        if (!isBadRequest(response)) return;
        ApiErrorResponse errorResponse;

        try {
            errorResponse = new Gson().fromJson(new InputStreamReader(response.getEntity().getContent()), ApiErrorResponse.class);
        } catch (IOException e) {
            throw new PdfApiException(e);
        }

        throw new BadRequestException(errorResponse);
    }

    private boolean isBadRequest(HttpResponse response) {
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_BAD_REQUEST;
    }

    private boolean isInternalError(HttpResponse response) {
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    private boolean isServiceUnavailable(HttpResponse response) {
        int code = response.getStatusLine().getStatusCode();

        return code == HttpStatus.SC_BAD_GATEWAY || code == HttpStatus.SC_SERVICE_UNAVAILABLE || code == HttpStatus.SC_GATEWAY_TIMEOUT;
    }
}
